package mc.mian.uniqueitems;

import mc.mian.uniqueitems.config.ModConfiguration;
import mc.mian.uniqueitems.util.ModResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniqueItems {
    public static final Logger LOGGER = LogManager.getLogger(ModResources.MOD_ID);
    public static ModConfiguration config;

    /** TODO
     - work on cmds to reset item uniquenesses and add an item to uniqueness
     - config to configure what items are unique (with a toggle to flip between that list MAKING items unique VERSUS making items NOT unique) and how UNIQUE (num of times can be crafted/dropped) items are by default. Overrides may be implemented for certain items if you want to edit the uniqueness for them individually.
     - item uniqueness goes down when dropped from entities
     **/

    public static void init() {
        LOGGER.info("Ooo.. shiny shiny");
    }
}