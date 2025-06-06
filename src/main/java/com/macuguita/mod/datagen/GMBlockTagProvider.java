package com.macuguita.mod.datagen;

import com.macuguita.mod.reg.GMObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class GMBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public GMBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(GMObjects.BUDDING_CRYSTALIZED_REDSTONE)
                .add(GMObjects.CRYSTALIZED_REDSTONE_BLOCK)
                .add(GMObjects.CRYSTALIZED_REDSTONE_CLUSTER)
                .add(GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD)
                .add(GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD);
    }
}
