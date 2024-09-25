package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueItem;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Inject(method = "isItemEnabled", at = @At("HEAD"), cancellable = true)
    private void isItemEnabled(FeatureFlagSet enabledFlags, CallbackInfoReturnable<Boolean> cir){
        UniqueItem uniqueItem = (UniqueItem) this.getItem();
        if(uniqueItem.isUnique() && !uniqueItem.isRetrievable()){
            cir.setReturnValue(false);
        }
    }
}
