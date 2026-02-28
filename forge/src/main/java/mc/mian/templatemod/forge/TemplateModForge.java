package mc.mian.templatemod.forge;

import mc.mian.templatemod.TemplateMod;
import mc.mian.templatemod.config.ConfigHolder;
import mc.mian.templatemod.util.TemplateConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.lang.invoke.MethodHandles;

@Mod(TemplateConstants.MOD_ID)
public class TemplateModForge {
    public static BusGroup modEventGroup = FMLJavaModLoadingContext.get().getModBusGroup();
    public static final BusGroup commonEventBus = BusGroup.DEFAULT;

    public TemplateModForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);

        TemplateMod.config = ConfigHolder.SERVER;
        TemplateMod.init();
    }
}