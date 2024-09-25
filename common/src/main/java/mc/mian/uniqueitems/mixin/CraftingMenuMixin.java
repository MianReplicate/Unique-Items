package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueData;
import mc.mian.uniqueitems.common.level.UniqueSavedData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {
    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V"))
    private void slotGrid(Player player, int index, CallbackInfoReturnable<ItemStack> cir, @Local Slot slotLocalRef){
        if(slotLocalRef instanceof ResultSlot slot && player.level() instanceof ServerLevel level){
            Item item = slot.getItem().getItem();
            UniqueData savedData = UniqueSavedData.getOrCreate(level.getServer().overworld().getDataStorage());
            savedData.addOrReduceItemUniqueness(item, UniqueItems.config.DEFAULT_UNIQUENESS.get());
        }
    }
}
