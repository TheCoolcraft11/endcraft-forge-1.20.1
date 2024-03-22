package net.thecoolcraft11.endcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thecoolcraft11.endcraft.block.entity.EndPedastelBlockEntity;
import net.thecoolcraft11.endcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class VoidbornAbyssPortalActivator extends Block {
    private static final VoxelShape SHAPE_UPPER = Block.box(0, -16, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_LOWER = Block.box(0, 0, 0, 16, 32, 16);
    public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half",DoubleBlockHalf.class);
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public VoidbornAbyssPortalActivator(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER));
        this.registerDefaultState(this.defaultBlockState().setValue(ON, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return SHAPE_LOWER;
        }else {
            return SHAPE_UPPER;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HALF);
        pBuilder.add(ON);
    }


    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        pLevel.setBlock(pPos.above(1), ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get().defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 0);
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }


    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(state.getValue(HALF) == DoubleBlockHalf.LOWER && level.getBlockState(pos.above(1)).getBlock() != ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get()) {
            level.setBlock(pos.above(1), Blocks.AIR.defaultBlockState(), 0);
        }if (state.getValue(HALF) == DoubleBlockHalf.UPPER  && level.getBlockState(pos.below(1)).getBlock() != ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get()) {
            level.setBlock(pos.below(1), Blocks.AIR.defaultBlockState(), 0);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

//Destroy Logic

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pState.getValue(ON)) {
            if (hasItem(pLevel, pPos.offset(5, -1, 2), ModItems.ENDERITE_INGOT.get(), 0)) {
                if (hasItem(pLevel, pPos.offset(5, -1, -2), ModItems.ENDERITE_INGOT.get(), 0)) {
                    if (hasItem(pLevel, pPos.offset(-5, -1, 2), ModItems.ENDERITE_INGOT.get(), 0)) {
                        if (hasItem(pLevel, pPos.offset(-5, -1, -2), ModItems.ENDERITE_INGOT.get(), 0)) {
                            if (hasItem(pLevel, pPos.offset(2, -1, 5), ModItems.ENDERITE_INGOT.get(), 0)) {
                                if (hasItem(pLevel, pPos.offset(-2, -1, 5), ModItems.ENDERITE_INGOT.get(), 0)) {
                                    if (hasItem(pLevel, pPos.offset(2, -1, -5), ModItems.ENDERITE_INGOT.get(), 0)) {
                                        if (hasItem(pLevel, pPos.offset(-2, -1, -5), ModItems.ENDERITE_INGOT.get(), 0)) {
                                            activatePortal(pLevel, pPos, -1, 2);
                                            pLevel.addParticle(ParticleTypes.EXPLOSION, pPos.getX(), pPos.getY(), pPos.getZ(), 0, 0, 0);
                                            pLevel.setBlock(pPos, ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get().defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(ON, true),0);
                                            pLevel.setBlock(pPos.below(1), ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get().defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(ON, true),0);
                                            return InteractionResult.SUCCESS;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
            return InteractionResult.FAIL;
    }

    private boolean hasItem(Level level, BlockPos pos, Item stack, int slot) {
        if(level.getBlockEntity(pos) != null) {
            EndPedastelBlockEntity blockEntity = ((EndPedastelBlockEntity) level.getBlockEntity(pos));
            return (blockEntity.inventory.getStackInSlot(slot).getItem() == stack);

        }
        return false;
    }
    private static void activatePortal(Level level, BlockPos centerPos, int y, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int z = -halfRadius; z <= halfRadius; z++) {
                BlockPos blockPos = centerPos.offset(x, y, z);
                level.setBlock(blockPos, ModBlocks.VOIDBORN_ABYSS_PORTAL.get().defaultBlockState().setValue(VoidbornAbyssPortal.ACTIVATED,true), 0);
            }
        }
    }
}
