package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import mc.mian.uniqueitems.api.UniqueItem;
import mc.mian.uniqueitems.util.ModUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {
    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V"))
    private void slotGrid(Player player, int index, CallbackInfoReturnable<ItemStack> cir, @Local Slot slotLocalRef){
        ModUtil.onCraft(player, slotLocalRef);
    }

    @Redirect(method = "slotChangedCraftingGrid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isItemEnabled(Lnet/minecraft/world/flag/FeatureFlagSet;)Z"))
    private static boolean isItemEnabled(ItemStack stack, FeatureFlagSet enabledFlags){
        return stack.isItemEnabled(enabledFlags) && ((UniqueItem) stack.getItem()).isRetrievable();
    }
}
