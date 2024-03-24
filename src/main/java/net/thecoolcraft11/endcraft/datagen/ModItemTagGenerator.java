package net.thecoolcraft11.endcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Endcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.IS_STAFF_UPGRADE)
                .add(ModItems.ENDER_UPGRADE_DURABILITY_1.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_2.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_3.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_4.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_5.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_6.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_7.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_7.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_8.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_9.get())
                .add(ModItems.ENDER_UPGRADE_DURABILITY_10.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_1.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_2.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_3.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_4.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_5.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_6.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_7.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_7.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_8.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_9.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_10.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_11.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_12.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_13.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_14.get())
                .add(ModItems.ENDER_UPGRADE_RANGE_15.get())
                .add(ModItems.ENDER_UPGRADE_FALL_1.get())
                .add(ModItems.ENDER_UPGRADE_FALL_2.get())
                .add(ModItems.ENDER_UPGRADE_FALL_3.get())
                .add(ModItems.ENDER_UPGRADE_FALL_4.get());
    }
}
