package mc.mian.templatemod.neoforge.event;

import mc.mian.templatemod.client.TemplateKeybinds;
import mc.mian.templatemod.util.TemplateConstants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

public class TemplateModClientEvents {
    @EventBusSubscriber(modid = TemplateConstants.MOD_ID, value = Dist.CLIENT)
    public static class Common{
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Pre tickEvent){
            TemplateKeybinds.tickKeybinds();
        }
    }

    @EventBusSubscriber(modid = TemplateConstants.MOD_ID, value = Dist.CLIENT)
    public static class Mod{
        @SubscribeEvent
        public static void registerKeybinds(RegisterKeyMappingsEvent event){
            TemplateKeybinds.getKeys().forEach(event::register);
        }
    }
}
