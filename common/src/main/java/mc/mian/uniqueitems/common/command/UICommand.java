package mc.mian.uniqueitems.common.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class UICommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(
                Commands.literal("ui")
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                        .then(Commands.literal("set_uniqueness")
                                .then(Commands.argument("add", BoolArgumentType.bool())
                                        .then(Commands.argument("block_id", ResourceArgument.resource(context, Registries.BLOCK))
                                                .suggests(((commandContext, suggestionsBuilder) ->
                                                        SharedSuggestionProvider.suggestResource(
                                                                BuiltInRegistries.BLOCK.keySet().stream(), suggestionsBuilder
                                                        )))
                                                .executes(source ->
                                                        addBlockId(
                                                                source.getSource(),
                                                                BoolArgumentType.getBool(source, "add"),
                                                                ResourceArgument.getResource(source, "block_id", Registries.BLOCK))))))
                        .then(Commands.literal("add")
                                .then(Commands.argument("from", BlockPosArgument.blockPos())
                                        .then(
                                                Commands.argument("to", BlockPosArgument.blockPos())
                                                        .then(
                                                                Commands.argument("indestructible", BoolArgumentType.bool())
                                                                        .executes(commandContext ->
                                                                                setStateOfBlocks(
                                                                                        commandContext.getSource(),
                                                                                        BoundingBox.fromCorners(BlockPosArgument.getLoadedBlockPos(commandContext, "from"), BlockPosArgument.getLoadedBlockPos(commandContext, "to")),
                                                                                        BoolArgumentType.getBool(commandContext, "indestructible")))))))
        );
    }

}
