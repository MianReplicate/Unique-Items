package mc.mian.templatemod.fabric;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import mc.mian.templatemod.platform.FabricPlatformHelper;
import mc.mian.templatemod.platform.Services;
import mc.mian.templatemod.util.TemplateConstants;
import net.fabricmc.api.ModInitializer;
import mc.mian.templatemod.TemplateMod;
import mc.mian.templatemod.config.ConfigHolder;
import net.neoforged.fml.config.ModConfig;

public class TemplateModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ConfigRegistry.INSTANCE.register(TemplateConstants.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        TemplateMod.config = ConfigHolder.SERVER;
        ((FabricPlatformHelper) Services.PLATFORM).init();
        TemplateMod.init();
    }
}