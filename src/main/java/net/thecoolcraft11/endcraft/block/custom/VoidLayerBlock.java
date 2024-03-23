package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thecoolcraft11.endcraft.damagetypes.ModDamageTypes;

public class VoidLayerBlock extends SnowLayerBlock {
    public static final int MAX_LAYERS = 8;
    public static final IntegerProperty LAYERS = SnowLayerBlock.LAYERS;
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 0, 0, 0);
    public VoidLayerBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LAYERS);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }


    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        pEntity.hurt(ModDamageTypes.of(pEntity.level(), ModDamageTypes.SWAM_VOID), 4f);
        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return false;
    }


    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if(pLevel.getBlockState(pPos.below(1)).getBlock() == Blocks.AIR) {
            pLevel.setBlock(pPos.below(1), pState, 3);
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        }
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }
}
