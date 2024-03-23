package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thecoolcraft11.endcraft.block.ModBlocks;
import net.thecoolcraft11.endcraft.statuseffect.ModStatusEffects;

import java.util.Random;

public class VoidBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 0, 0, 0);
    public VoidBlock(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(pEntity instanceof Player) {
            Player player = (Player) pEntity;
            MobEffectInstance effect = null;
            if (player.hasEffect(ModStatusEffects.VOID.get())) {
                int duration = 1;
                effect = new MobEffectInstance(ModStatusEffects.VOID.get(), player.getEffect(ModStatusEffects.VOID.get()).getDuration() + 1, 1);
                if (player.getEffect(ModStatusEffects.VOID.get()).getDuration()/ 10 < 25) {
                    duration = player.getEffect(ModStatusEffects.VOID.get()).getDuration()/ 10;
                }else {
                    duration = 25;
                }
                spread(pLevel, pPos, duration, 500, false);
            }else {
                // spread(world,pos,5, 250,false);
                effect = new MobEffectInstance(ModStatusEffects.VOID.get(), 1, 1);
            }
            player.addEffect(effect);
        }else {
            Random random = new Random();
            int rxyz = random.nextInt(64 * 2)- 64 + 1;
            int rd = random.nextInt(16)+ 1;
            pEntity.kill();
            //drawLine(world,pos, pos.add(rxyz,rxyz,rxyz), ModBlocks.VOID_FLUID, rd);
        }

    }
    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if(pLevel.getBlockState(pPos.below(1)).getBlock() == Blocks.AIR) {
            pLevel.setBlock(pPos.below(1), pState, 3);
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        }
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }

    private void spread(Level world, BlockPos centerPos, int radius, int chance, boolean spreadair) {
        int halfRadius = radius / 2;

        for (int x = -halfRadius; x <= halfRadius; x++) {
            for (int y = -halfRadius; y <= halfRadius; y++) {
                for (int z = -halfRadius; z <= halfRadius; z++) {
                    BlockPos blockPos = centerPos.offset(x, y-halfRadius, z);
                    if(spreadair) {
                        if(!(world.getBlockState(blockPos).getBlock().getExplosionResistance() >= 3600000)) {
                            Random random = new Random();
                            int randomnumber = random.nextInt(chance) + 1;
                            if (randomnumber == 1) {
                                world.setBlock(blockPos, ModBlocks.VOID_BLOCK.get().defaultBlockState(), 0);
                            }
                        }
                    }else {
                        if(!(world.getBlockState(blockPos).getBlock().getExplosionResistance() >= 3600000 )) {
                            Random random = new Random();
                            if(world.getBlockState(blockPos).getBlock() != Blocks.AIR) {
                                int randomnumber = random.nextInt(chance) + 1;
                                if (randomnumber == 1) {
                                    world.setBlock(blockPos, ModBlocks.VOID_BLOCK.get().defaultBlockState(), 0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static void drawLine(Level world, BlockPos start, BlockPos end, Block block, int diameter) {
        int startX = start.getX();
        int startY = start.getY();
        int startZ = start.getZ();
        int endX = end.getX();
        int endY = end.getY();
        int endZ = end.getZ();

        int deltaX = endX - startX;
        int deltaY = endY - startY;
        int deltaZ = endZ - startZ;

        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);
        int absDeltaZ = Math.abs(deltaZ);


        int maxDelta = Math.max(Math.max(absDeltaX, absDeltaY), absDeltaZ);

        float normalizedStepX = (float) deltaX / maxDelta;
        float normalizedStepY = (float) deltaY / maxDelta;
        float normalizedStepZ = (float) deltaZ / maxDelta;

        float x = startX;
        float y = startY;
        float z = startZ;
        for (int i = 0; i <= maxDelta; i++) {
            for (int xi = -diameter / 2; xi <= diameter / 2; xi++) {
                for (int yi = -diameter / 2; yi <= diameter / 2; yi++) {
                    for (int zi = -diameter / 2; zi <= diameter / 2; zi++) {
                        if (world.getBlockState(new BlockPos((int) x + xi, (int) y + yi, (int) z + zi)).getBlock() != Blocks.AIR) {
                            world.setBlock(new BlockPos((int) x + xi, (int) y + yi, (int) z + zi), block.defaultBlockState(), 0);
                        }
                    }
                }

                x += normalizedStepX;
                y += normalizedStepY;
                z += normalizedStepZ;
            }
        }
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return false;
    }
}
