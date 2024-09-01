package mc.mian.templatemod.datagen;

import mc.mian.templatemod.TemplateMod;
import mc.mian.templatemod.common.ModBlocks;
import mc.mian.templatemod.common.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, TemplateMod.MOD_ID, "en_us");
    }

    public void addAdvancement(ResourceLocation advancementLocation, String title, String desc){
        add("advancement."+TemplateMod.MOD_ID+":"+advancementLocation.getPath(), title);
        add("advancement."+TemplateMod.MOD_ID+":"+advancementLocation.getPath()+".desc", desc);
    }

    public void addGuiMessage(String title, String translation){
        add("gui."+ TemplateMod.MOD_ID+"."+title, translation);
    }

    public void addChatMessage(String title, String translation){
        add("chat.message."+ TemplateMod.MOD_ID+"."+title, translation);
    }

    public void addBannedMessage(String title, String translation){
        add("bannedmessage."+ TemplateMod.MOD_ID+"."+title, translation);
    }

    @Override
    protected void addTranslations() {
        add("templatemod.configuration.title", ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName() + " Configuration");
        addBlock(ModBlocks.TEMPLATE_BLOCK, "Meowing Block");
        addItem(ModItems.TEMPLATE_ITEM, "Meowing Item");
    }
}
