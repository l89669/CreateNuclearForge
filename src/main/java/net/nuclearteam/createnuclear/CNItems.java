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
