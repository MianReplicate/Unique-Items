package mc.mian.templatemod.datagen;

import mc.mian.templatemod.common.block.TemplateBlocks;
import mc.mian.templatemod.common.item.TemplateItems;
import mc.mian.templatemod.util.TemplateConstants;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class TemplateModelProvider extends ModelProvider {
    public TemplateModelProvider(PackOutput output) {
        super(output, TemplateConstants.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(TemplateBlocks.TEMPLATE_BLOCK.get());

        itemModels.generateFlatItem(TemplateItems.TEMPLATE_ITEM.get(), ModelTemplates.FLAT_ITEM);
    }
}
