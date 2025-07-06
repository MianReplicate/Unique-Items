package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements UniqueItem {
    @Unique
    private boolean uniqueItems$retrievable = true;

    @Override
    public boolean uniqueItems$isRetrievable() {
        return this.uniqueItems$retrievable || !this.uniqueItems$isUnique();
    }

    @Override
    public void uniqueItems$setRetrievable(boolean retrievable) {
        this.uniqueItems$retrievable = retrievable;
    }

    @Override
    public boolean uniqueItems$isUnique() {
        ResourceLocation itemLocation = BuiltInRegistries.ITEM.getKey((Item)(Object)this);
        if(!UniqueItems.config.INVERT_LIST.get())
            return UniqueItems.config.UNIQUE_ITEM_LIST.get().contains(itemLocation.toString());
        else
            return !UniqueItems.config.UNIQUE_ITEM_LIST.get().contains(itemLocation.toString());
    }
}
