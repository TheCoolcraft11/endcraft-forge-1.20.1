package net.thecoolcraft11.endcraft.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Endcraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<EnderForgeConverterBlockEntity>> ENDER_FORGE_BE = BLOCK_ENTITIES.register("ender_forge_be",() ->BlockEntityType.Builder.of(EnderForgeConverterBlockEntity::new, ModBlocks.ENDER_FORGE_CONVERTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<EnderChargerBlockEntity>> ENDER_CHARGER_BE = BLOCK_ENTITIES.register("ender_charger_be",() ->BlockEntityType.Builder.of(EnderChargerBlockEntity::new, ModBlocks.ENDER_CHARGER_BLOCK.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
