package mc.mian.uniqueitems.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.tree.CommandNode;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandContextBuilder.class)
public class CommandManagerMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;setConsumer(Lcom/mojang/brigadier/ResultConsumer;)V", remap = false), method = "<init>")
    private void fabric_addCommands(CommandDispatcher dispatcher, Object source, CommandNode rootNode, int start, CallbackInfo ci) {
        CommandRegistrationCallback.EVENT.invoker().register(this.dispatcher, registryAccess, environment);
    }
}
