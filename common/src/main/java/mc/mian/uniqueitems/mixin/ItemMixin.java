package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemMixin implements UniqueItem {
    private boolean retrievable = true;

    @Override
    public boolean isRetrievable() {
        return this.retrievable || !this.isUnique();
    }

    @Override
    public void setRetrievable(boolean retrievable) {
        this.retrievable = retrievable;
    }

    @Override
    public boolean isUnique() {
        ResourceLocation itemLocation = BuiltInRegistries.ITEM.getKey((Item)(Object)this);
        if(!UniqueItems.config.INVERT_LIST.get())
            return UniqueItems.config.UNIQUE_ITEM_LIST.get().contains(itemLocation.toString());
        else
            return !UniqueItems.config.UNIQUE_ITEM_LIST.get().contains(itemLocation.toString());
    }
}
