package net.nuclearteam.createnuclear;

import com.google.common.collect.Lists;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class CNPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, CreateNuclear.MOD_ID);

    private static final List<PotionBrewing.Mix<Potion>> POTION_MIXES = Lists.newArrayList();

    public static final RegistryObject<Potion> POTION_1 = POTIONS.register("potion_of_radiation_1",
            () -> new Potion(new MobEffectInstance(CNEffects.RADIATION.get(), 900)));
    public static final RegistryObject<Potion> POTION_AUGMENT_1 = POTIONS.register("potion_of_radiation_augment_1",
            () -> new Potion(new MobEffectInstance(CNEffects.RADIATION.get(), 1800)));
    public static final RegistryObject<Potion> POTION_2 = POTIONS.register("potion_of_radiation_2",
            () -> new Potion(new MobEffectInstance(CNEffects.RADIATION.get(), 410, 1)));


    private static void addMix(Potion p_43514_, Item p_43515_, Potion p_43516_) {
        POTION_MIXES.add(new PotionBrewing.Mix<>(net.minecraftforge.registries.ForgeRegistries.POTIONS, p_43514_, Ingredient.of(p_43515_), p_43516_));
    }

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    public static void registerPotionsRecipes() {
        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                Ingredient.of(CNItems.ENRICHED_YELLOWCAKE.get()),
                PotionUtils.setPotion(new ItemStack(Items.POTION), POTION_1.get())
        );
        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), POTION_1.get())),
                Ingredient.of(Items.REDSTONE),
                PotionUtils.setPotion(new ItemStack(Items.POTION), POTION_AUGMENT_1.get())
        );

        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), POTION_1.get())),
                Ingredient.of(Items.GLOWSTONE_DUST),
                PotionUtils.setPotion(new ItemStack(Items.POTION), POTION_2.get())
        );

    }
}
