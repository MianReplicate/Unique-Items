package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import mc.mian.uniqueitems.util.ModUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//TODO: Perhaps make this more generic in the future, this is specific to crafting table. maybe add support for furnace and other modded thingies.
@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {
    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V"))
    private void onQuickCraft(Player player, int index, CallbackInfoReturnable<ItemStack> cir, @Local Slot slotLocalRef){
        ModUtil.onCraft(player, slotLocalRef);
    }
}
