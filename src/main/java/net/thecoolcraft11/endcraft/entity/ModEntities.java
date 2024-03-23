package net.thecoolcraft11.endcraft.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.custom.VoidGhostEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Endcraft.MOD_ID);

    public static final RegistryObject<EntityType<VoidGhostEntity>> VOID_GHOST  = ENTITY_TYPES.register("void_ghost", () -> EntityType.Builder.of(VoidGhostEntity::new, MobCategory.MONSTER).sized(0.5f, 0.5f).build("void_ghost"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
