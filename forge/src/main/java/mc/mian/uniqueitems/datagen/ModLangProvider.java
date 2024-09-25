package mc.mian.uniqueitems.datagen;

import mc.mian.uniqueitems.util.ModResources;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public static final String MOD_ID = ModResources.MOD_ID;
    
    public ModLangProvider(PackOutput output) {
        super(output, MOD_ID, "en_us");
    }

    public void addGuiMessage(String title, String translation){
        add("gui."+ MOD_ID+"."+title, translation);
    }

    @Override
    protected void addTranslations() {
        addGuiMessage("set_uniqueness", "Successfully set \"%s\"'s uniqueness to %s.");
        addGuiMessage("get_uniqueness", "\"%s\"'s uniqueness is %s.");
    }
}
