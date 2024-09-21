package mc.mian.templatemod.datagen;

import mc.mian.templatemod.common.block.ModBlocks;
import mc.mian.templatemod.util.ModResources;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModStateAndModelProvider extends BlockStateProvider {
    public ModStateAndModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModResources.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile crystal_block_model = cubeAll(ModBlocks.TEMPLATE_BLOCK.get());
        simpleBlockWithItem(ModBlocks.TEMPLATE_BLOCK.get(), crystal_block_model);
    }
}
