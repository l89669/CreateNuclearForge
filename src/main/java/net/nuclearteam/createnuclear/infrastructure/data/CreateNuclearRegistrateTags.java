package net.nuclearteam.createnuclear.infrastructure.data;

import com.simibubi.create.foundation.data.TagGen.CreateTagsProvider;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.nuclearteam.createnuclear.CNTags.CNEntityTags;
import net.nuclearteam.createnuclear.CreateNuclear;

public class CreateNuclearRegistrateTags {
    public static void addGenerators() {
        CreateNuclear.REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, CreateNuclearRegistrateTags::genEntityTags);
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
