package com.macuguita.mod.datagen;

import com.macuguita.mod.reg.GMObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class GMBlockLootTableProvider extends FabricBlockLootTableProvider {

    protected GMBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryWrapper.Impl<Item> impl2 = this.registries.getOrThrow(RegistryKeys.ITEM);
        addDrop(GMObjects.CLOVERS, segmentedDrops(GMObjects.CLOVERS));
        addDrop(GMObjects.LAVENDER_SPROUTS, segmentedDrops(GMObjects.CLOVERS));
        addDrop(GMObjects.CRYSTALIZED_REDSTONE_BLOCK);
        addDrop(GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD,
                block -> this.dropsWithSilkTouch(
                block,
                ItemEntry.builder(Items.REDSTONE)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                        .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(impl2, ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .alternatively(
                                this.applyExplosionDecay(
                                        block, ItemEntry.builder(Items.REDSTONE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                )
                        )
        ));
        addDrop(GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD,
                block -> this.dropsWithSilkTouch(
                block,
                ItemEntry.builder(Items.REDSTONE)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                        .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(impl2, ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .alternatively(
                                this.applyExplosionDecay(
                                        block, ItemEntry.builder(Items.REDSTONE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                )
                        )
        ));
        addDrop(GMObjects.CRYSTALIZED_REDSTONE_CLUSTER,
                block -> this.dropsWithSilkTouch(
                        block,
                        ItemEntry.builder(GMObjects.CRYSTALIZED_REDSTONE)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(impl2, ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .alternatively(
                                        this.applyExplosionDecay(
                                                block, ItemEntry.builder(GMObjects.CRYSTALIZED_REDSTONE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                        )
                                )
                ));
    }
}
