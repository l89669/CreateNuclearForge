package net.nuclearteam.createnuclear;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.nuclearteam.createnuclear.content.enriching.campfire.EnrichingCampfireBlockEntity;

public class CNBlockEntityTypes {
    public static final BlockEntityEntry<EnrichingCampfireBlockEntity> ENRICHING_CAMPFIRE_BLOCK =
            CreateNuclear.REGISTRATE.blockEntity("enriching_campfire_block", EnrichingCampfireBlockEntity::new)
                    .validBlock(CNBlocks.ENRICHING_CAMPFIRE)
                    .register();

    public static void register() {}
}
