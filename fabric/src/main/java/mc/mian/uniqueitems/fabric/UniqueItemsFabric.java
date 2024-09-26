package mc.mian.uniqueitems.fabric;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import mc.mian.uniqueitems.common.command.UICommand;
import mc.mian.uniqueitems.util.ModResources;
import net.fabricmc.api.ModInitializer;
import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.config.ConfigHolder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.neoforged.fml.config.ModConfig;

public class UniqueItemsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.register(ModResources.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        UniqueItems.config = ConfigHolder.SERVER;
        UniqueItems.init();

        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandBuildContext, commandSelection) -> UICommand.register(commandDispatcher, commandBuildContext));
    }
}