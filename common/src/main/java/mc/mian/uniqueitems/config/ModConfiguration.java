package mc.mian.uniqueitems.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;
import java.util.List;

public class ModConfiguration {
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> UNIQUE_ITEM_LIST;
    public final ForgeConfigSpec.BooleanValue INVERT_LIST;
    public final ForgeConfigSpec.IntValue DEFAULT_UNIQUENESS;
    public final ForgeConfigSpec.ConfigValue<String> RETRIEVED_MESSAGE;
    public ModConfiguration(final ForgeConfigSpec.Builder builder) {
        builder.comment("This category holds general values that most people will want to change.");
        builder.push("General Settings");
        this.UNIQUE_ITEM_LIST = buildStringList(builder, "Unique Items", "Put the identifier of an item here to consider it unique/non-unique");
        this.INVERT_LIST = buildBoolean(builder, "Invert List", false, "If this is true, then items by default are considered unique and the list above will be used to make items non-unique");
        this.DEFAULT_UNIQUENESS = buildInt(builder, "Default Uniqueness", 1, 1, Integer.MAX_VALUE, "How many times an item can be created? Example: If an item has unique 4, then it will only show up 4 times in the world");
        this.RETRIEVED_MESSAGE = buildString(builder, "Message Used When Item is Finished", "%s has been retrieved!", "This chat message is sent globally to everyone when an item is no longer retrievable. If you put %s in the text, it will be replaced with the item name. If this is empty, no message will be sent.");
    }

    private static ForgeConfigSpec.ConfigValue<String> buildString(ForgeConfigSpec.Builder builder, String name, String defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.IntValue buildInt(final ForgeConfigSpec.Builder builder, String translationPath, int defaultValue, int min, int max, @Nullable String comment) {
        return comment == null ? builder.translation(translationPath).defineInRange(translationPath, defaultValue, min, max) : builder.comment(comment).translation(translationPath).defineInRange(translationPath, defaultValue, min, max);
    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(final ForgeConfigSpec.Builder builder, String translationPath, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(translationPath).define(translationPath, defaultValue);
    }

    private static ForgeConfigSpec.ConfigValue<List<? extends String>> buildStringList(final ForgeConfigSpec.Builder builder, String translationPath, String comment){
        return builder.comment(comment).translation(translationPath).defineList(translationPath, Lists.newArrayList(), String.class::isInstance);
    }
}
