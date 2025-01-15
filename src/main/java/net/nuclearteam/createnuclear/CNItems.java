package net.nuclearteam.createnuclear;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.nuclearteam.createnuclear.content.equipment.cloth.ClothItem;
import net.nuclearteam.createnuclear.content.equipment.cloth.ClothItem.DyeItemList;
import net.nuclearteam.createnuclear.foundation.utility.TextUtils;

import java.util.function.Supplier;

public class CNItems {

    public static final ItemEntry<Item>
        YELLOWCAKE = CreateNuclear.REGISTRATE
            .item("yellowcake", Item::new)
            .register(),

        RAW_LEAD = CreateNuclear.REGISTRATE
                .item("raw_lead", Item::new)
                .tag(CNTags.forgeItemTag("raw_ores"), CNTags.forgeItemTag("raw_materials"), CNTags.forgeItemTag("raw_materials/lead"))
                .register(),

        RAW_URANIUM = CreateNuclear.REGISTRATE
                .item("raw_uranium", Item::new)
                .tag(CNTags.forgeItemTag("raw_ores"), CNTags.forgeItemTag("raw_materials"), CNTags.forgeItemTag("raw_materials/uranium"))
                .register(),

        URANIUM_POWDER = CreateNuclear.REGISTRATE
                .item("uranium_powder", Item::new)
                .register(),

        STEEL_INGOT = CreateNuclear.REGISTRATE
                .item("steel_ingot", Item::new)
                .tag(CNTags.forgeItemTag("ingots"), CNTags.forgeItemTag("ingots/steel"))
                .register(),

        COAL_DUST = CreateNuclear.REGISTRATE
                .item("coal_dust", Item::new)
                .tag(CNTags.forgeItemTag("dusts"), CNTags.forgeItemTag("coal_dusts"))
                .register(),

        GRAPHITE_ROD = CreateNuclear.REGISTRATE
                .item("graphite_rod", Item::new)
                .tag(CNTags.forgeItemTag("rods"), CNTags.CNItemTags.COOLER.tag)
                .register(),

        STEEL_NUGGET = CreateNuclear.REGISTRATE
                .item("steel_nugget", Item::new)
                .tag(CNTags.forgeItemTag("nuggets"), CNTags.forgeItemTag("nuggets/steel"))
                .register(),

    URANIUM_ROD = CreateNuclear.REGISTRATE
            .item("uranium_rod", Item::new)
            .tag(CNTags.forgeItemTag("rods"), CNTags.CNItemTags.FUEL.tag)
            .register(),

    LEAD_INGOT = CreateNuclear.REGISTRATE
            .item("lead_ingot", Item::new)
            .tag(CNTags.forgeItemTag("ingots"), CNTags.forgeItemTag("ingots/lead"))
            .register(),

    LEAD_NUGGET = CreateNuclear.REGISTRATE
            .item("lead_nugget", Item::new)
            .tag(CNTags.forgeItemTag("nuggets"), CNTags.forgeItemTag("nuggets/lead"))
            .register(),

    GRAPHENE = CreateNuclear.REGISTRATE
            .item("graphene", Item::new)
            .register(),

    ENRICHED_YELLOWCAKE = CreateNuclear.REGISTRATE
            .item("enriched_yellowcake", Item::new)
            .register()
    ;

    public static final ItemEntry<ForgeSpawnEggItem> SPAWN_WOLF = registerSpawnEgg("wolf_irradiated_spawn_egg", CNEntityType.IRRADIATED_WOLF, 0x42452B,0x4C422B, "Irradiated Wolf Spawn Egg");
    public static final ItemEntry<ForgeSpawnEggItem> SPAWN_CAT = registerSpawnEgg("cat_irradiated_spawn_egg", CNEntityType.IRRADIATED_CAT, 0x382C19, 0x742728, "Irradiated Cat Spawn Egg");
    public static final ItemEntry<ForgeSpawnEggItem> SPAWN_CHICKEN = registerSpawnEgg("chicken_irradiated_spawn_egg", CNEntityType.IRRADIATED_CHICKEN, 0x6B9455, 0x95393C, "Irradiated Chicken Spawn Egg");


    private static ItemEntry<ForgeSpawnEggItem> registerSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> entity, int backgroundColor, int highlightColor, String nameItems) {
        return CreateNuclear.REGISTRATE
                .item(name, p -> new ForgeSpawnEggItem(entity, backgroundColor, highlightColor, p))
                .lang(nameItems)
                .model((c, p) -> p.withExistingParent(c.getName(), new ResourceLocation("item/template_spawn_egg")))
                .register();

    }

    public static final DyeItemList<ClothItem> CLOTHS = new ClothItem.DyeItemList<>(color -> {

        String colorName = color.getSerializedName();

        return CreateNuclear.REGISTRATE.item(colorName+ "_cloth", p -> new ClothItem(p, color))
                .tag(CNTags.CNItemTags.CLOTH.tag)
                .lang(TextUtils.titleCaseConversion(color.getName()) + " Cloth")
                .model((c, p) -> p.generated(c, CreateNuclear.asResource("item/cloth/" + colorName + "_cloth")))
                .register();
    });


    public static void register() {}
}
