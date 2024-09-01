package mc.mian.templatemod;

import mc.mian.templatemod.config.ModConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemplateMod {
    public static final String MOD_ID = "templatemod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static ModConfiguration config;

    public static void init() {
        LOGGER.info("Hiya, I am a template mod!");
    }
}