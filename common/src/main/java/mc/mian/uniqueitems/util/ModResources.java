package mc.mian.uniqueitems.util;

import net.minecraft.resources.ResourceLocation;

public class ModResources {
    public static final String MOD_ID = "uniqueitems";

    public static ResourceLocation modLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}