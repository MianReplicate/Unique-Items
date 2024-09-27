package mc.mian.uniqueitems;

import mc.mian.uniqueitems.config.ModConfiguration;
import mc.mian.uniqueitems.util.ModResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniqueItems {
    public static final Logger LOGGER = LogManager.getLogger(ModResources.MOD_ID);
    public static ModConfiguration config;

    //TODO:
    // add item tooltip that says if item is retrievable.
    // add config option to make item uniqueness go back up whenever itemstack is destroyed
    // add item that can make every item in a chest considered unique
    // remove need of fabric api by registering cmds via mixin
    // list of config values should be put into a map for better lookup performance
    // put uniqueness nbt/compound on item, check in constructor, if it doesnt exist, add and decrement uniqueness counter

    public static void init() {
        LOGGER.info("Ooo.. shiny shiny");

    }
}