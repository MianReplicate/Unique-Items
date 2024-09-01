package mc.mian.templatemod;

import mc.mian.templatemod.common.ModBlocks;
import mc.mian.templatemod.common.ModCreativeModeTabs;
import mc.mian.templatemod.common.ModItems;
import mc.mian.templatemod.common.ModSoundEvents;
import mc.mian.templatemod.config.ModConfiguration;
import mc.mian.templatemod.util.ModResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemplateMod {
    public static final Logger LOGGER = LogManager.getLogger(ModResources.MOD_ID);
    public static ModConfiguration config;

    public static void init() {
        LOGGER.info("Meow? MEOW!!");
        ModCreativeModeTabs.TABS.register();
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
        ModSoundEvents.SOUND_EVENTS.register();
    }
}