/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod.client;

import com.macuguita.mod.reg.GMObjects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;

public class GuitaModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(GMObjects.CLOVERS, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ?
                BiomeColors.getGrassColor(blockAndTintGetter, blockPos) : -1, GMObjects.CLOVERS);

        BlockRenderLayerMap.INSTANCE.putBlock(GMObjects.LAVENDER_SPROUTS, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ?
                BiomeColors.getGrassColor(blockAndTintGetter, blockPos) : -1, GMObjects.LAVENDER_SPROUTS);

        BlockRenderLayerMap.INSTANCE.putBlock(GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GMObjects.CRYSTALIZED_REDSTONE_CLUSTER, RenderLayer.getCutout());
    }
}
