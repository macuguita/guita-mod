/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod.blocks;

import com.macuguita.mod.reg.GMObjects;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class BuddingCrystalizedRedstoneBlock extends CrystalizedRedstoneBlock{

    public static final MapCodec<CrystalizedRedstoneBlock> CODEC = createCodec(BuddingCrystalizedRedstoneBlock::new);

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }
    
    public BuddingCrystalizedRedstoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            Direction direction = Direction.UP;
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD;
            } else if (blockState.isOf(GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD) && blockState.get(CrystalizedRedstoneClusterBlock.FACING) == direction) {
                block = GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD;
            } else if (blockState.isOf(GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD) && blockState.get(CrystalizedRedstoneClusterBlock.FACING) == direction) {
                block = GMObjects.CRYSTALIZED_REDSTONE_CLUSTER;
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState()
                        .with(CrystalizedRedstoneClusterBlock.FACING, direction)
                        .with(CrystalizedRedstoneClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
        }
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
