package mc.mian.uniqueitems.neoforge;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.common.command.UICommand;
import mc.mian.uniqueitems.config.ConfigHolder;
import mc.mian.uniqueitems.datagen.ModDataGenerators;
import mc.mian.uniqueitems.util.ModResources;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(ModResources.MOD_ID)
public class UniqueItemsNeoForge {
    public static final IEventBus eventBus = NeoForge.EVENT_BUS;
    public UniqueItemsNeoForge(IEventBus modEventBus) {
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);

        UniqueItems.config = ConfigHolder.SERVER;
        UniqueItems.init();

        modEventBus.register(ModDataGenerators.class);
        eventBus.register(ModEvents.class);
    }

    public static class ModEvents{
        @SubscribeEvent
        public static void registerCommandsEvent(final RegisterCommandsEvent event){
            UICommand.register(event.getDispatcher(), event.getBuildContext());
        }
    }
}