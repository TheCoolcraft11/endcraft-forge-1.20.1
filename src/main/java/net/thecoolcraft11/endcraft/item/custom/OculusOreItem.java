package net.thecoolcraft11.endcraft.item.custom;

import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.entity.custom.OculusOreProjectile;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class OculusOreItem extends Item {
    public OculusOreItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (!pPlayer.getMainHandItem().getOrCreateTag().getBoolean("hasEntity")) {
            pPlayer.getMainHandItem().getOrCreateTag().putString("entity", pInteractionTarget.getType().toString().replace("entity.", "").replace(".", ":"));
            pPlayer.getMainHandItem().getOrCreateTag().putBoolean("hasEntity", true);
            return InteractionResult.SUCCESS;
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getItemInHand().getOrCreateTag().getBoolean("hasEntity")) {
            EntityType<?> entity = EntityType.byString(pContext.getItemInHand().getOrCreateTag().getString("entity")).orElse(null);
            if(!(entity == null)) {
                damage(pContext.getPlayer().getMainHandItem(), 1);
                if(pContext.getLevel().getServer() != null) {
                    entity.spawn(Objects.requireNonNull(pContext.getLevel().getServer().getLevel(pContext.getLevel().dimension())), pContext.getClickedPos(), MobSpawnType.SPAWN_EGG);
                }else if(Minecraft.getInstance().getSingleplayerServer() != null) {
                    entity.spawn(Objects.requireNonNull(Minecraft.getInstance().getSingleplayerServer().getLevel(pContext.getLevel().dimension())), pContext.getClickedPos().above(1), MobSpawnType.SPAWN_EGG);
                }
            }

        }
        return super.useOn(pContext);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        addDamageTags(pStack);
        if(!hasDurability(pStack)) {
            pStack.shrink(1);
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        if(pStack.getOrCreateTag().getBoolean("hasEntity")) {
            if(EntityType.byString(pStack.getOrCreateTag().getString("entity")).get() == EntityType.LIGHTNING_BOLT) {
                return 0xFFF600;
            }
            if(EntityType.byString(pStack.getOrCreateTag().getString("entity")).get() == EntityType.TNT) {
                return 0xFF0000;
            }
            EntityType<?> entity = EntityType.byString(pStack.getOrCreateTag().getString("entity")).orElse(null);
            if(SpawnEggItem.byId(entity) != null) {
                return SpawnEggItem.byId(entity).getColor(0);
            }
        }
        return super.getBarColor(pStack);
    }

    @Override
    public Component getName(ItemStack stack) {
        if(stack.getOrCreateTag().getBoolean("hasEntity")) {
            EntityType<?> entity = EntityType.byString(stack.getOrCreateTag().getString("entity")).orElse(null);
            if(entity != null) {
                return (Component.translatable("item.endcraft.oculus_ore").copy().append(" [").append(Component.translatable(entity.toString().replace(":", "."))).append("]").append("x").append(String.valueOf(stack.getOrCreateTag().getInt("maxDamage") - stack.getOrCreateTag().getInt("damage"))));
            }
        }
        return super.getName(stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        EntityType<?> entity = EntityType.byString(pStack.getOrCreateTag().getString("entity")).orElse(null);
        if (entity != null) {
            pTooltipComponents.add(Component.translatable(entity.toString().replace(":", ".")));
        }else {
            pTooltipComponents.add(Component.literal(""));
        }
        pTooltipComponents.add(Component.translatable("item.endcraft.usedleft").append(" ").append(String.valueOf(pStack.getOrCreateTag().getInt("maxDamage") - pStack.getOrCreateTag().getInt("damage"))).append("/").append(String.valueOf(pStack.getOrCreateTag().getInt("maxDamage"))));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public void damage(ItemStack itemStack, int damage) {
        itemStack.getOrCreateTag().putInt("damage", itemStack.getOrCreateTag().getInt("damage")+ damage);
}
public void setMaxDamage(ItemStack itemStack, int maxDamage) {
        itemStack.getOrCreateTag().putInt("maxDamage", maxDamage);
}
private boolean hasDurability(ItemStack itemStack) {
        if(itemStack.getOrCreateTag().contains("maxDamage") && itemStack.getOrCreateTag().contains("damage")) {
            return itemStack.getOrCreateTag().getInt("damage") < itemStack.getOrCreateTag().getInt("maxDamage");
        }
        return true;
}
private void addDamageTags(ItemStack itemStack) {
        if(!itemStack.getOrCreateTag().contains("maxDamage")) {
            itemStack.getOrCreateTag().putInt("maxDamage", itemStack.getMaxDamage());
        }
        if(!itemStack.getOrCreateTag().contains("damage")) {
            itemStack.getOrCreateTag().putInt("damage", 0);
        }
}
    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return (pStack.getOrCreateTag().getBoolean("hasEntity"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            if(hasDurability(itemStack)) {
                if(itemStack.getOrCreateTag().getBoolean("hasEntity")) {
                    pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
                    damage(itemStack, 1);
                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    OculusOreProjectile oculusOreProjectile = new OculusOreProjectile(pLevel, pPlayer);
                    oculusOreProjectile.setItem(itemStack);
                    oculusOreProjectile.getPersistentData().putString("entity", itemStack.getOrCreateTag().getString("entity"));
                    oculusOreProjectile.getPersistentData().putBoolean("hasEntity", itemStack.getOrCreateTag().getBoolean("hasEntity"));
                    oculusOreProjectile.getPersistentData().putInt("damage", itemStack.getOrCreateTag().getInt("damage"));
                    oculusOreProjectile.getPersistentData().putInt("maxDamage", itemStack.getOrCreateTag().getInt("maxDamage"));
                    oculusOreProjectile.setOwner(pPlayer);
                    oculusOreProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                    pLevel.addFreshEntity(oculusOreProjectile);
                }else {
                    pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    OculusOreProjectile oculusOreProjectile = new OculusOreProjectile(pLevel, pPlayer);
                    oculusOreProjectile.setItem(itemStack);
                    oculusOreProjectile.getPersistentData().putBoolean("hasEntity", itemStack.getOrCreateTag().getBoolean("hasEntity"));
                    oculusOreProjectile.getPersistentData().putInt("damage", itemStack.getOrCreateTag().getInt("damage"));
                    oculusOreProjectile.getPersistentData().putInt("maxDamage", itemStack.getOrCreateTag().getInt("maxDamage"));
                    oculusOreProjectile.setOwner(pPlayer);
                    oculusOreProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                    pLevel.addFreshEntity(oculusOreProjectile);
                    itemStack.shrink(1);
                }
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }
}
