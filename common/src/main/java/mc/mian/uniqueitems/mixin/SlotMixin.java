package mc.mian.uniqueitems.mixin;

import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Slot.class)
public abstract class SlotMixin {
//    @Shadow public abstract ItemStack getItem();
//
//    public UniqueSavedData getUniqueData(ServerLevel level){
//        return UniqueSavedData.getOrCreate(level.getDataStorage());
//    }
//
//    @Inject(method = "onTake", at = @At("HEAD"))
//    public void onTake(Player player, ItemStack stack, CallbackInfo ci){
//        if(!player.level().isClientSide && ((Slot)(Object)this) instanceof ResultSlot){
//            UniqueItems.LOGGER.info("uniqueness added");
//            getUniqueData((ServerLevel) player.level()).addOrReduceItemUniqueness(stack.getItem(), 1);
//            UniqueItems.LOGGER.info(getUniqueData((ServerLevel) player.level()).getUniqueness(stack.getItem()));
//        }
//    }
//
//    @Inject(method = "set", at = @At("HEAD"))
//    public void set(ItemStack stack, CallbackInfo ci){
//        if(((Slot)(Object)this) instanceof ResultSlot slot && !slot.player.level().isClientSide){
//            UniqueSavedData savedData = getUniqueData((ServerLevel) slot.player.level());
//            if(savedData.getUniqueness(stack.getItem()) != null){
//                if(savedData.getUniqueness(stack.getItem()) <= 0){
//                    UniqueItems.LOGGER.info("canceled result: "+stack.getDisplayName().toString());
//                    ci.cancel();
//                }
//            }
//        }
//    }
//
//    @Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
//    public void mayPickup(Player player, CallbackInfoReturnable<Boolean> cir){
//        if(!player.level().isClientSide){
//            if(((Slot)(Object)this) instanceof ResultSlot slot){
//                UniqueSavedData savedData = getUniqueData((ServerLevel) slot.player.level());
//                if(savedData.getUniqueness(this.getItem().getItem()) != null){
//                    if(savedData.getUniqueness(this.getItem().getItem()) <= 0){
//                        UniqueItems.LOGGER.info("canceled result");
//                        cir.setReturnValue(false);
//                    }
//                }
//            }
//        }
//    }
}
