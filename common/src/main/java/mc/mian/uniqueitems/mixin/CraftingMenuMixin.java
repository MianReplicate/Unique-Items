package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import mc.mian.uniqueitems.common.level.UniqueSavedData;
import mc.mian.uniqueitems.util.ModUtil;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {
    @ModifyArg(index = 1, method = "slotChangedCraftingGrid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private static ItemStack setItem(ItemStack stack, @Local LocalRef<Level> level){
        return ModUtil.isRetrievable((ServerLevel) level.get(), stack.getItem()) ? stack : ItemStack.EMPTY;
    }

    @ModifyArg(index = 1, method = "slotChangedCraftingGrid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;setRemoteSlot(ILnet/minecraft/world/item/ItemStack;)V"))
    private static ItemStack setRemoteSlot(ItemStack stack, @Local LocalRef<Level> level){
        return ModUtil.isRetrievable((ServerLevel) level.get(), stack.getItem()) ? stack : ItemStack.EMPTY;
    }

    @ModifyArg(method = "slotChangedCraftingGrid", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V"))
    private static Packet<?> sendPacket(Packet<?> packet, @Local LocalRef<AbstractContainerMenu> menu, @Local LocalRef<Level> level, @Local LocalRef<ItemStack> stack){
        ItemStack usedStack = ModUtil.isRetrievable((ServerLevel) level.get(), stack.get().getItem()) ? stack.get() : ItemStack.EMPTY;
        return new ClientboundContainerSetSlotPacket(menu.get().containerId, menu.get().incrementStateId(), 0, usedStack);
    }

    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V"))
    private void slotGrid(Player player, int index, CallbackInfoReturnable<ItemStack> cir, @Local LocalRef<Slot> slotLocalRef){
        if(slotLocalRef.get() instanceof ResultSlot slot && player.level() instanceof ServerLevel level){
            Item item = slot.getItem().getItem();
            UniqueSavedData savedData = UniqueSavedData.getOrCreate(level.getServer().overworld().getDataStorage());
            savedData.addOrReduceItemUniqueness(item, 1);
        }
    }
}
