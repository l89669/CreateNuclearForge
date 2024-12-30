package net.nuclearteam.createnuclear;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.model.generators.ModelFile;
import net.nuclearteam.createnuclear.content.enriching.fire.EnrichingFireBlock;

public class CNBlocks {

    public static final BlockEntry<EnrichingFireBlock> ENRICHING_FIRE =
            CreateNuclear.REGISTRATE.block("enriching_fire", properties -> new EnrichingFireBlock(properties, 3.0f))
                    .initialProperties(() -> Blocks.FIRE)
                    .properties(BlockBehaviour.Properties::replaceable)
                    .properties(BlockBehaviour.Properties::noCollission)
                    .properties(BlockBehaviour.Properties::noOcclusion)
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

    public static void register() {
        CreateNuclear.LOGGER.info("Registering ModBlocks for " + CreateNuclear.MOD_ID);
    }
}
