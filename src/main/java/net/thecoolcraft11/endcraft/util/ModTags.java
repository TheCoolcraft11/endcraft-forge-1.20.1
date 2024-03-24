package net.thecoolcraft11.endcraft.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.thecoolcraft11.endcraft.Endcraft;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ENDERITE_TOOL = tag("needs_enderite_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Endcraft.MOD_ID, name));

        }
    }

    public static class Items {
        public static final TagKey<Item> IS_STAFF_UPGRADE = tag("is_staff_upgrade");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Endcraft.MOD_ID, name));
        }
    }
}