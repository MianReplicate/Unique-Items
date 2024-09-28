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
    private int uniqueness = -1; // if it is -1, it has not been set yet and thus the server has not started yet (or it is just not unique)
    @Unique
    private boolean isClientSide = true; // default to true

    @Override
    public boolean uniqueItems$canMakeNewStack() {
        if(!this.uniqueItems$isEditable())
            return true;
        return this.uniqueness > 0; // If this is not unique, then force make it retrievable
    }

    @Override
    public boolean uniqueItems$isEditable() {
        return !this.uniqueItems$isClientSide() && this.uniqueItems$isUniqueInList();
    }

    @Override
    public int uniqueItems$getUniqueness() {
        return this.uniqueness;
    }

    @Override
    public void uniqueItems$setUniqueness(int uniqueness){
        if(this.uniqueItems$isEditable())
            this.uniqueness = Math.max(0, uniqueness);
    }

    @Override
    public void uniqueItems$setSide(boolean isClientSide){
        this.isClientSide = isClientSide;
    }

    @Override
    public boolean uniqueItems$isClientSide(){
        return this.isClientSide;
    }

    @Override
    public boolean uniqueItems$isUniqueInList() {
        ResourceLocation itemLocation = BuiltInRegistries.ITEM.getKey((Item)(Object)this);
        if(!UniqueItems.config.INVERT_LIST.get())
            return UniqueItems.config.UNIQUE_ITEM_LIST.get().contains(itemLocation.toString());
        else
            return !UniqueItems.config.UNIQUE_ITEM_LIST.get().contains(itemLocation.toString());
    }
}
