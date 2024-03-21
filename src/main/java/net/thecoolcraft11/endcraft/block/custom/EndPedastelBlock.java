package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thecoolcraft11.endcraft.block.entity.EndPedastelBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.EnderForgeConverterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class EndPedastelBlock extends BaseEntityBlock {
    private final static VoxelShape SHAPE = Block.box(2,0,2,14,12,14);
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,8);
    public EndPedastelBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TYPE);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) return InteractionResult.SUCCESS;
        EndPedastelBlockEntity blockEntity = (EndPedastelBlockEntity) pLevel.getBlockEntity(pPos);


        if (!pPlayer.getMainHandItem().isEmpty()) {

            if (blockEntity.inventory.getStackInSlot(0).isEmpty()) {
                blockEntity.inventory.setStackInSlot(0, pPlayer.getMainHandItem().copy());
                pPlayer.getMainHandItem().setCount(0);
            }
        } else {
            if (pPlayer.getMainHandItem().isEmpty()) {
                if (!(blockEntity.inventory.getStackInSlot(0).isEmpty())) {
                    pPlayer.addItem(blockEntity.inventory.getStackInSlot(0));
                    blockEntity.inventory.setStackInSlot(0, Items.AIR.getDefaultInstance());
                    blockEntity.setChanged();

                }
            }
        }
        return InteractionResult.SUCCESS;
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EndPedastelBlockEntity(blockPos, blockState);
    }
}
