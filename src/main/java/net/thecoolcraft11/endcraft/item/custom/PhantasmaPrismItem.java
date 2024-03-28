package net.thecoolcraft11.endcraft.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.core.jmx.Server;

public class PhantasmaPrismItem extends Item {
   final int FILL_LIMIT = 262176;
    public PhantasmaPrismItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
            if (pContext.getItemInHand().getOrCreateTag().getInt("mode") == 1) {
                if (!pContext.getPlayer().isCrouching()) {
                    pContext.getItemInHand().getOrCreateTag().putInt("x1", pContext.getClickedPos().getX());
                    pContext.getItemInHand().getOrCreateTag().putInt("y1", pContext.getClickedPos().getY());
                    pContext.getItemInHand().getOrCreateTag().putInt("z1", pContext.getClickedPos().getZ());
                    pContext.getItemInHand().getOrCreateTag().putBoolean("hasp1", true);
                } else {
                    pContext.getItemInHand().getOrCreateTag().putInt("x2", pContext.getClickedPos().getX());
                    pContext.getItemInHand().getOrCreateTag().putInt("y2", pContext.getClickedPos().getY());
                    pContext.getItemInHand().getOrCreateTag().putInt("z2", pContext.getClickedPos().getZ());
                    pContext.getItemInHand().getOrCreateTag().putBoolean("hasp2", true);
                }
            }
            if (pContext.getPlayer().getMainHandItem().getOrCreateTag().getInt("mode") == 2) {
                if (!pContext.getPlayer().isCrouching()) {
                    pContext.getItemInHand().getOrCreateTag().putInt("x1", pContext.getClickedPos().getX());
                    pContext.getItemInHand().getOrCreateTag().putInt("y1", pContext.getClickedPos().getY());
                    pContext.getItemInHand().getOrCreateTag().putInt("z1", pContext.getClickedPos().getZ());
                    pContext.getItemInHand().getOrCreateTag().putBoolean("hasp1", true);
                }else {
                    if(pContext.getItemInHand().getOrCreateTag().getInt("radius") < 25) {
                        pContext.getItemInHand().getOrCreateTag().putInt("radius", pContext.getItemInHand().getOrCreateTag().getInt("radius") + 1);
                    }else {
                        pContext.getItemInHand().getOrCreateTag().putInt("radius", 0);
                    }
                    pContext.getPlayer().displayClientMessage(Component.translatable("message.endcraft.phantasma_prism.radius").copy().append(String.valueOf(pContext.getItemInHand().getOrCreateTag().getInt("radius"))).withStyle(ChatFormatting.BLUE) , true);
                }
            }
            if (pContext.getItemInHand().getOrCreateTag().getInt("mode") == 3) {
                if (!pContext.getPlayer().isCrouching()) {
                    pContext.getItemInHand().getOrCreateTag().putInt("x1", pContext.getClickedPos().getX());
                    pContext.getItemInHand().getOrCreateTag().putInt("y1", pContext.getClickedPos().getY());
                    pContext.getItemInHand().getOrCreateTag().putInt("z1", pContext.getClickedPos().getZ());
                    pContext.getItemInHand().getOrCreateTag().putBoolean("hasp1", true);
                } else {
                    pContext.getItemInHand().getOrCreateTag().putInt("x2", pContext.getClickedPos().getX());
                    pContext.getItemInHand().getOrCreateTag().putInt("y2", pContext.getClickedPos().getY());
                    pContext.getItemInHand().getOrCreateTag().putInt("z2", pContext.getClickedPos().getZ());
                    pContext.getItemInHand().getOrCreateTag().putBoolean("hasp2", true);
                }
            }
            if (pContext.getItemInHand().getOrCreateTag().getInt("mode") == 4) {
                if (!pContext.getPlayer().isCrouching()) {
                    pContext.getItemInHand().getOrCreateTag().putInt("x1", pContext.getClickedPos().getX());
                    pContext.getItemInHand().getOrCreateTag().putInt("y1", pContext.getClickedPos().getY());
                    pContext.getItemInHand().getOrCreateTag().putInt("z1", pContext.getClickedPos().getZ());
                    pContext.getItemInHand().getOrCreateTag().putBoolean("hasp1", true);
                }else {
                    if(pContext.getItemInHand().getOrCreateTag().getInt("radius") < 25) {
                        pContext.getItemInHand().getOrCreateTag().putInt("radius", pContext.getItemInHand().getOrCreateTag().getInt("radius") + 1);
                    }else {
                        pContext.getItemInHand().getOrCreateTag().putInt("radius", 0);
                    }
                    pContext.getPlayer().displayClientMessage(Component.translatable("message.endcraft.phantasma_prism.radius").copy().append(String.valueOf(pContext.getItemInHand().getOrCreateTag().getInt("radius"))).withStyle(ChatFormatting.BLUE), true);
                }
                if (pContext.getItemInHand().getOrCreateTag().getInt("mode") == 5) {
                    if (!pContext.getPlayer().isCrouching()) {
                        pContext.getItemInHand().getOrCreateTag().putInt("x1", pContext.getClickedPos().getX());
                        pContext.getItemInHand().getOrCreateTag().putInt("y1", pContext.getClickedPos().getY());
                        pContext.getItemInHand().getOrCreateTag().putInt("z1", pContext.getClickedPos().getZ());
                        pContext.getItemInHand().getOrCreateTag().putBoolean("hasp1", true);
                    } else {
                        pContext.getItemInHand().getOrCreateTag().putInt("x2", pContext.getClickedPos().getX());
                        pContext.getItemInHand().getOrCreateTag().putInt("y2", pContext.getClickedPos().getY());
                        pContext.getItemInHand().getOrCreateTag().putInt("z2", pContext.getClickedPos().getZ());
                        pContext.getItemInHand().getOrCreateTag().putBoolean("hasp2", true);
                    }
                }
                if (pContext.getItemInHand().getOrCreateTag().getInt("mode") == 6) {
                    if (!pContext.getPlayer().isCrouching()) {
                        pContext.getItemInHand().getOrCreateTag().putInt("x1", pContext.getClickedPos().getX());
                        pContext.getItemInHand().getOrCreateTag().putInt("y1", pContext.getClickedPos().getY());
                        pContext.getItemInHand().getOrCreateTag().putInt("z1", pContext.getClickedPos().getZ());
                        pContext.getItemInHand().getOrCreateTag().putBoolean("hasp1", true);
                    } else {
                        pContext.getItemInHand().getOrCreateTag().putInt("x2", pContext.getClickedPos().getX());
                        pContext.getItemInHand().getOrCreateTag().putInt("y2", pContext.getClickedPos().getY());
                        pContext.getItemInHand().getOrCreateTag().putInt("z2", pContext.getClickedPos().getZ());
                        pContext.getItemInHand().getOrCreateTag().putBoolean("hasp2", true);
                    }
                }
            }

            return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
            if(pPlayer.isCrouching()) {
                if(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") < 6) {
                    pPlayer.getItemInHand(pUsedHand).getOrCreateTag().putInt("mode", pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") + 1);
                }else {
                    pPlayer.getItemInHand(pUsedHand).getOrCreateTag().putInt("mode", 1);
                }
                pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_mode." + pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode")).withStyle(ChatFormatting.AQUA), true);

                // 1 cube, 2 sphere
            }else {
                BlockPos pos1 = new BlockPos(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("x1"), pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("y1"), pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("z1"));
                BlockPos pos2 = new BlockPos(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("x2"), pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("y2"), pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("z2"));

                if(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") == 1) {
                    if(countBlocks(pos1, pos2) <= FILL_LIMIT) {
                        if(getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) >= countBlocks(pos1, pos2)) {
                            fillBlocks(pLevel, pos1, pos2, Block.byItem(pPlayer.getOffhandItem().getItem()));
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("radius");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp2");
                        }else {
                            pPlayer.displayClientMessage((Component.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) + " < " + countBlocks(pos1, pos2))).withStyle(ChatFormatting.AQUA), true);
                        }
                    }else {
                        pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).withStyle(ChatFormatting.DARK_RED),true);

                    }
                }else {
                    if(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") == 2) {
                        if(countSphere(pos1, pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("radius")) <= FILL_LIMIT) {
                            if(getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) <= countSphere(pos1, pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("radius"))) {
                                fillSphere(pLevel, pos1, pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("radius"), Block.byItem(pPlayer.getOffhandItem().getItem()));
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x1");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y1");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z1");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x2");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y2");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z2");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("radius");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp1");
                                pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp2");
                            }else {
                                pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) + " < " + countBlocks(pos1, pos2)).withStyle(ChatFormatting.DARK_RED), true);
                            }
                        }else {
                            pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).withStyle(ChatFormatting.DARK_RED), true);

                        }
                    }
                }
                if(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") == 3) {
                    if(countBlocks(pos1, pos2) <= FILL_LIMIT) {
                        if(!pPlayer.getOffhandItem().isEmpty()) {
                            replaceBlocks(pLevel, pos1, pos2, Block.byItem(pPlayer.getOffhandItem().getItem()), Blocks.AIR);
                        }else {
                            fillBlocks(pLevel, pos1, pos2, Block.byItem(pPlayer.getOffhandItem().getItem()));
                        }
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x1");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y1");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z1");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x2");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y2");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z2");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("radius");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp1");
                        pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp2");

                    }else {
                        pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).withStyle(ChatFormatting.DARK_RED), true);

                    }
                }else {
                    if(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") == 4) {
                        if(countSphere(pos1, pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("radius")) <= FILL_LIMIT) {
                            if(!pPlayer.getOffhandItem().isEmpty()) {
                                replaceSphere(pLevel, pos1, pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("radius"), Block.byItem(pPlayer.getOffhandItem().getItem()), Blocks.AIR);
                            }else {
                                fillSphere(pLevel, pos1, pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("radius"), Blocks.AIR);
                            }
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("radius");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp2");
                        }else {
                            pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).withStyle(ChatFormatting.DARK_RED), true);
                        }
                    }
                }
                if(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") == 5) {
                    if (countHollowBlocks(pos1, pos2) <= FILL_LIMIT) {
                        if (getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) >= countHollowBlocks( pos1, pos2)) {
                            fillHollowBlocks(pLevel, pos1, pos2, Block.byItem(pPlayer.getOffhandItem().getItem()));
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("radius");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp2");
                        } else {
                            pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) + " < " + countHollowBlocks( pos1, pos2)).withStyle(ChatFormatting.DARK_RED), true);
                        }
                    } else {
                        pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).withStyle(ChatFormatting.DARK_RED), true);

                    }
                }
                if(pPlayer.getItemInHand(pUsedHand).getOrCreateTag().getInt("mode") == 6) {
                    if (countHollowBlocks(pos1, pos2) <= FILL_LIMIT) {
                        if (getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) >= countPyramid( pos1, pos2)) {
                            fillPyramid(pLevel, pos1, pos2, Block.byItem(pPlayer.getOffhandItem().getItem()));
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("x2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("y2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("z2");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("radius");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp1");
                            pPlayer.getItemInHand(pUsedHand).getOrCreateTag().remove("hasp2");
                        } else {
                            pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_to.many.blocks").copy().append(" " + getItemCount(pPlayer, pPlayer.getOffhandItem().getItem()) + " < " + countPyramid( pos1, pos2)).withStyle(ChatFormatting.DARK_RED), true);
                        }
                    } else {
                        pPlayer.displayClientMessage(Component.translatable("message.endcraft.phantasma_prism_fill.limit").copy().append(" "+ FILL_LIMIT).withStyle(ChatFormatting.DARK_RED), true);

                    }
                }
            }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private static void fillBlocks(Level world, BlockPos pos1, BlockPos pos2, Block block) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    world.setBlock(currentPos, block.defaultBlockState(), 3);
                }
            }
        }
    }
    private static void fillHollowBlocks(Level world, BlockPos pos1, BlockPos pos2, Block block) {

        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    if(x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ) {
                        world.setBlock(currentPos, block.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
    private static int countHollowBlocks(BlockPos pos1, BlockPos pos2) {

        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        int count = 0;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    if(x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private static void replaceBlocks(Level world, BlockPos pos1, BlockPos pos2, Block block, Block block2) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    if(world.getBlockState(currentPos) == block.defaultBlockState()) {
                        world.setBlock(currentPos, block2.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
    private static int countBlocks(BlockPos pos1, BlockPos pos2) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());

        int count = 0;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    count++;
                }
            }
        }
        return count;
    }
    private static int countSphere( BlockPos center, int radius) {
        int diameter = radius * 2;
        int count = 0;
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    double distanceSquared = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2) + Math.pow(z - center.getZ(), 2);
                    if (distanceSquared <= radius * radius) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private static void fillSphere(Level world, BlockPos center, int radius, Block block) {
        int diameter = radius * 2;
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    double distanceSquared = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2) + Math.pow(z - center.getZ(), 2);
                    if (distanceSquared <= radius * radius) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                        world.setBlock(currentPos, block.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
    private static void replaceSphere(Level world, BlockPos center, int radius, Block block, Block block2) {
        int diameter = radius * 2;
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    double distanceSquared = Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2) + Math.pow(z - center.getZ(), 2);
                    if (distanceSquared <= radius * radius) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                        if(world.getBlockState(currentPos) == block.defaultBlockState()) {
                            world.setBlock(currentPos, block2.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }
    private static int getItemCount(Player player, Item item) {
        Inventory inventory = player.getInventory();
        int count = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == item) {
                count += stack.getCount();
            }
        }
        return count;
    }
    public static void fillPyramid(Level world, BlockPos pos1, BlockPos pos2, Block block) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());


        int height = Math.abs(pos1.getY() - pos2.getY()) + 1;

        for (int y = 0; y < height; y++) {
            int width = height - y - 1;

            for (int x = minX + y; x <= minX + width; x++) {
                for (int z = minZ + y; z <= minZ + width; z++) {
                    BlockPos blockPos = new BlockPos(x, minY + y, z);
                    world.setBlock(blockPos, block.defaultBlockState(), 3);
                }
            }
        }
    }
    public static int countPyramid(BlockPos pos1, BlockPos pos2) {
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());


        int height = Math.abs(pos1.getY() - pos2.getY()) + 1;
        int count = 0;

        for (int y = 0; y < height; y++) {
            int width = height - y - 1;

            for (int x = minX + y; x <= minX + width; x++) {
                for (int z = minZ + y; z <= minZ + width; z++) {
                    count++;

                }
            }
        }
        return count;
    }
}
