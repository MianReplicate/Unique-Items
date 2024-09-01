package mc.mian.templatemod.common;

import mc.mian.templatemod.TemplateMod;
import mc.mian.templatemod.registry.DeferredRegistry;
import mc.mian.templatemod.registry.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(TemplateMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> TEMPLATE_ITEM = ITEMS.register("template_item", () -> new Item(new Item.Properties()));

    public static void register(){
        TemplateMod.LOGGER.info("Registering " + ModItems.class.getName() + " for " + TemplateMod.MOD_ID);
        ITEMS.register();
    }
}
