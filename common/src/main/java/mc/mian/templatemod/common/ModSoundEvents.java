package mc.mian.templatemod.common;

import mc.mian.templatemod.registry.DeferredRegistry;
import mc.mian.templatemod.registry.RegistrySupplierHolder;
import mc.mian.templatemod.util.ModResources;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static final DeferredRegistry<SoundEvent> SOUND_EVENTS = DeferredRegistry.create(ModResources.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplierHolder<SoundEvent, SoundEvent> MUSIC_MEOW = SOUND_EVENTS.registerForHolder(
            "template_sound_event", () -> SoundEvent.createVariableRangeEvent(ModResources.TEMPLATE_SOUND));
}
