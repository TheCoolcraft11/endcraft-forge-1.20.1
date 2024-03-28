package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.common.Mod;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.block.entity.EndPedastelBlockEntity;
import net.thecoolcraft11.endcraft.item.ModItems;
import net.thecoolcraft11.endcraft.networking.ModMessages;
import net.thecoolcraft11.endcraft.networking.packet.EnderFlameC2SPacket;
import net.thecoolcraft11.endcraft.networking.packet.VoidBornPortalActivatorC2SPacket;
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
        if (pLevel.getBlockState(pPos.above(1)).getBlock() == Blocks.AIR) {
            pLevel.setBlock(pPos.above(1), ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get().defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 3);
        }else {
            pLevel.destroyBlock(pPos, false);
        }
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }


    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if(pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
            pLevel.destroyBlock(pPos.above(1),false);
        }if (pState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            pLevel.destroyBlock(pPos.below(1), false);
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pState.getValue(ON)) {
            if (hasItem(pLevel, pPos.offset(5, -1, 2))) {
                if (hasItem(pLevel, pPos.offset(5, -1, -2))) {
                    if (hasItem(pLevel, pPos.offset(-5, -1, 2))) {
                        if (hasItem(pLevel, pPos.offset(-5, -1, -2))) {
                            if (hasItem(pLevel, pPos.offset(2, -1, 5))) {
                                if (hasItem(pLevel, pPos.offset(-2, -1, 5))) {
                                    if (hasItem(pLevel, pPos.offset(2, -1, -5))) {
                                        if (hasItem(pLevel, pPos.offset(-2, -1, -5))) {
                                            activatePortal(pLevel, pPos, -1, 2);
                                            setLocked(pLevel, pPos.offset(5,-1,2));
                                            setLocked(pLevel, pPos.offset(5,-1,-2));
                                            setLocked(pLevel, pPos.offset(-5,-1,-2));
                                            setLocked(pLevel, pPos.offset(-5,-1,-2));
                                            setLocked(pLevel, pPos.offset(2,-1,5));
                                            setLocked(pLevel, pPos.offset(2,-1,-5));
                                            setLocked(pLevel, pPos.offset(-2,-1,5));
                                            setLocked(pLevel, pPos.offset(-2,-1,-5));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(5,-1, 2)));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(5,-1, -2)));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(-5,-1, 2)));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(-5,-1, -2)));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(2,-1, 5)));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(2,-1, -5)));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(-2,-1, 5)));
                                            ModMessages.sendToServer(new EnderFlameC2SPacket(pPos.offset(-2,-1, -5)));
                                            ModMessages.sendToServer(new VoidBornPortalActivatorC2SPacket(pPos));
                                            pLevel.setBlock(pPos, ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get().defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(ON, true),0);
                                            pLevel.setBlock(pPos.below(1), ModBlocks.VOIDBORN_ABYSS_PORTAL_ACTIVATOR.get().defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(ON, true),0);
                                            Vec3 lookDirection = pPlayer.getLookAngle().scale(-1.0);
                                            pPlayer.push(lookDirection.x * 1, 3.5, lookDirection.z * 1);
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

    private boolean hasItem(Level level, BlockPos pos) {
        if(level.getBlockEntity(pos) != null) {
            EndPedastelBlockEntity blockEntity = ((EndPedastelBlockEntity) level.getBlockEntity(pos));
            int type = level.getBlockState(pos).getValue(EndPedastelBlock.TYPE);
            ItemStack stack = blockEntity.inventory.getStackInSlot(0);
            return blockEntity.getRightItemsForType(type, stack);

        }
        return false;
    }
    private void setLocked(Level level, BlockPos pos) {
        if(level.getBlockState(pos).getBlock() == ModBlocks.END_PEDASTEL.get()) {
            level.setBlock(pos, level.getBlockState(pos).setValue(EndPedastelBlock.LOCKED, true), 3);
        }
    }
    private static void activatePortal(Level level, BlockPos centerPos, int y, int radius) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int z = -halfRadius; z <= halfRadius; z++) {
                BlockPos blockPos = centerPos.offset(x, y, z);
                level.setBlock(blockPos, ModBlocks.VOIDBORN_ABYSS_PORTAL.get().defaultBlockState().setValue(VoidbornAbyssPortal.ACTIVATED,true), 3);
            }
        }
    }
}
