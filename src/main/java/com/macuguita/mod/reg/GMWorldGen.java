/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod.reg;

import com.macuguita.mod.GuitaMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

public class GMWorldGen {

    public static final RegistryKey<ConfiguredFeature<?, ?>> REDSTONE_GEODE_CONFIGURED_FEATURE = registerConfiguredFeature("redstone_geode");
    public static final RegistryKey<PlacedFeature> REDSTONE_GEODE_PLACED_FEATURE = registerPlacedFeature("redstone_geode");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CLOVERS_CONFIGURED_FEATURE = registerConfiguredFeature("clovers");
    public static final RegistryKey<PlacedFeature> CLOVERS_PLACED_FEATURE = registerPlacedFeature("clovers");

    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_DECORATION, REDSTONE_GEODE_PLACED_FEATURE);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, CLOVERS_PLACED_FEATURE);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, GuitaMod.id(id));
    }

    public static RegistryKey<PlacedFeature> registerPlacedFeature(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, GuitaMod.id(id));
    }
}
