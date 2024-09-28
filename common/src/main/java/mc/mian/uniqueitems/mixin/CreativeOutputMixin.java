package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.api.UniqueItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// This class is needed because we need to supply a unique boolean to the compound tag for a unique item
@Mixin(CreativeModeTab.Output.class)
public interface CreativeOutputMixin {

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CreativeModeTab$Output;accept(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V"), method = "Lnet/minecraft/world/item/CreativeModeTab$Output;accept(Lnet/minecraft/world/level/ItemLike;)V")
    default ItemStack accept(ItemStack stack){
        if(((UniqueItem)stack.getItem()).uniqueItems$isUniqueInList()){
            CompoundTag tag = new CompoundTag();
            tag.putByte("Count", (byte) 1);
            tag.putBoolean("unique", true);
            tag.putString("id", stack.getItemHolder().unwrapKey().get().location().toString());
            return ItemStack.of(tag);
        }
        return stack;
    }
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CreativeModeTab$Output;accept(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V"), method = "Lnet/minecraft/world/item/CreativeModeTab$Output;accept(Lnet/minecraft/world/level/ItemLike;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V")
    default ItemStack accept2(ItemStack stack){
        if(((UniqueItem)stack.getItem()).uniqueItems$isUniqueInList()){
            CompoundTag tag = new CompoundTag();
            tag.putByte("Count", (byte) 1);
            tag.putBoolean("unique", true);
            tag.putString("id", stack.getItemHolder().unwrapKey().get().location().toString());
            return ItemStack.of(tag);
        }
        return stack;
    }
}
