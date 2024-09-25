package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueData;
import mc.mian.uniqueitems.api.UniqueItem;
import mc.mian.uniqueitems.common.level.UniqueSavedData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(LootTable.class)
public class LootTableMixin {
    @Inject(method="method_331", at = @At(value = "HEAD"))
    private static void addRandomItem(ServerLevel serverLevel, Consumer consumer, ItemStack itemStack, CallbackInfo ci){
        UniqueData uniqueData = UniqueSavedData.getOrCreate(serverLevel.getServer().overworld().getDataStorage());
        UniqueItem uniqueItem = (UniqueItem) itemStack.getItem();
        if(uniqueItem.isUnique()){
            int uniqueness = uniqueData.getUniqueness(itemStack.getItem()).orElseThrow();
            itemStack.setCount(uniqueness);
        }
    }
    @Inject(method = "method_331", at = @At(ordinal = 0,value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private static void accept(ServerLevel serverLevel, Consumer consumer, ItemStack itemStack, CallbackInfo ci){
        UniqueSavedData.getOrCreate(serverLevel.getServer().overworld().getDataStorage()).addOrReduceItemUniqueness(itemStack.getItem(), UniqueItems.config.DEFAULT_UNIQUENESS.get());
    }
    @Inject(method = "method_331", at = @At(ordinal = 1,value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private static void accept1(ServerLevel serverLevel, Consumer consumer, ItemStack itemStack, CallbackInfo ci){
        UniqueSavedData.getOrCreate(serverLevel.getServer().overworld().getDataStorage()).addOrReduceItemUniqueness(itemStack.getItem(), UniqueItems.config.DEFAULT_UNIQUENESS.get());
    }
}
