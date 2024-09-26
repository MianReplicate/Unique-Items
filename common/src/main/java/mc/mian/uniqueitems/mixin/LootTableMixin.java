package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.api.UniqueData;
import mc.mian.uniqueitems.api.UniqueItem;
import mc.mian.uniqueitems.common.level.UniqueSavedData;
import mc.mian.uniqueitems.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(LootTable.class)
public class LootTableMixin {
    @Inject(method="method_331", at = @At(value = "HEAD"))
    private static void addRandomItem(ServerLevel serverLevel, Consumer consumer, ItemStack itemStack, CallbackInfo ci){
        UniqueData uniqueData = UniqueSavedData.getOrCreate(serverLevel.getServer().overworld().getDataStorage());
        UniqueItem uniqueItem = (UniqueItem) itemStack.getItem();
        if(uniqueItem.isUnique()){
            int uniqueness = uniqueData.getUniquenessOrDefault(itemStack.getItem());
            if(itemStack.getCount() > uniqueness)
                itemStack.setCount(uniqueness);
        }
    }
    @Redirect(method = "method_331", at = @At(ordinal = 1,value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I"))
    private static int getCount(ItemStack itemStack, @Local ServerLevel level){
        UniqueData uniqueData = UniqueSavedData.getOrCreate(level.getServer().overworld().getDataStorage());
        UniqueItem uniqueItem = (UniqueItem) itemStack.getItem();
        int count = itemStack.getCount();
        if(uniqueItem.isUnique()){
            int uniqueness = uniqueData.getUniquenessOrDefault(itemStack.getItem());
            if(count > uniqueness)
                count = uniqueness;
        }
        return count;
    }

    @Inject(method = "method_331", at = @At(ordinal = 0,value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private static void accept(ServerLevel serverLevel, Consumer consumer, ItemStack itemStack, CallbackInfo ci){
        ModUtil.announceItemIfLastSpawn(serverLevel.getServer(), itemStack.getItem(), itemStack.getCount());
    }
    @Inject(method = "method_331", at = @At(ordinal = 1,value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private static void accept1(ServerLevel serverLevel, Consumer consumer, ItemStack itemStack, CallbackInfo ci){
        ModUtil.announceItemIfLastSpawn(serverLevel.getServer(), itemStack.getItem(), itemStack.getCount());
    }

    @Redirect(method = "method_331", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isItemEnabled(Lnet/minecraft/world/flag/FeatureFlagSet;)Z"))
    private static boolean isItemEnabled(ItemStack itemStack, FeatureFlagSet enabledFlags){
        return itemStack.isItemEnabled(enabledFlags) && ((UniqueItem) itemStack.getItem()).isRetrievable();
    }
}
