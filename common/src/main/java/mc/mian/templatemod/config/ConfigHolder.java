package mc.mian.templatemod.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHolder {
    public static final ModConfigSpec SERVER_SPEC;
    public static final TemplateConfiguration SERVER;

    static{
        final Pair<TemplateConfiguration, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(TemplateConfiguration::new);
        SERVER = specPair.getLeft();
        SERVER_SPEC = specPair.getRight();
    }
}
