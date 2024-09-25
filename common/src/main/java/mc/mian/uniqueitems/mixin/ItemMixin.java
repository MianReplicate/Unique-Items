package mc.mian.uniqueitems.mixin;

import mc.mian.uniqueitems.api.UniqueItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemMixin implements UniqueItem {
    private boolean retrievable = true;
    private boolean unique = false;

    @Override
    public boolean isRetrievable() {
        return this.retrievable;
    }

    @Override
    public void setRetrievable(boolean retrievable) {
        this.retrievable = retrievable;
    }

    @Override
    public boolean isUnique() {
        return this.unique;
    }

    @Override
    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
