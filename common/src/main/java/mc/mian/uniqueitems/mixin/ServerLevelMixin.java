package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.common.level.UniqueSavedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;
import java.util.function.BooleanSupplier;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @Shadow @Nonnull public abstract MinecraftServer getServer();

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void tick(BooleanSupplier hasTimeLeft, CallbackInfo ci){
        UniqueSavedData.getOrCreate(this.getServer()).tick();
    }
}
