package mc.mian.templatemod.datagen;

import mc.mian.templatemod.common.block.ModBlocks;
import mc.mian.templatemod.common.item.ModItems;
import mc.mian.templatemod.util.ModResources;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.CompletableFuture;

public class ModDataGenerators {
    private static final String PATH_ITEM_PREFIX = "textures/item";
    private static final String PATH_BLOCK_PREFIX = "textures/block";
    private static final String PATH_SUFFIX = ".png";

    @SubscribeEvent
    public static void generateData(GatherDataEvent ev) {
        final CompletableFuture<HolderLookup.Provider> provider = ev.getLookupProvider();
        final DataGenerator gen = ev.getGenerator();
        final PackOutput packOutput = gen.getPackOutput();
        final ExistingFileHelper efh = ev.getExistingFileHelper();

        addVirtualPackContents(efh);

        if (ev.includeServer()) {
            gen.addProvider(ev.includeServer(), new ModLangProvider(packOutput));
            gen.addProvider(ev.includeServer(), new ModItemModelProvider(packOutput, efh));
            gen.addProvider(ev.includeServer(), new ModStateAndModelProvider(packOutput, efh));
            TagsProvider blockTagProvider = gen.addProvider(ev.includeServer(), new ModBlockTagProvider(packOutput, provider, efh));
            gen.addProvider(ev.includeServer(), new ModItemTagProvider(
                    packOutput, provider, blockTagProvider.contentsGetter(), efh
            ));
        }
    }

    private static void addVirtualPackContents(ExistingFileHelper existingFileHelper) {
        existingFileHelper.trackGenerated(
                ModResources.modLoc(ModItems.TEMPLATE_ITEM.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_ITEM_PREFIX
        );
        existingFileHelper.trackGenerated(
                ModResources.modLoc(ModBlocks.TEMPLATE_BLOCK.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_BLOCK_PREFIX
        );
    }
}
