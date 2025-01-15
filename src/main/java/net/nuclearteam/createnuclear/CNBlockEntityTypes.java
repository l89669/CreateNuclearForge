package net.nuclearteam.createnuclear;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.nuclearteam.createnuclear.content.enriching.campfire.EnrichingCampfireBlockEntity;
import net.nuclearteam.createnuclear.content.multiblock.casing.ReactorCasingEntity;
import net.nuclearteam.createnuclear.content.multiblock.core.ReactorCoreEntity;
import net.nuclearteam.createnuclear.content.multiblock.input.ReactorInputEntity;

public class CNBlockEntityTypes {
    public static final BlockEntityEntry<EnrichingCampfireBlockEntity> ENRICHING_CAMPFIRE_BLOCK =
            CreateNuclear.REGISTRATE.blockEntity("enriching_campfire_block", EnrichingCampfireBlockEntity::new)
                    .validBlock(CNBlocks.ENRICHING_CAMPFIRE)
                    .register();

    public static final BlockEntityEntry<ReactorCasingEntity> REACTOR_CASING =
            CreateNuclear.REGISTRATE.blockEntity("reactor_casing", ReactorCasingEntity::new)
                    .validBlocks(CNBlocks.REACTOR_CASING)
                    .register();

    public static final BlockEntityEntry<ReactorCoreEntity> REACTOR_CORE =
            CreateNuclear.REGISTRATE.blockEntity("reactor_core", ReactorCoreEntity::new)
                    .validBlocks(CNBlocks.REACTOR_CORE)
                    .register();

    public static final BlockEntityEntry<ReactorInputEntity> REACTOR_INPUT =
            CreateNuclear.REGISTRATE.blockEntity("reactor_input", ReactorInputEntity::new)
                    .validBlocks(CNBlocks.REACTOR_INPUT)
                    .register();

    public static void register() {}
}
