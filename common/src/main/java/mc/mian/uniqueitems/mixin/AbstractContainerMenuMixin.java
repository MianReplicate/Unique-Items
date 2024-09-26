package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import mc.mian.uniqueitems.util.ModUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public class AbstractContainerMenuMixin {
    @Inject(method = "doClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;tryRemove(IILnet/minecraft/world/entity/player/Player;)Ljava/util/Optional;"))
    public void doClick(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci, @Local Slot slotLocalRef){
        ModUtil.onCraft(player, slotLocalRef);
    }
}
