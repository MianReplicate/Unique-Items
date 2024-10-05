package mc.mian.templatemod.forge;

import fuzs.forgeconfigapiport.forge.api.neoforge.v4.NeoForgeConfigRegistry;
import mc.mian.templatemod.TemplateMod;
import mc.mian.templatemod.config.ConfigHolder;
import mc.mian.templatemod.forge.event.TemplateModClientEvents;
import mc.mian.templatemod.util.TemplateConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TemplateConstants.MOD_ID)
public class TemplateModForge {
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final IEventBus commonEventBus = MinecraftForge.EVENT_BUS;
    public TemplateModForge() {
        NeoForgeConfigRegistry.INSTANCE.register(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);

        TemplateMod.config = ConfigHolder.SERVER;
        TemplateMod.init();
    }
}