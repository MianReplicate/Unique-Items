package mc.mian.templatemod.platform;

import mc.mian.templatemod.common.item.TemplateItems;
import mc.mian.templatemod.common.tab.TemplateCreativeModeTabs;
import mc.mian.templatemod.platform.services.ITabCreator;
import mc.mian.templatemod.util.TemplateConstants;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class FabricTabCreator implements ITabCreator {
    public CreativeModeTab createTab(String title){
        return FabricCreativeModeTab.builder()
                .icon(TemplateCreativeModeTabs::makeIcon)
                .title(Component.translatable("itemGroup."+ TemplateConstants.MOD_ID+"."+title))
                .displayItems((itemDisplayParameters, output) -> TemplateItems.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
                .build();
    }
}
