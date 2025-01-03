package net.nuclearteam.createnuclear.infrastructure.data;

import com.simibubi.create.foundation.data.TagGen.CreateTagsProvider;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.nuclearteam.createnuclear.CNTags.CNBlockTags;
import net.nuclearteam.createnuclear.CNTags.CNEntityTags;
import net.nuclearteam.createnuclear.CreateNuclear;

public class CreateNuclearRegistrateTags {
    public static void addGenerators() {
        CreateNuclear.REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, CreateNuclearRegistrateTags::genBlockTags);
        CreateNuclear.REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, CreateNuclearRegistrateTags::genEntityTags);
    }

    private static void genBlockTags(RegistrateTagsProvider<Block> provIn) {
        CreateTagsProvider<Block> prov = new CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);

        for (CNBlockTags tag : CNBlockTags.values()) {
            if (tag.alwaysDatagen) {
                prov.getOrCreateRawBuilder(tag.tag);
            }
        }
    }

    private static void genEntityTags(RegistrateTagsProvider<EntityType<?>> provIn) {
        CreateTagsProvider<EntityType<?>> prov = new CreateTagsProvider<>(provIn, EntityType::builtInRegistryHolder);

        for (CNEntityTags tag : CNEntityTags.values()) {
            if (tag.alwaysDatagen) {
                prov.getOrCreateRawBuilder(tag.tag);
            }
        }
    }
}
