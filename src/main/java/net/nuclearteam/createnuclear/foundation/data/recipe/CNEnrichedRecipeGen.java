package net.nuclearteam.createnuclear.foundation.data.recipe;

// A RAJOUTER QUAND LE FAN SERA PRET, IL MANQUE CNRecipeTypes
//import com.simibubi.create.foundation.utility.RegisteredObjects;
//import net.minecraft.data.PackOutput;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.level.ItemLike;
//import net.nuclearteam.createnuclear.CNBlocks;
//import net.nuclearteam.createnuclear.CNItems;
//import net.nuclearteam.createnuclear.CreateNuclear;
//
//import java.util.function.Supplier;
//
//public class CNEnrichedRecipeGen extends CNProcessingRecipeGen {
//
//    GeneratedRecipe
//            ENRICHING_CAMPFIRES = convert(Items.CAMPFIRE, CNBlocks.ENRICHING_CAMPFIRE),
//            ENRICHED_YELLOWCAKE = convert(() -> Ingredient.of(CNItems.YELLOWCAKE), () -> CNItems.ENRICHED_YELLOWCAKE)
//                    ;
//
//
//
//
//    public GeneratedRecipe convert(ItemLike input, ItemLike result) {
//        return convert(() -> Ingredient.of(input), () -> result);
//    }
//
//    public GeneratedRecipe convert(Supplier<Ingredient> input, Supplier<ItemLike> result) {
//        return create(
//                CreateNuclear.asResource(
//                        RegisteredObjects.getKeyOrThrow(
//                                        result.get().asItem()
//                                )
//                                .getPath()
//                ),
//                p -> p.withItemIngredients(input.get()).output(result.get()));
//    }
//
//
//    public CNEnrichedRecipeGen(PackOutput generator) {
//        super(generator);
//    }
//
//    @Override
//    protected CNRecipeTypes getRecipeType() {
//        return CNRecipeTypes.ENRICHED;
//    }
//}
