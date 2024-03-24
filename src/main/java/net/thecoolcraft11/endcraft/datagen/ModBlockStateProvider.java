package net.thecoolcraft11.endcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Endcraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ENDERITE_ORE);
        simpleBlockWithItem(ModBlocks.ENDER_FORGE_CONVERTER.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/ender_forge_converter")));
        simpleBlockWithItem(ModBlocks.ENDER_CHARGER_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/ender_charger")));
        simpleBlockWithItem(ModBlocks.ESSENCE_ALTAR.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/essence_altar")));
        blockWithItem(ModBlocks.ENDERITE_BLOCK);
        blockWithItem(ModBlocks.VOID_BLOCK);
        blockWithItem(ModBlocks.INFECTED_DIRT);
        blockWithItem(ModBlocks.INFECTED_STONE);
        simpleBlockWithItem(ModBlocks.INFECTED_GRASS.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/infected_grass")));
        simpleBlockWithItem(ModBlocks.MOD_TABLE_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/mod_table")));
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
