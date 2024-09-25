package mc.mian.uniqueitems;

import mc.mian.uniqueitems.config.ModConfiguration;
import mc.mian.uniqueitems.util.ModResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniqueItems {
    public static final Logger LOGGER = LogManager.getLogger(ModResources.MOD_ID);
    public static ModConfiguration config;

    public static void init() {
        LOGGER.info("Ooo.. shiny shiny");
    }
}