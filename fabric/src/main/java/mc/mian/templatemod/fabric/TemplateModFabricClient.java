package mc.mian.templatemod.fabric;

import mc.mian.templatemod.client.TemplateKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class TemplateModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(mc -> TemplateKeybinds.tickKeybinds());

        TemplateKeybinds.getKeys().forEach(KeyMappingHelper::registerKeyMapping);
    }
}