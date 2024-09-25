package mc.mian.uniqueitems.forge;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.config.ConfigHolder;
import mc.mian.uniqueitems.datagen.ModDataGenerators;
import mc.mian.uniqueitems.util.ModResources;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModResources.MOD_ID)
public class UniqueItemsForge {
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final IEventBus eventBus = MinecraftForge.EVENT_BUS;
    public UniqueItemsForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);

        UniqueItems.config = ConfigHolder.SERVER;
        UniqueItems.init();

        modEventBus.register(ModDataGenerators.class);
    }
}