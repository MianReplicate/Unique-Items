package mc.mian.templatemod.datagen;

import mc.mian.templatemod.common.item.ModItems;
import mc.mian.templatemod.util.ModResources;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, ModResources.MOD_ID, efh);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.TEMPLATE_ITEM.get());
    }
}
