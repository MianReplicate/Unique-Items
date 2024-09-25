package mc.mian.uniqueitems.datagen;

import mc.mian.uniqueitems.util.ModResources;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public static final String MOD_ID = ModResources.MOD_ID;
    
    public ModLangProvider(PackOutput output) {
        super(output, MOD_ID, "en_us");
    }

    public void addAdvancement(ResourceLocation advancementLocation, String title, String desc){
        add("advancement."+MOD_ID+":"+advancementLocation.getPath(), title);
        add("advancement."+MOD_ID+":"+advancementLocation.getPath()+".desc", desc);
    }

    public void addGuiMessage(String title, String translation){
        add("gui."+ MOD_ID+"."+title, translation);
    }

    public void addChatMessage(String title, String translation){
        add("chat.message."+ MOD_ID+"."+title, translation);
    }

    public void addDisconnectionReason(String title, String translation){
        add("disconnect."+ MOD_ID+"."+title, translation);
    }

    public void addItemGroup(String title, String translation){
        add("itemGroup."+ModResources.MOD_ID+"."+title, translation);
    }

    public void addSound(String title, String translation){
        add(ModResources.MOD_ID+".sound."+title, translation);
    }

    @Override
    protected void addTranslations() {

    }
}
