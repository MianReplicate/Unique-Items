package mc.mian.uniqueitems.api;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Optional;

public interface UniqueData {
    HashMap<Item, Integer> getUniquenesses();
    void addOrReduceItemUniqueness(Item item, int defaultAmt);
    void putItem(Item item, int defaultAmt);
    Optional<Integer> getUniqueness(Item item);
}
