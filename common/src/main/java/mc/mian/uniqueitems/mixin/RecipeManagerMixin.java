package mc.mian.uniqueitems.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import mc.mian.uniqueitems.api.UniqueItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

// Should prevent recipes from being shown when the item is no longer retrievable, will help add more compat with mods
@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method="method_42301",at = @At(value = "HEAD"),cancellable = true)
    private static void redirect(Container container, Level level, Recipe recipe, CallbackInfoReturnable<Boolean> cir){
        if(!((UniqueItem)recipe.getResultItem(level.registryAccess()).getItem()).uniqueItems$canMakeNewStack())
            cir.setReturnValue(false);
    }
    @Redirect(at = @At(value = "INVOKE", target = "Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"), method="Lnet/minecraft/world/item/crafting/RecipeManager;getRecipeFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/Container;Lnet/minecraft/world/level/Level;Lnet/minecraft/resources/ResourceLocation;)Ljava/util/Optional;")
    private <T> Optional<Pair<ResourceLocation, ?>> redirect(T value, @Local Recipe<?> recipe, @Local Level level, @Local ResourceLocation lastRecipe){
        if(!((UniqueItem)recipe.getResultItem(level.registryAccess()).getItem()).uniqueItems$canMakeNewStack())
            return Optional.empty();
        return Optional.of(Pair.of(lastRecipe, recipe));
    }
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Recipe;matches(Lnet/minecraft/world/Container;Lnet/minecraft/world/level/Level;)Z"), method="method_42298")
    private static <C extends Container> boolean redirect(Recipe recipe, C inventory, Level level){
        if(!((UniqueItem)recipe.getResultItem(level.registryAccess()).getItem()).uniqueItems$canMakeNewStack())
            return false;
        return recipe.matches(inventory, level);
    }
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Recipe;matches(Lnet/minecraft/world/Container;Lnet/minecraft/world/level/Level;)Z"), method="method_42297")
    private static <C extends Container> boolean redirect2(Recipe recipe, C inventory, Level level){
        if(!((UniqueItem)recipe.getResultItem(level.registryAccess()).getItem()).uniqueItems$canMakeNewStack())
            return false;
        return recipe.matches(inventory, level);
    }
}
