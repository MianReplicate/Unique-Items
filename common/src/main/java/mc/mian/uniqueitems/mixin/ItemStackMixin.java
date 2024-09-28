package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueItem;
import mc.mian.uniqueitems.api.UniqueItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements UniqueItemStack {
    @Mutable
    @Shadow @Final @Deprecated @Nullable private Item item;

    @Shadow private int count;

    @Shadow public abstract Item getItem();

    @Shadow public abstract void setCount(int count);

    @Unique
    private boolean unique = false;

    @Inject(method = "save", at = @At(value = "RETURN"))
    public void save(CompoundTag compoundTag, CallbackInfoReturnable<CompoundTag> cir){
        if(((UniqueItem) this.getItem()).uniqueItems$isUniqueInList())
            compoundTag.putBoolean("unique", this.uniqueItems$getUnique());
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/ItemLike;I)V", at = @At(value = "TAIL"))
    private void init(ItemLike itemLike, int count, CallbackInfo ci){
        UniqueItem item = (UniqueItem) itemLike.asItem();
        if(item.uniqueItems$isEditable()){
//            CompoundTag compoundTag = this.getTag();
//            UniqueItems.LOGGER.info("compound:"+compoundTag);
//            if(compoundTag != null){
//                if(!compoundTag.contains("unique") && !item.uniqueItems$canMake()) {
//                    this.item = Items.AIR;
//                } else if(!compoundTag.contains("unique") && item.uniqueItems$canMake()) {
//                    UniqueItems.LOGGER.info("meow");
//                    // gotta mkae it so count changes if this is too much of a number
//                    item.uniqueItems$setUniqueness(item.uniqueItems$getUniqueness() - count);
//                    compoundTag.putBoolean("unique", true);
//                    this.setTag(compoundTag);
//                }
//            } else {
                if(!item.uniqueItems$canMakeNewStack() && !this.uniqueItems$getUnique()) {
                    UniqueItems.LOGGER.info("Not RETRIEVABLE");
                    this.item = Items.AIR;
                } else if(!this.uniqueItems$getUnique()){
                    // if it's not already unique, then it is a newly created item
                    UniqueItems.LOGGER.info("New item, REMOVE UNIQUENESS");
                    int allowedToGive = Math.min(item.uniqueItems$getUniqueness(), count);
                    this.setCount(allowedToGive);
                    UniqueItems.LOGGER.info("ALLOWED TO GIVE: "+allowedToGive);
                    UniqueItems.LOGGER.info("UNIQUENESS AMOUNT: "+item.uniqueItems$getUniqueness());
                    UniqueItems.LOGGER.info("COUNT: "+count);
                    item.uniqueItems$setUniqueness(item.uniqueItems$getUniqueness() - allowedToGive);
                    this.uniqueItems$setUnique(true);
                }
//            }
        }
    }
    @Inject(method = "<init>(Lnet/minecraft/nbt/CompoundTag;)V", at = @At(value = "TAIL"))
    private void init(CompoundTag compoundTag, CallbackInfo ci){
        UniqueItem item = (UniqueItem) BuiltInRegistries.ITEM.get(new ResourceLocation(compoundTag.getString("id")));
        if(item.uniqueItems$isEditable()){
            UniqueItems.LOGGER.info("unique:"+compoundTag.contains("unique"));
            if(compoundTag.contains("unique")) {
                UniqueItems.LOGGER.info("set unique");
                this.uniqueItems$setUnique(compoundTag.getBoolean("unique"));
            }else{
                // new item being created
                if(!item.uniqueItems$canMakeNewStack()) {
                    UniqueItems.LOGGER.info(3);
                    this.item = Items.AIR;
                }else{
                    UniqueItems.LOGGER.info("meow");
                    // gotta mkae it so count changes if this is too much of a number
                    int allowedToGive = Math.min(item.uniqueItems$getUniqueness(), compoundTag.getByte("Count"));
                    this.setCount(allowedToGive);
                    item.uniqueItems$setUniqueness(item.uniqueItems$getUniqueness() - allowedToGive);
                    this.uniqueItems$setUnique(true);
                }
            }
        }
    }

    @Override
    public void uniqueItems$setUnique(boolean unique) {
        if(((UniqueItem) this.getItem()).uniqueItems$isUniqueInList())
            this.unique = unique;
    }

    @Override
    public boolean uniqueItems$getUnique() {
        return this.unique;
    }
}
