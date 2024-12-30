package net.nuclearteam.createnuclear;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CNItems {

    public static final ItemEntry<Item>
        YELLOWCAKE = CreateNuclear.REGISTRATE
            .item("yellowcake", Item::new)
            .properties(p -> p.food(new FoodProperties.Builder()
                .nutrition(20)
                .saturationMod(.3f)
                .alwaysEat()
                //.effect(() -> new MobEffectInstance(CNEffects.RADIATION.get(), 6000, 25), 1.0F)
                .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 5), 1.0F)
                .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 6000, 1000), 1.0F)
                .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 6000, 5), 1.0F)
                .effect(() -> new MobEffectInstance(MobEffects.WITHER, 6000, 8), 1.0F)
                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 5), 1.0F)
                .build())
            )
            .register()
    ;

    public static void register() {}
}
