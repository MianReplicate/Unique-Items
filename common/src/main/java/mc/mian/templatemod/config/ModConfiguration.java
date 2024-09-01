package mc.mian.templatemod.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import javax.annotation.Nullable;

public class ModConfiguration {
    public ModConfiguration(final ModConfigSpec.Builder builder) {
        builder.comment("This category holds general values that most people will want to change.");
        builder.push("General Settings");
    }

    private static ModConfigSpec.IntValue buildInt(final ModConfigSpec.Builder builder, String name, int defaultValue, int min, int max, @Nullable String comment) {
        return comment == null ? builder.translation(name).defineInRange(name, defaultValue, min, max) : builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

    private static ModConfigSpec.DoubleValue buildDouble(final ModConfigSpec.Builder builder, String name, double defaultValue, double min, double max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

    private static ModConfigSpec.ConfigValue buildString(final ModConfigSpec.Builder builder, String name, String defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ModConfigSpec.BooleanValue buildBoolean(final ModConfigSpec.Builder builder, String name, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ModConfigSpec.EnumValue buildEnum(final ModConfigSpec.Builder builder, String name, Enum defaultValue, String comment) {
        return builder.comment(comment).translation(name).defineEnum(name, defaultValue);
    }
}
