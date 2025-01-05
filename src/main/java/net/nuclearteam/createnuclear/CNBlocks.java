package net.nuclearteam.createnuclear;

import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.nuclearteam.createnuclear.content.enriching.campfire.EnrichingCampfireBlock;
import net.nuclearteam.createnuclear.content.enriching.fire.EnrichingFireBlock;
import net.nuclearteam.createnuclear.content.multiblock.casing.ReactorCasing;
import net.nuclearteam.createnuclear.CNTags.CNBlockTags;
import net.nuclearteam.createnuclear.content.multiblock.core.ReactorCore;
import net.nuclearteam.createnuclear.content.multiblock.gauge.ReactorGauge;
import net.nuclearteam.createnuclear.content.multiblock.gauge.ReactorGaugeItem;
import net.nuclearteam.createnuclear.content.multiblock.reactorCoolingFrame.ReactorCoolingFrame;
import net.nuclearteam.createnuclear.content.multiblock.reinforced.ReinforcedGlassBlock;

import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static net.nuclearteam.createnuclear.content.multiblock.gauge.ReactorGauge.Part.*;


public class CNBlocks {

    public static final BlockEntry<ReactorCasing> REACTOR_CASING =
            CreateNuclear.REGISTRATE.block("reactor_casing", properties -> new ReactorCasing(properties, ReactorCasing.TypeBlock.CASING))
                    .properties(p -> p.explosionResistance(3F)
                            .destroyTime(4F))
                    .blockstate((c,p) ->
                            p.getVariantBuilder(c.getEntry()).forAllStates((state) -> ConfiguredModel.builder()
                                    .modelFile(p.models().getExistingFile(p.modLoc("block/reactor/casing/block")))
                                    .build()))
                    .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(CNSpriteShifts.REACTOR_CASING)))
                    .onRegister(casingConnectivity((block,cc) -> cc.makeCasing(block, CNSpriteShifts.REACTOR_CASING)))
                    .tag(CNBlockTags.NEEDS_DIAMOND_TOOL.tag)
                    .simpleItem()
                    .transform(pickaxeOnly())
                    .register();

    public static final BlockEntry<ReactorCore> REACTOR_CORE =
            CreateNuclear.REGISTRATE.block("reactor_core", ReactorCore::new)
                    .properties(p -> p.explosionResistance(6F))
                    .properties(p -> p.destroyTime(4F))
                    .tag(CNBlockTags.NEEDS_DIAMOND_TOOL.tag)
                    .blockstate((c, p) ->
                        p.getVariantBuilder(c.getEntry())
                            .forAllStates(state -> ConfiguredModel.builder()
                                .modelFile(p.models().getExistingFile(p.modLoc("block/reactor/core/block")))
                                .uvLock(false)
                                .build()
                            )
                    )
                    .transform(pickaxeOnly())
                    .simpleItem()
                    .register();

    public static final BlockEntry<ReactorGauge> REACTOR_GAUGE =
            CreateNuclear.REGISTRATE.block("reactor_gauge", ReactorGauge::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.explosionResistance(3F).destroyTime(2F))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(pickaxeOnly())
                    .tag(CNBlockTags.NEEDS_DIAMOND_TOOL.tag)
                    .blockstate((c, p) ->
                        p.getVariantBuilder(c.getEntry())
                        .forAllStatesExcept(state -> {
                            ReactorGauge.Part part = state.getValue(ReactorGauge.PART);
                            String baseFile = "block/reactor/gauge/gauge_";
                            ModelFile start = p.models().getExistingFile(p.modLoc(baseFile + "top"));
                            ModelFile middle = p.models().getExistingFile(p.modLoc(baseFile + "middle"));
                            ModelFile bottom = p.models().getExistingFile(p.modLoc(baseFile + "bottom"));
                            ModelFile none = p.models().getExistingFile(p.modLoc(baseFile + "none"));
                            return ConfiguredModel.builder().modelFile(switch (part) {
                                case START -> start;
                                case MIDDLE -> middle;
                                case END -> bottom;
                                default -> none;
                            })
                            .uvLock(false)
                            .build();
                        })
                    )
                    .item(ReactorGaugeItem::new)
                    .model(AssetLookup.customBlockItemModel("reactor", "gauge", "item"))
                    .build()
                    .register();

    public static final BlockEntry<ReactorCoolingFrame> REACTOR_COOLING_FRAME =
            CreateNuclear.REGISTRATE.block("reactor_cooling_frame", ReactorCoolingFrame::new)
                    .properties(p -> p.explosionResistance(3F)
                            .destroyTime(4F))
                    .blockstate((c,p) ->
                            p.getVariantBuilder(c.getEntry()).forAllStates((state) -> ConfiguredModel.builder()
                                    .modelFile(p.models().getExistingFile(p.modLoc("block/reactor/cooling_frame/block")))
                                    .build()))
                    .tag(CNBlockTags.NEEDS_DIAMOND_TOOL.tag)
                    .simpleItem()
                    .transform(pickaxeOnly())
                    .register();

    public static final BlockEntry<ReinforcedGlassBlock> REINFORCED_GLASS = CreateNuclear.REGISTRATE
            .block("reinforced_glass", ReinforcedGlassBlock::new)
            .initialProperties(() -> Blocks.GLASS)
            .properties(p -> p.explosionResistance(1200F).destroyTime(2F))
            .loot(RegistrateBlockLootTables::dropWhenSilkTouch)
            .tag(CNTags.forgeBlockTag("glass"), BlockTags.IMPERMEABLE)
            .blockstate((c, p) -> p.getVariantBuilder(c.getEntry())
                .forAllStates(state -> ConfiguredModel.builder().modelFile(p.models()
                    .withExistingParent("reinforced_glass",new ResourceLocation("block/cube_all"))
                    .texture("all", p.modLoc("block/reactor/reinforced/glass"))
                    .texture("particle", p.modLoc("block/reactor/reinforced/glass"))
                )
                .build())
            )
            .addLayer(() -> RenderType::translucent)
            .item()
            .tag(CNTags.forgeItemTag("glass"))
            .build()
            .register();



    public static final BlockEntry<EnrichingFireBlock> ENRICHING_FIRE =
            CreateNuclear.REGISTRATE.block("enriching_fire", properties -> new EnrichingFireBlock(properties, 3.0f))
                    .initialProperties(() -> Blocks.FIRE)
                    .properties(Properties::replaceable)
                    .properties(Properties::noCollission)
                    .properties(Properties::noOcclusion)
                    .properties(EnrichingFireBlock.getLight())
                    .tag(CNBlockTags.FAN_PROCESSING_CATALYSTS_ENRICHED.tag)
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
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(axeOrPickaxe())
                    .tag(CNBlockTags.ALL_CAMPFIRES.tag)
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
