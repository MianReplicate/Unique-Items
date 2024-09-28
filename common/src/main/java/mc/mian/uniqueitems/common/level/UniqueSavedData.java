package mc.mian.uniqueitems.common.level;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueItem;
import mc.mian.uniqueitems.api.UniqueData;
import mc.mian.uniqueitems.util.ModResources;
import mc.mian.uniqueitems.util.ModUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Optional;

public class UniqueSavedData extends SavedData implements UniqueData {
    private final HashMap<Item, Integer> item_uniqueness_map;
    private MinecraftServer server;

    public UniqueSavedData(MinecraftServer server){
        this.server = server;
        this.item_uniqueness_map = new HashMap<>();

        UniqueItems.config.UNIQUE_ITEM_LIST.get().forEach(string -> this.putItem(BuiltInRegistries.ITEM.get(new ResourceLocation(string)), UniqueItems.config.DEFAULT_UNIQUENESS.get()));
    }

    public void tick(){
        item_uniqueness_map.forEach((item, integer) -> {
            UniqueItem uniqueItem = (UniqueItem) item;
            if(uniqueItem.uniqueItems$getUniqueness() != integer){
                UniqueItems.LOGGER.info("subtracting: "+ (uniqueItem.uniqueItems$getUniqueness() - integer));
                ModUtil.announceItemIfLastSpawn(server, item, -(uniqueItem.uniqueItems$getUniqueness() - integer));
            }
        });
    }

    @Override
    public HashMap<Item, Integer> getUniquenesses() {
        return this.item_uniqueness_map;
    }

    @Override
    public void addOrReduceItemUniqueness(Item item, int decreaseBy) {
        this.putItem(item, this.getUniquenessOrDefault(item) - decreaseBy);
    }

    @Override
    public void putItem(Item item, int amt) {
        if(item == Items.AIR || amt < 0 || !((UniqueItem) item).uniqueItems$isUniqueInList())
            return;
        UniqueItem unique = ((UniqueItem) item);
        unique.uniqueItems$setSide(false);
        unique.uniqueItems$setUniqueness(amt);
        UniqueItems.LOGGER.info("set for "+ item);
        this.item_uniqueness_map.put(item, amt);
        this.setDirty();
    }

    @Override
    public Optional<Integer> getUniqueness(Item item) {
        return Optional.ofNullable(this.item_uniqueness_map.get(item));
    }

    @Override
    public Integer getUniquenessOrDefault(Item item) {
        return this.getUniqueness(item).orElse(UniqueItems.config.DEFAULT_UNIQUENESS.get());
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        if (!this.item_uniqueness_map.isEmpty()) {
            ListTag listTag = new ListTag();
            for (Item item: item_uniqueness_map.keySet()) {
                if(!((UniqueItem) item).uniqueItems$isUniqueInList())
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

    public static UniqueSavedData load(CompoundTag tag, MinecraftServer server) {
        UniqueSavedData data = create(server);
        if (tag.contains("item_uniqueness_map", Tag.TAG_LIST)) {
            for (Tag override : tag.getList("item_uniqueness_map", Tag.TAG_COMPOUND)) {
                if(override instanceof CompoundTag itemCompound){
                    String itemLocation = itemCompound.getString("item");
                    Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(itemLocation));
                    if(((UniqueItem) item).uniqueItems$isUniqueInList()){
                        int amount = itemCompound.getInt("amount");
                        data.putItem(BuiltInRegistries.ITEM.get(new ResourceLocation(itemLocation)), amount);
                    }
                }
            }
        }
        return data;
    }

    public static UniqueSavedData create(MinecraftServer server){
        UniqueSavedData data = new UniqueSavedData(server);
        data.setDirty();
        return data;
    }

    public static UniqueSavedData getOrCreate(MinecraftServer server){
        return server.overworld().getDataStorage().computeIfAbsent(compound -> UniqueSavedData.load(compound, server), () -> UniqueSavedData.create(server), ModResources.MOD_ID);
    }
}
