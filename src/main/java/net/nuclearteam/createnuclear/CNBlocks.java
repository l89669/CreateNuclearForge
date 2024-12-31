package net.nuclearteam.createnuclear;

import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.nuclearteam.createnuclear.content.enriching.campfire.EnrichingCampfireBlock;
import net.nuclearteam.createnuclear.content.enriching.fire.EnrichingFireBlock;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class CNBlocks {

    public static final BlockEntry<EnrichingFireBlock> ENRICHING_FIRE =
            CreateNuclear.REGISTRATE.block("enriching_fire", properties -> new EnrichingFireBlock(properties, 3.0f))
                    .initialProperties(() -> Blocks.FIRE)
                    .properties(Properties::replaceable)
                    .properties(Properties::noCollission)
                    .properties(Properties::noOcclusion)
                    .properties(EnrichingFireBlock.getLight())
                    .tag(CNTags.CNBlockTags.FAN_PROCESSING_CATALYSTS_ENRICHED.tag, CNTags.CNBlockTags.FIRE.tag, CNTags.CNBlockTags.DRAGON_TRANSPARENT.tag)
                    .loot((lt, b) -> lt.add(b, BlockLootSubProvider.noDrop()))
                    .addLayer(() -> RenderType::cutout)
                    .blockstate((c, p) -> {
                        String baseFolder = "block/enriching/fire/";
                        ModelFile Floor0 = p.models().getExistingFile(p.modLoc(baseFolder + "floor0"));
                        ModelFile Floor1 = p.models().getExistingFile(p.modLoc(baseFolder + "floor1"));
                        ModelFile Side0 = p.models().getExistingFile(p.modLoc(baseFolder + "side0"));
                        ModelFile Side1 = p.models().getExistingFile(p.modLoc(baseFolder + "side1"));
                        ModelFile SideAlt0 = p.models().getExistingFile(p.modLoc(baseFolder + "side_alt0"));
                        ModelFile SideAlt1 = p.models().getExistingFile(p.modLoc(baseFolder + "side_alt1"));

                        p.getMultipartBuilder(c.get())
                                .part()
                                .modelFile(Floor0)
                                .nextModel()
                                .modelFile(Floor1)
                                .addModel()
                                .end()
                                .part()
                                .modelFile(Side0)
                                .nextModel()
                                .modelFile(Side1)
                                .nextModel()
                                .modelFile(SideAlt0)
                                .nextModel()
                                .modelFile(SideAlt1)
                                .addModel()
                                .end()
                                .part()
                                .modelFile(Side0).rotationY(90).nextModel()
                                .modelFile(Side1).rotationY(90).nextModel()
                                .modelFile(SideAlt0).rotationY(90).nextModel()
                                .modelFile(SideAlt1).rotationY(90)
                                .addModel()
                                .end()
                                .part()
                                .modelFile(Side0).rotationY(180).nextModel()
                                .modelFile(Side1).rotationY(180).nextModel()
                                .modelFile(SideAlt0).rotationY(180).nextModel()
                                .modelFile(SideAlt1).rotationY(180)
                                .addModel()
                                .end()
                                .part()
                                .modelFile(Side0).rotationY(270).nextModel()
                                .modelFile(Side1).rotationY(270).nextModel()
                                .modelFile(SideAlt0).rotationY(270).nextModel()
                                .modelFile(SideAlt1).rotationY(270)
                                .addModel()
                                .end();
                    })
                    .register()
            ;

    public static final BlockEntry<EnrichingCampfireBlock> ENRICHING_CAMPFIRE =
            CreateNuclear.REGISTRATE.block("enriching_campfire", properties -> new EnrichingCampfireBlock(properties, true, 5))
                    .properties(p -> p.mapColor(MapColor.PODZOL))
                    .properties(p -> p.instrument(NoteBlockInstrument.BASS))
                    .properties(p -> p.strength(2.0F))
                    .properties(p -> p.sound(SoundType.WOOD))
                    .properties(Properties::noOcclusion)
                    .properties(Properties::ignitedByLava)
                    //.properties(Properties::replaceable)
                    .addLayer(() -> RenderType::cutout)
                    .transform(axeOrPickaxe())
                    .tag(CNTags.CNBlockTags.CAMPFIRE.tag, CNTags.CNBlockTags.ALL_CAMPFIRES.tag)
                    //.loot((lt, b) -> lt.add(b, RegistrateBlockLootTables.createSilkTouchDispatchTable(b, lt.applyExplosionDecay(b, LootItem.lootTableItem(CNBlocks.ENRICHING_SOUL_SOIL)))))
                    .blockstate((c, p) ->
                        p.getVariantBuilder(c.getEntry()).forAllStatesExcept(state -> {
                            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                            return ConfiguredModel.builder()
                                    .modelFile(p.models().getExistingFile(p.modLoc("block/enriching/campfire/" + (state.getValue(EnrichingCampfireBlock.LIT) ? "block" : "block_off"))))
                                    .uvLock(false)
                                    .rotationY(switch (facing) {
                                        case NORTH -> 180;
                                        case SOUTH -> 0;
                                        case WEST -> 90;
                                        case EAST -> 270;
                                        default -> 0;
                                    })
                                    .build();
                            }, BlockStateProperties.SIGNAL_FIRE, BlockStateProperties.WATERLOGGED
                        )
                    )
                    .item()
                    .model(AssetLookup.customBlockItemModel("enriching", "campfire", "block"))
                    .build()
                    .tag(CNTags.CNBlockTags.FAN_PROCESSING_CATALYSTS_ENRICHED.tag)
                    .register();


    public static void register() {
        CreateNuclear.LOGGER.info("Registering ModBlocks for " + CreateNuclear.MOD_ID);
    }
}
