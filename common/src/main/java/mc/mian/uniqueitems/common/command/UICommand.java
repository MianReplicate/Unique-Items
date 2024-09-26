package mc.mian.uniqueitems.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import mc.mian.uniqueitems.UniqueItems;
import mc.mian.uniqueitems.common.level.UniqueSavedData;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UICommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(
                Commands.literal("ui")
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                        .then(Commands.literal("list")
                                .then(Commands.argument("item_id", ResourceArgument.resource(context, Registries.ITEM))
                                        .suggests((commandContext, suggestionsBuilder) ->
                                                SharedSuggestionProvider.suggestResource(
                                                        BuiltInRegistries.ITEM.keySet().stream(), suggestionsBuilder
                                                ))
                                        .then(Commands.argument("add", BoolArgumentType.bool())
                                                .executes(source ->
                                                        editUniqueList(
                                                                source.getSource(),
                                                                ResourceArgument.getResource(source, "item_id", Registries.ITEM),
                                                                BoolArgumentType.getBool(source, "add"))))))
                        .then(Commands.literal("uniqueness")
                                .then(Commands.literal("set")
                                    .then(Commands.argument("item_id", ResourceArgument.resource(context, Registries.ITEM))
                                            .suggests(((commandContext, suggestionsBuilder) ->
                                                    SharedSuggestionProvider.suggestResource(
                                                            BuiltInRegistries.ITEM.keySet().stream(), suggestionsBuilder
                                                    )))
                                            .then(Commands.argument("amount", IntegerArgumentType.integer())
                                                    .executes(source ->
                                                            setItemUniqueness(
                                                                    source.getSource(),
                                                                    ResourceArgument.getResource(source, "item_id", Registries.ITEM),
                                                                    IntegerArgumentType.getInteger(source, "amount"))))))
                                .then(Commands.literal("get")
                                    .then(Commands.argument("item_id", ResourceArgument.resource(context, Registries.ITEM))
                                            .suggests(((commandContext, suggestionsBuilder) ->
                                                    {
                                                        Set<Item> items = UniqueSavedData.getOrCreate(commandContext.getSource().getServer().overworld().getDataStorage()).getUniquenesses().keySet();
                                                        List<ResourceLocation> resourceLocations = new ArrayList<>();
                                                        items.forEach(item -> resourceLocations.add(BuiltInRegistries.ITEM.getKey(item)));
                                                        return SharedSuggestionProvider.suggestResource(
                                                                resourceLocations.stream(), suggestionsBuilder
                                                        );
                                                    }
                                                    ))
                                            .executes(source ->
                                                    getItemUniqueness(
                                                            source.getSource(),
                                                            ResourceArgument.getResource(source, "item_id", Registries.ITEM)
        ))))));
    }

    private static int editUniqueList(CommandSourceStack sourceStack, Holder.Reference<Item> item, boolean add){
        List<String> unique_item_list = (List<String>) UniqueItems.config.UNIQUE_ITEM_LIST.get();
        String stringLocation = BuiltInRegistries.ITEM.getKey(item.value()).toString();
        if(unique_item_list.contains(stringLocation) && !add)
            unique_item_list.remove(stringLocation);
        else if(!unique_item_list.contains(stringLocation) && add)
            unique_item_list.add(stringLocation);
        UniqueItems.config.UNIQUE_ITEM_LIST.set(unique_item_list);
        UniqueItems.config.UNIQUE_ITEM_LIST.save();

        sourceStack.sendSuccess(() -> Component.translatable("chat.uniqueitems.edited_list", item.value().getDescription()), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int setItemUniqueness(CommandSourceStack sourceStack, Holder.Reference<Item> item, int amount){
        UniqueSavedData.getOrCreate(sourceStack.getLevel().getServer().overworld().getDataStorage()).putItem(item.value(), amount);
        sourceStack.sendSuccess(() -> Component.translatable("chat.uniqueitems.set_uniqueness", item.value().getDescription(), amount), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int getItemUniqueness(CommandSourceStack sourceStack, Holder.Reference<Item> item){
        Integer amount = UniqueSavedData.getOrCreate(sourceStack.getLevel().getServer().overworld().getDataStorage()).getUniqueness(item.value()).orElse(null);
        String toUse = amount == null ? "non-existant" : amount.toString();
        sourceStack.sendSuccess(() -> Component.translatable("chat.uniqueitems.get_uniqueness", item.value().getDescription(), toUse), true);
        return Command.SINGLE_SUCCESS;
    }
}
