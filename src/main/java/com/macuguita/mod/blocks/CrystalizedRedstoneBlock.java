/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod.blocks;

import com.macuguita.mod.reg.GMObjects;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CrystalizedRedstoneBlock extends Block {

    public static final MapCodec<CrystalizedRedstoneBlock> CODEC = createCodec(CrystalizedRedstoneBlock::new);

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    public CrystalizedRedstoneBlock(Settings settings) {
        super(settings);
    }

    public static void transformToBudding(World world, BlockPos pos) {
        if (pos.getY() > -30) return;
        BlockState blockState = world.getBlockState(pos);
        BlockPos blockPos;
        BlockState blockState2;
        if (blockState.isOf(Blocks.LIGHTNING_ROD)) {
            blockPos = pos.offset((blockState.get(LightningRodBlock.FACING)).getOpposite());
            blockState2 = world.getBlockState(blockPos);
        } else {
            blockPos = pos;
            blockState2 = blockState;
        }

        if (blockState2.getBlock() instanceof CrystalizedRedstoneBlock) {
            world.setBlockState(blockPos, GMObjects.BUDDING_CRYSTALIZED_REDSTONE.getDefaultState());
        }
    }

    @Override
    protected boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return 15;
    }
}
