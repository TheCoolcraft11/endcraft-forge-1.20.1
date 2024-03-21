package net.thecoolcraft11.endcraft.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.custom.*;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Endcraft.MOD_ID);

    public static final RegistryObject<Block> ENDERITE_ORE = registerBlock("enderite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.AMETHYST).destroyTime(10f).explosionResistance(10f)));
    public static final RegistryObject<Block> ENDER_FORGE_CONVERTER = registerBlock("ender_forge_converter",
            () -> new EnderForgeConverterBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).noOcclusion()));
    public static final RegistryObject<Block> ENDER_CHARGER_BLOCK = registerBlock("ender_charger",
            () -> new EnderChargerBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noOcclusion()));
    public static final RegistryObject<Block> ESSENCE_ALTAR = registerBlock("essence_altar",
            () -> new EssenceAltarBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noOcclusion()));
    public static final RegistryObject<Block> ENDERITE_BLOCK = registerBlock("enderite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> VOID_BLOCK = registerBlock("void_block",
            () -> new VoidBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).noOcclusion().noLootTable().noCollission()));
    public static final RegistryObject<Block> VOID_LAYER = registerBlock("void_layer",
            () -> new VoidLayerBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).noOcclusion().noLootTable().noCollission()));
    public static final RegistryObject<Block> END_PEDASTEL = registerBlock("end_pedastel",
            () -> new EndPedastelBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).noOcclusion().noLootTable()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
