package mc.mian.templatemod.common.item;

import mc.mian.templatemod.common.sound.ModSoundEvents;
import mc.mian.templatemod.registry.DeferredRegistry;
import mc.mian.templatemod.registry.RegistrySupplier;
import mc.mian.templatemod.util.ModResources;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

public class ModItems {
    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(ModResources.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> TEMPLATE_ITEM = ITEMS.register("template_item", () ->
            new RecordItem(11, ModSoundEvents.TEMPLATE_SOUND_EVENT.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 156));
}
