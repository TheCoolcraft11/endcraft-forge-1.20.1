package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thecoolcraft11.endcraft.block.entity.EndPedastelBlockEntity;
import net.thecoolcraft11.endcraft.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class EndPedastelBlock extends BaseEntityBlock {
    private final static VoxelShape SHAPE = Block.box(2,0,2,14,12,14);
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,8);
    public static final BooleanProperty RIGHT_ITEM = BooleanProperty.create("right_item");
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");



    public EndPedastelBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(RIGHT_ITEM, false).setValue(TYPE, 0).setValue(LOCKED, false));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.END_PEDASTEL_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TYPE);
        pBuilder.add(RIGHT_ITEM);
        pBuilder.add(LOCKED);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pState.getValue(LOCKED)) {
            if (pLevel.isClientSide) return InteractionResult.SUCCESS;
            EndPedastelBlockEntity blockEntity = (EndPedastelBlockEntity) pLevel.getBlockEntity(pPos);


            if (!pPlayer.getMainHandItem().isEmpty() && pPlayer.getMainHandItem().getItem() != blockEntity.inventory.getStackInSlot(0).getItem()) {

                if (blockEntity.inventory.getStackInSlot(0).isEmpty()) {
                    blockEntity.inventory.setStackInSlot(0, pPlayer.getMainHandItem().copy().copyWithCount(1));
                    pPlayer.getMainHandItem().setCount(pPlayer.getMainHandItem().getCount() - 1);
                }
            }else  {
                if (pPlayer.getMainHandItem().isEmpty()) {
                    if (!(blockEntity.inventory.getStackInSlot(0).isEmpty())) {
                        pPlayer.getInventory().add(blockEntity.inventory.getStackInSlot(0));
                        blockEntity.inventory.setStackInSlot(0, Items.AIR.getDefaultInstance());
                        blockEntity.setChanged();
                    }
                }
                    if( pPlayer.getMainHandItem().getItem() == blockEntity.inventory.getStackInSlot(0).getItem()) {
                        if (pPlayer.getMainHandItem().getCount() + blockEntity.inventory.getStackInSlot(0).getCount() <= pPlayer.getMainHandItem().getMaxStackSize()) {
                            pPlayer.getMainHandItem().setCount(pPlayer.getMainHandItem().getCount() + blockEntity.inventory.getStackInSlot(0).getCount());
                            blockEntity.inventory.setStackInSlot(0, Items.AIR.getDefaultInstance());
                            blockEntity.setChanged();
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EndPedastelBlockEntity(blockPos, blockState);
    }
}
