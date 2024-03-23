package net.thecoolcraft11.endcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thecoolcraft11.endcraft.worldgen.dimension.ModDimensions;

public class VoidbornAbyssPortal extends Block {
    protected static final VoxelShape SHAPE = Block.box(0.0, 6.0, 0.0, 16.0, 12.0, 16.0);
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public VoidbornAbyssPortal(Properties pProperties) {
        super(pProperties);
        defaultBlockState().setValue(ACTIVATED, false);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVATED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ACTIVATED);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity entity) {

        if (pLevel.getBlockState(pPos).getValue(ACTIVATED)) {
            if (pLevel instanceof ServerLevel && !entity.isOnPortalCooldown() && Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move(-pPos.getX(), -pPos.getY(), -pPos.getZ())), pState.getShape(pLevel, pPos), BooleanOp.AND)) {
                ResourceKey<Level> registryKey = ((ServerLevel) pLevel).getLevel().dimension() == ModDimensions.VOIDBORN_ABYSS_LEVEL_KEY ? Level.OVERWORLD : ModDimensions.VOIDBORN_ABYSS_LEVEL_KEY;
                ServerLevel serverWorld = ((ServerLevel)pLevel).getServer().getLevel(registryKey);
                if (serverWorld == null) {
                    return;
                }
                if (registryKey == ModDimensions.VOIDBORN_ABYSS_LEVEL_KEY) {
                    entity.teleportTo(serverWorld, pPos.getX(),475,pPos.getZ(), RelativeMovement.ALL,0,0);
                }else {

                    if (entity instanceof Player) {
                        Player player = (Player) entity;
                        BlockPos blockPos = player.getSleepingPos().orElse(null);

                        if(blockPos != null) {
                            player.teleportTo(serverWorld, blockPos.getX(),blockPos.getY(), blockPos.getZ(), RelativeMovement.ALL,0,0);
                        }else {
                            player.teleportTo(serverWorld, serverWorld.getServer().getLevel(registryKey).getSharedSpawnPos().getX(),serverWorld.getServer().getLevel(registryKey).getSharedSpawnPos().getY() ,serverWorld.getServer().getLevel(registryKey).getSharedSpawnPos().getZ(),RelativeMovement.ALL,0,0);
                        }
                    }else {
                        entity.teleportTo(serverWorld, serverWorld.getServer().getLevel(registryKey).getSharedSpawnPos().getX(),serverWorld.getServer().getLevel(registryKey).getSharedSpawnPos().getY() ,serverWorld.getServer().getLevel(registryKey).getSharedSpawnPos().getZ(),RelativeMovement.ALL,0,0);
                    }
                }
            }
        }else {
            Vec3 lookDirection = entity.getLookAngle().scale(-1.0);
            entity.push(lookDirection.x * 2, 2.5f, lookDirection.z * 2);
        }
        super.entityInside(pState, pLevel, pPos, entity);
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return false;
    }
}
