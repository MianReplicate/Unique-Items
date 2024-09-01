package mc.mian.templatemod.common;

import mc.mian.templatemod.TemplateMod;
import mc.mian.templatemod.registry.DeferredRegistry;
import mc.mian.templatemod.registry.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final DeferredRegistry<Block> BLOCKS = DeferredRegistry.create(TemplateMod.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> TEMPLATE_BLOCK = BLOCKS.register("template_block", () -> new Block(BlockBehaviour.Properties.of()));

    public static void register(){
        TemplateMod.LOGGER.info("Registering " + ModBlocks.class.getName() + " for " + TemplateMod.MOD_ID);
        BLOCKS.register();
    }
}
