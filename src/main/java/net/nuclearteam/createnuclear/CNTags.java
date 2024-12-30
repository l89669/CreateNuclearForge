package net.nuclearteam.createnuclear;

import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import static net.nuclearteam.createnuclear.CNTags.NameSpace.MOD;

import java.util.Collections;

public class CNTags {
    public static <T> TagKey<T> optionalTag(IForgeRegistry<T> registry, ResourceLocation id) {
        return registry.tags().createOptionalTagKey(id, Collections.emptySet());
    }

    public static <T> TagKey<T> forgeTag(IForgeRegistry<T> registry, String path) {
        return optionalTag(registry, new ResourceLocation("forge", path));
    }

    public static TagKey<Block> forgeBlockTag(String path) {
        return forgeTag(ForgeRegistries.BLOCKS, path);
    }

    public static TagKey<Item> forgeItemTag(String path) {
        return forgeTag(ForgeRegistries.ITEMS, path);
    }

    public static TagKey<Fluid> forgeFluidTag(String path) {
        return forgeTag(ForgeRegistries.FLUIDS, path);
    }

    public enum NameSpace {
        MOD(CreateNuclear.MOD_ID, false, true),
        CREATE("create"),
        FORGE("forge"),
        MINECRAFT("minecraft")
        ;

        public final String id;
        public final boolean optionalDefault;
        public final boolean alwayDatagenDefault;

        NameSpace(String id) {
            this(id, true, false);
        }

        NameSpace(String id, boolean optionalDefault, boolean alwayDatagenDefault) {
            this.id = id;
            this.optionalDefault = optionalDefault;
            this.alwayDatagenDefault = alwayDatagenDefault;
        }
    }

    public enum CNBlockTags {
        ;

        public final TagKey<Block> tag;
        public final boolean alwaysDatagen;

        CNBlockTags() {
            this(MOD);
        }

        CNBlockTags(NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwayDatagenDefault);
        }

        CNBlockTags(NameSpace nameSpace, String path) {
            this(nameSpace, path, nameSpace.optionalDefault, nameSpace.alwayDatagenDefault);
        }

        CNBlockTags(NameSpace nameSpace, boolean optional, boolean alwayDatagenDefault) {
            this(nameSpace, null, optional, alwayDatagenDefault);
        }

        CNBlockTags(NameSpace nameSpace, String path, boolean optional, boolean alwayDatagenDefault) {
            ResourceLocation id = new ResourceLocation(nameSpace.id, path == null ? Lang.asId(name()) : path);
            if (optional) {
                tag = optionalTag(ForgeRegistries.BLOCKS, id);
            } else {
                tag = BlockTags.create(id);
            }
            this.alwaysDatagen = alwayDatagenDefault;
        }

        @SuppressWarnings("deprecation")
        public boolean matches(Block block) {
            return block.builtInRegistryHolder().is(tag);
        }

        public boolean matches(ItemStack stack) {
            return stack != null && stack.getItem() instanceof BlockItem blockItem && matches(blockItem.getBlock());
        }

        public boolean matches(BlockState state) {
            return state.is(tag);
        }

        private static void init() {}
    }


    public static void init() {
        CNBlockTags.init();
    }
}
