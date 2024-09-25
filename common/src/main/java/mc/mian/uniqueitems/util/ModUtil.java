package mc.mian.uniqueitems.util;

import mc.mian.uniqueitems.common.level.UniqueSavedData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;

public class ModUtil {
    public static boolean isRetrievable(ServerLevel level, Item item){
        UniqueSavedData uniqueSavedData = UniqueSavedData.getOrCreate(level.getServer().overworld().getDataStorage());
        return uniqueSavedData.getUniqueness(item).orElse(Integer.MAX_VALUE) > 0;
    }
}
