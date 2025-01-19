package net.nuclearteam.createnuclear;

import com.google.common.collect.Lists;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class CNPotions {

    private static final List<PotionBrewing.Mix<Potion>> POTION_MIXES = Lists.newArrayList();


    public static final Potion potion_1 = registerPotion("potion_of_radiation_1", new Potion(new MobEffectInstance(CNEffects.RADIATION.get(), 900)));
    public static final Potion potion_augment_1 = registerPotion("potion_of_radiation_augment_1", new Potion(new MobEffectInstance(CNEffects.RADIATION.get(), 1800)));
    public static final Potion potion_2 = registerPotion("potion_of_radiation_2", new Potion(new MobEffectInstance(CNEffects.RADIATION.get(), 410, 1)));

    private static void addMix(Potion p_43514_, Item p_43515_, Potion p_43516_) {
        POTION_MIXES.add(new PotionBrewing.Mix<>(net.minecraftforge.registries.ForgeRegistries.POTIONS, p_43514_, Ingredient.of(p_43515_), p_43516_));
    }

    public static Potion registerPotion(String name, Potion potion) {
        return Registry.register(BuiltInRegistries.POTION, CreateNuclear.asResource(name), potion);
    }

    public static void registerPotionsRecipes() {
        CNPotions.addMix(Potions.AWKWARD, CNItems.ENRICHED_YELLOWCAKE.get(), CNPotions.potion_1);
        CNPotions.addMix(potion_1, Items.REDSTONE, CNPotions.potion_augment_1);
        CNPotions.addMix(potion_1, Items.GLOWSTONE_DUST, CNPotions.potion_2);


    }
}
