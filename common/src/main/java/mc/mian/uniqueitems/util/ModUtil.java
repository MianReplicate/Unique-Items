package mc.mian.uniqueitems.util;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueData;
import mc.mian.uniqueitems.api.UniqueItem;
import mc.mian.uniqueitems.common.level.UniqueSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;

public class ModUtil {
    public static void announceItemIfLastSpawn(MinecraftServer server, Item item, int decreaseBy){
        UniqueItem uniqueItem = ((UniqueItem) item);
        UniqueData uniqueData = UniqueSavedData.getOrCreate(server);
        if(uniqueData.getUniquenessOrDefault(item) > 0 && uniqueItem.uniqueItems$isUniqueInList()) {
            uniqueData.addOrReduceItemUniqueness(item, decreaseBy);
            if(uniqueData.getUniquenessOrDefault(item) <= 0) {
                String message = UniqueItems.config.RETRIEVED_MESSAGE.get().formatted(item.getDescription().getString());
                if(!message.isBlank())
                    server.getPlayerList().broadcastSystemMessage(Component.literal(message).withStyle(ChatFormatting.YELLOW), false);
            }
        }
    }

//    public static void onCraft(Player player, Slot slot){
//        if(slot instanceof ResultSlot && player.level() instanceof ServerLevel level){
//            Item item = slot.getItem().getItem();
//            announceItemIfLastSpawn(level.getServer(), item, 1);
//        }
//    }
}
