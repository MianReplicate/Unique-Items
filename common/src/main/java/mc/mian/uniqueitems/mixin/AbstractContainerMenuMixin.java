package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueData;
import mc.mian.uniqueitems.common.level.UniqueSavedData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {
    @Inject(method = "doClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;tryRemove(IILnet/minecraft/world/entity/player/Player;)Ljava/util/Optional;"))
    public void doClick(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci, @Local Slot slotLocalRef){
        if(slotLocalRef instanceof ResultSlot slot && player.level() instanceof ServerLevel level){
            Item item = slot.getItem().getItem();
            UniqueData savedData = UniqueSavedData.getOrCreate(level.getServer().overworld().getDataStorage());
            savedData.addOrReduceItemUniqueness(item, UniqueItems.config.DEFAULT_UNIQUENESS.get());
        }
    }
}
