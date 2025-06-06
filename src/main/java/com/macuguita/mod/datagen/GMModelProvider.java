/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod.datagen;

import com.macuguita.mod.reg.GMObjects;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;

import static net.minecraft.client.data.BlockStateModelGenerator.createSingletonBlockState;
import static net.minecraft.client.data.BlockStateModelGenerator.createWeightedVariant;
import static net.minecraft.client.data.TextureMap.getId;
import static net.minecraft.client.data.TextureMap.getSubId;

public class GMModelProvider extends FabricModelProvider {

    public GMModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerFlowerbed(GMObjects.CLOVERS);
        blockStateModelGenerator.registerFlowerbed(GMObjects.LAVENDER_SPROUTS);

        blockStateModelGenerator.registerSimpleCubeAll(GMObjects.CRYSTALIZED_REDSTONE_BLOCK);
        blockStateModelGenerator.registerAmethyst(GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD);
        blockStateModelGenerator.registerAmethyst(GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD);
        blockStateModelGenerator.registerAmethyst(GMObjects.CRYSTALIZED_REDSTONE_CLUSTER);
        registerBuddingCrystalizedRedstoneBlock(GMObjects.BUDDING_CRYSTALIZED_REDSTONE, GMObjects.CRYSTALIZED_REDSTONE_BLOCK, blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(GMObjects.CRYSTALIZED_REDSTONE, Models.GENERATED);
        itemModelGenerator.register(GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD.asItem(), Models.GENERATED);
        itemModelGenerator.register(GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD.asItem(), Models.GENERATED);
        itemModelGenerator.register(GMObjects.CRYSTALIZED_REDSTONE_CLUSTER.asItem(), Models.GENERATED);
    }

    private void registerBuddingCrystalizedRedstoneBlock(Block block, Block regular, BlockStateModelGenerator blockStateModelGenerator) {
        TextureMap textureMap = new TextureMap()
                .put(TextureKey.SIDE, getSubId(block, "_side"))
                .put(TextureKey.TOP, getId(block))
                .put(TextureKey.BOTTOM, getId(regular));
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(block, createWeightedVariant(Models.CUBE_BOTTOM_TOP.upload(block, textureMap, blockStateModelGenerator.modelCollector))));
    }
}
