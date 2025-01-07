package net.nuclearteam.createnuclear;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.RegistryObject;
import net.nuclearteam.createnuclear.content.effects.
import org.apache.http.config.Registry;

public class CNDamagesTypes {
    //public static final LazyRegistrar<MobEffect> EFFECTS = LazyRegistrar.create(Registries.MOB_EFFECT, CreateNuclear.MOD_ID);
    public static final BuiltInRegistri
    public static final RegistryObject<MobEffect> RADIATION = EFFECTS.register("radiation", RadiationEffect::new);

    public static void register() {
        EFFECTS.register();
    }
}
