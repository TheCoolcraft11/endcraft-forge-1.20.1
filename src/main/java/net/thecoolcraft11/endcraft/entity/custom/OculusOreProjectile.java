package net.thecoolcraft11.endcraft.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.ModEntities;
import net.thecoolcraft11.endcraft.item.ModItems;

import java.util.Objects;
import java.util.Random;

public class OculusOreProjectile extends ThrowableItemProjectile {
    public OculusOreProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public OculusOreProjectile(Level pLevel) {
        super(ModEntities.OCULUS_ORE_PROJECTILE.get(),pLevel);
    }
    public OculusOreProjectile(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.OCULUS_ORE_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.OCULUS_ORE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(!this.level().isClientSide) {
                this.level().broadcastEntityEvent(this, (byte) 3);
                if(this.getPersistentData().getBoolean("hasEntity")) {
                    EntityType<?> entity = EntityType.byString(this.getPersistentData().getString("entity")).orElse(null);
                    if (entity != null) {
                        if (this.level().getServer() != null) {
                            entity.spawn(Objects.requireNonNull(this.level().getServer().getLevel(this.level().dimension())), this.getOnPos(), MobSpawnType.SPAWN_EGG);
                        }
                    } else {
                        this.level().addParticle(ParticleTypes.EXPLOSION, this.getBlockX(), this.getBlockY(), this.getBlockZ(), 0, 0, 0);
                        this.discard();
                    }
                }else {
                    if(this.getServer() != null) {
                    if(this.level().getBlockState(pResult.getBlockPos()).getBlock() == Blocks.LIGHTNING_ROD && this.level().isThundering()) {
                        EntityType entityType = EntityType.LIGHTNING_BOLT;
                        entityType.spawn(this.level().getServer().getLevel(this.level().dimension()),pResult.getBlockPos(), MobSpawnType.NATURAL);
                        ItemEntity entity = EntityType.ITEM.create(this.level());
                        ItemStack itemStack = ModItems.OCULUS_ORE.get().getDefaultInstance();
                        itemStack.getOrCreateTag().putBoolean("hasEntity", true);
                        itemStack.getOrCreateTag().putInt("damage", this.getPersistentData().getInt("damage") + 1);
                        itemStack.getOrCreateTag().putInt("maxDamage", this.getPersistentData().getInt("maxDamage"));
                        itemStack.getOrCreateTag().putString("entity", EntityType.LIGHTNING_BOLT.toString().replace("entity.", "").replace(".", ":"));
                        entity.setItem(itemStack);
                        entity.setInvulnerable(true);
                        Random random = new Random();
                        entity.teleportTo(pResult.getBlockPos().getX(), pResult.getBlockPos().getY()+ 1, pResult.getBlockPos().getZ());
                        entity.setDeltaMovement(random.nextDouble(0.6) - 0.25, random.nextDouble(1.0), random.nextDouble(0.25) - 0.25);
                        this.level().getServer().getLevel(this.level().dimension()).addFreshEntity(entity);
                        this.discard();
                        }
                    }
                    ItemEntity entity = EntityType.ITEM.create(this.level());
                    ItemStack itemStack = ModItems.OCULUS_ORE.get().getDefaultInstance();
                    itemStack.getOrCreateTag().putString("entity", this.getPersistentData().getString("entity"));
                    itemStack.getOrCreateTag().putInt("damage", this.getPersistentData().getInt("damage") + 1);
                    itemStack.getOrCreateTag().putInt("maxDamage", this.getPersistentData().getInt("maxDamage"));
                    entity.setItem(itemStack);
                    entity.teleportTo(this.getOnPos().getX(), this.getOnPos().getY(), this.getOnPos().getZ());
                    if (this.getServer() != null) {
                        this.level().getServer().getLevel(this.level().dimension()).addFreshEntity(entity);
                    }
                }

            this.discard();
        }
        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Player player = (Player) this.getOwner();
        if(!this.getPersistentData().getBoolean("hasEntity")) {
            ItemEntity entity = EntityType.ITEM.create(this.level());
            ItemStack itemStack = ModItems.OCULUS_ORE.get().getDefaultInstance();
            itemStack.getOrCreateTag().putBoolean("hasEntity", true);
            itemStack.getOrCreateTag().putString("entity", pResult.getEntity().getType().toString().replace("entity.", "").replace(".", ":"));
            itemStack.getOrCreateTag().putInt("damage", this.getPersistentData().getInt("damage") + 1);
            itemStack.getOrCreateTag().putInt("maxDamage", this.getPersistentData().getInt("maxDamage"));
            entity.setItem(itemStack);
            entity.teleportTo(pResult.getEntity().getX(), pResult.getEntity().getY(), pResult.getEntity().getZ());
            if (this.level().getServer() != null) {
                this.level().getServer().getLevel(this.level().dimension()).addFreshEntity(entity);
            }
            this.discard();
        }
        super.onHitEntity(pResult);
    }
}
