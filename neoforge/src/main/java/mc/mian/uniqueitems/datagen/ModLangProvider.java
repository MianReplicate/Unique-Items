package mc.mian.uniqueitems.datagen;

import mc.mian.uniqueitems.util.ModResources;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public static final String MOD_ID = ModResources.MOD_ID;
    
    public ModLangProvider(PackOutput output) {
        super(output, MOD_ID, "en_us");
    }

    public void addChatMessage(String title, String translation){
        add("chat."+ MOD_ID+"."+title, translation);
    }

    @Override
    protected void addTranslations() {
        addChatMessage("set_uniqueness", "Successfully set \"%s\"'s uniqueness to %s.");
        addChatMessage("get_uniqueness", "\"%s\"'s uniqueness is %s.");
        addChatMessage("edited_list", "The unique list has been successfully edited to add/remove \"%s\".");
    }
}
