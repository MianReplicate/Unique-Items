package mc.mian.uniqueitems.common.level;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueItem;
import mc.mian.uniqueitems.api.UniqueData;
import mc.mian.uniqueitems.util.ModResources;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.HashMap;
import java.util.Optional;

public class UniqueSavedData extends SavedData implements UniqueData {
    private final HashMap<Item, Integer> item_uniqueness_map = new HashMap<>();

    @Override
    public HashMap<Item, Integer> getUniquenesses() {
        return this.item_uniqueness_map;
    }

    @Override
    public void addOrReduceItemUniqueness(Item item, int defaultAmt, int decreaseBy) {
        this.putItem(item, this.getUniqueness(item).orElse(defaultAmt) - decreaseBy);
    }

    @Override
    public void putItem(Item item, int amt) {
        if(item == Items.AIR || amt < 0 || !((UniqueItem) item).isUnique())
            return;
        UniqueItem unique = ((UniqueItem) item);
        unique.setRetrievable(amt > 0);
        this.item_uniqueness_map.put(item, amt);
        this.setDirty();
    }

    @Override
    public Optional<Integer> getUniqueness(Item item) {
        return Optional.ofNullable(this.item_uniqueness_map.get(item));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        if (!this.item_uniqueness_map.isEmpty()) {
            ListTag listTag = new ListTag();
            for (Item item: item_uniqueness_map.keySet()) {
                if(((UniqueItem) item).isUnique())
                    continue;

                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putString("item", item.getDefaultInstance().getItemHolder().unwrapKey().get().location().toString());
                compoundTag.putInt("amount", item_uniqueness_map.get(item));
                listTag.add(compoundTag);
            }
            tag.put("item_uniqueness_map", listTag);
        }
        return tag;
    }

    public static UniqueSavedData load(CompoundTag tag) {
        UniqueSavedData data = create();
        if (tag.contains("item_uniqueness_map", Tag.TAG_LIST)) {
            for (Tag override : tag.getList("item_uniqueness_map", Tag.TAG_COMPOUND)) {
                if(override instanceof CompoundTag itemCompound){
                    String itemLocation = itemCompound.getString("item");
                    Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(itemLocation));
                    if(((UniqueItem) item).isUnique()){
                        int amount = itemCompound.getInt("amount");
                        data.putItem(BuiltInRegistries.ITEM.get(new ResourceLocation(itemLocation)), amount);
                    }
                }
            }
        }
        return data;
    }

    public static UniqueSavedData create(){
        return new UniqueSavedData();
    }

    public static UniqueSavedData getOrCreate(DimensionDataStorage dataStorage){
        return dataStorage.computeIfAbsent(UniqueSavedData::load, UniqueSavedData::create, ModResources.MOD_ID);
    }
}
