package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.thecoolcraft11.endcraft.Endcraft;
import net.thecoolcraft11.endcraft.block.entity.EnderiteChestBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.ModBlockEntities;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EnderiteChestBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 15, 16);
    public EnderiteChestBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof EnderiteChestBlockEntity) {
                ((EnderiteChestBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (!pLevel.isClientSide) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof EnderiteChestBlockEntity) {
                EnderiteChestBlockEntity customBlockEntity = (EnderiteChestBlockEntity) blockEntity;
                customBlockEntity.setPlacer(pPlacer.getUUID(), pPlacer.getName().getString(), pPos);
                customBlockEntity.setPwd(UUID.randomUUID());

            }
        }
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof EnderiteChestBlockEntity) {
                EnderiteChestBlockEntity customBlockEntity = (EnderiteChestBlockEntity) blockEntity;
                if(customBlockEntity.getPlacer().equals(pPlayer.getUUID())) {
                        BlockEntity entity = pLevel.getBlockEntity(pPos);
                        if(entity instanceof EnderiteChestBlockEntity) {
                            NetworkHooks.openScreen(((ServerPlayer)pPlayer), (EnderiteChestBlockEntity)entity, pPos);
                        } else {
                            throw new IllegalStateException("Our Container provider is missing!");
                            }
                        }
                }else if (pPlayer.getItemInHand(pHand).getItem() == ModItems.ENDERITE_CHEST_KEY.get() && pPlayer.getItemInHand(pHand).getOrCreateTag().getUUID("pwd") == ((EnderiteChestBlockEntity) blockEntity).getPwd()) {
                    BlockEntity entity = pLevel.getBlockEntity(pPos);
                    if(entity instanceof EnderiteChestBlockEntity) {
                        NetworkHooks.openScreen(((ServerPlayer)pPlayer), (EnderiteChestBlockEntity)entity, pPos);
                    } else {
                        throw new IllegalStateException("Our Container provider is missing!");
                    }
                }else if (((EnderiteChestBlockEntity) blockEntity).isGuest(pPlayer)) {
                    BlockEntity entity = pLevel.getBlockEntity(pPos);
                    if(entity instanceof EnderiteChestBlockEntity) {
                        NetworkHooks.openScreen(((ServerPlayer)pPlayer), (EnderiteChestBlockEntity)entity, pPos);
                    } else {
                        throw new IllegalStateException("Our Container provider is missing!");
                    }
                }
                else{
                    pPlayer.displayClientMessage(Component.translatable("message.endcraft.enderite_chest.no_access").withStyle(ChatFormatting.DARK_RED), true);
                }
            }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.ENDERITE_CHEST_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EnderiteChestBlockEntity(blockPos, blockState);
    }
}
