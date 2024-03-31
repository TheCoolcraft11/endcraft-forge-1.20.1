package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.thecoolcraft11.endcraft.Endcraft;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;


public class StaffOfTeleportationItem extends Item {
    public StaffOfTeleportationItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getPlayer().isCrouching()) {
            if (!pContext.getPlayer().getMainHandItem().getOrCreateTag().getBoolean("p1")) {
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("x1", pContext.getClickedPos().getX()  + 0.5f);
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("y1", pContext.getClickedPos().above().getY());
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("z1", pContext.getClickedPos().getZ() + 0.5f);
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putString("Level1", pContext.getLevel().dimension().location().toString());
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putBoolean("p1", true);
            }else if (!pContext.getPlayer().getMainHandItem().getOrCreateTag().getBoolean("p2")) {
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("x2", pContext.getClickedPos().getX() + 0.5f);
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("y2", pContext.getClickedPos().above().getY());
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("z2", pContext.getClickedPos().getZ() + 0.5f);
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putString("Level2", pContext.getLevel().dimension().location().toString());
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putBoolean("p2", true);
            }else if (!pContext.getPlayer().getMainHandItem().getOrCreateTag().getBoolean("p3")) {
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("x3", pContext.getClickedPos().getX()  + 0.5f);
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("y3", pContext.getClickedPos().above().getY());
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putFloat("z3", pContext.getClickedPos().getZ() + 0.5f);
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putString("Level3", pContext.getLevel().dimension().location().toString());
                pContext.getPlayer().getMainHandItem().getOrCreateTag().putBoolean("p3", true);
            }
        }else {
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("x1");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("y1");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("z1");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("p1");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("x2");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("y2");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("z2");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("p2");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("x3");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("y3");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("z3");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("p3");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("Level1");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("Level2");
            pContext.getPlayer().getMainHandItem().getOrCreateTag().remove("Level3");
            pContext.getPlayer().displayClientMessage(Component.translatable("tooltip.endcraft.removed_coords").withStyle(ChatFormatting.GREEN),true);
        }
        return InteractionResult.SUCCESS ;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getOrCreateTag();
        if(pStack.getOrCreateTag().getBoolean("p1") && pStack.getOrCreateTag().contains("x1") && pStack.getOrCreateTag().contains("y1") && pStack.getOrCreateTag().contains("z1") && pStack.getOrCreateTag().contains("Level1")) {
            pTooltipComponents.add(Component.literal("1: ").append(Component.nullToEmpty(addUperCaseLetters(getDimName(pStack.getOrCreateTag().getString("Level1"), ":").replace("_", " "))).copy().append(" / ").append(String.valueOf(tag.getFloat("x1") - 0.5f)).append(", ").append(String.valueOf(tag.getInt("y1") - 0.0f)).append(", ").append(String.valueOf(tag.getFloat("z1") - 0.5f))));
        }else {
            pTooltipComponents.add(Component.nullToEmpty(null));
        }
        if(pStack.getOrCreateTag().getBoolean("p2") && pStack.getOrCreateTag().contains("x2") && pStack.getOrCreateTag().contains("y2") && pStack.getOrCreateTag().contains("z2") && pStack.getOrCreateTag().contains("Level2")) {
            pTooltipComponents.add(Component.literal("2: ").append(Component.nullToEmpty(addUperCaseLetters(getDimName(pStack.getOrCreateTag().getString("Level2"), ":").replace("_", " "))).copy().append(" / ").append(String.valueOf(tag.getFloat("x2") - 0.5f)).append(", ").append(String.valueOf(tag.getInt("y2") - 0.0f)).append(", ").append(String.valueOf(tag.getFloat("z2") - 0.5f))));
        }else {
            pTooltipComponents.add(Component.nullToEmpty(null));
        }
        if(pStack.getOrCreateTag().getBoolean("p3") && pStack.getOrCreateTag().contains("x3") && pStack.getOrCreateTag().contains("y3") && pStack.getOrCreateTag().contains("z3") && pStack.getOrCreateTag().contains("Level3")) {
            pTooltipComponents.add(Component.literal("3: ").append(Component.nullToEmpty(addUperCaseLetters(getDimName(pStack.getOrCreateTag().getString("Level3"), ":").replace("_", " "))).copy().append(" / ").append(String.valueOf(tag.getFloat("x3") - 0.5f)).append(", ").append(String.valueOf(tag.getInt("y3") - 0.0f)).append(", ").append(String.valueOf(tag.getFloat("z3") - 0.5f))));
        }else {
            pTooltipComponents.add(Component.nullToEmpty(null));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pStack.getDamageValue() > 0) {
            pStack.setDamageValue(pStack.getDamageValue() - 1);
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }
    private static String getDimName(String input, String delimiter) {
        int delimiterIndex = input.indexOf(delimiter);

        if (delimiterIndex != -1) {
            String result = input.substring(delimiterIndex + 1);
            return result;
        } else {
            return null;
        }
    }
    private static String addUperCaseLetters(String input) {
        StringBuilder result = new StringBuilder();

        boolean capitalizeNext = true;
        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
                result.append(c);
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
