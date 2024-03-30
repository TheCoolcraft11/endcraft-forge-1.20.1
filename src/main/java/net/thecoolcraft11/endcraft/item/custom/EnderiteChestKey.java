package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraftforge.network.NetworkHooks;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.EnderiteChestBlockEntity;
import net.thecoolcraft11.endcraft.networking.ModMessages;
import net.thecoolcraft11.endcraft.networking.packet.OpenEnderiteChestC2SPacket;

public class EnderiteChestKey extends Item {
    public EnderiteChestKey(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().getBlockState(pContext.getClickedPos()) == ModBlocks.ENDERITE_CHEST.get().defaultBlockState()) {
            if (!pContext.getItemInHand().getOrCreateTag().getBoolean("aligned")) {
                if(pContext.getLevel().getBlockState(pContext.getClickedPos()) == ModBlocks.ENDERITE_CHEST.get().defaultBlockState()) {
                    pContext.getItemInHand().getOrCreateTag().putInt("x1", pContext.getClickedPos().getX());
                    pContext.getItemInHand().getOrCreateTag().putInt("y1", pContext.getClickedPos().getY());
                    pContext.getItemInHand().getOrCreateTag().putInt("z1", pContext.getClickedPos().getZ());
                    BlockEntity blockEntity = pContext.getLevel().getBlockEntity(pContext.getClickedPos());
                    if (blockEntity instanceof EnderiteChestBlockEntity) {
                        EnderiteChestBlockEntity customBlockEntity = (EnderiteChestBlockEntity) blockEntity;
                        pContext.getItemInHand().getOrCreateTag().putUUID("pwd", customBlockEntity.getPwd());
                    }
                    pContext.getItemInHand().getOrCreateTag().putBoolean("aligned", true);
                }
            }else {
                pContext.getPlayer().displayClientMessage(Component.translatable("message.endcraft.enderite_chest.already_aligned").withStyle(ChatFormatting.DARK_RED), true);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        BlockPos pos = new BlockPos(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("x1"), pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("y1"), pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("z1"));
        BlockEntity blockEntity = pLevel.getBlockEntity(pos);
        if (blockEntity instanceof EnderiteChestBlockEntity) {
            EnderiteChestBlockEntity customBlockEntity = (EnderiteChestBlockEntity) blockEntity;
            if (customBlockEntity.getPlacer() != null) {
                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getUUID("pwd");
                if (pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getUUID("pwd").equals(customBlockEntity.getPwd())) {
                    BlockEntity entity = pLevel.getBlockEntity(pos);
                    if (entity instanceof EnderiteChestBlockEntity) {
                        ModMessages.sendToServer(new OpenEnderiteChestC2SPacket(pos));
                    } else {
                        throw new IllegalStateException("Our Container provider is missing!");
                    }
                } else {
                    pPlayer.displayClientMessage(Component.translatable("message.endcraft.enderite_chest.pwd_not_match").copy().withStyle(ChatFormatting.DARK_RED), true);
                }
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
