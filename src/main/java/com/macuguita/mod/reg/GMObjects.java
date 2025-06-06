/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod.reg;

import com.macuguita.mod.GuitaMod;
import com.macuguita.mod.blocks.BuddingCrystalizedRedstoneBlock;
import com.macuguita.mod.blocks.CrystalizedRedstoneBlock;
import com.macuguita.mod.blocks.CrystalizedRedstoneClusterBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Function;

public class GMObjects {

    public static final Block CLOVERS = register("clovers", FlowerbedBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.EMERALD_GREEN).noCollision().sounds(BlockSoundGroup.FLOWERBED).pistonBehavior(PistonBehavior.DESTROY));
    public static final Block LAVENDER_SPROUTS = register("lavender_sprouts", FlowerbedBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().sounds(BlockSoundGroup.FLOWERBED).pistonBehavior(PistonBehavior.DESTROY));
    public static final Item CRYSTALIZED_REDSTONE = register("crystalized_redstone", Item::new, new Item.Settings());
    public static final Block CRYSTALIZED_REDSTONE_BLOCK = register("crystalized_redstone_block",
            CrystalizedRedstoneBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.RED).strength(1.5F).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()
    );
    public static final Block BUDDING_CRYSTALIZED_REDSTONE = register("budding_crystalized_redstone",
            BuddingCrystalizedRedstoneBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED)
                    .ticksRandomly()
                    .strength(1.5F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block CRYSTALIZED_REDSTONE_CLUSTER = register("crystalized_redstone_cluster",
            settings -> new CrystalizedRedstoneClusterBlock(14.0F, 14.0F, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED)
                    .solid()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                    .strength(1.5F)
                    .luminance(state -> 5)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block MEDIUM_CRYSTALIZED_REDSTONE_BUD = register(
            "medium_crystalized_redstone_bud",
            settings -> new AmethystClusterBlock(11.0F, 12.0F, settings),
            AbstractBlock.Settings.copyShallow(CRYSTALIZED_REDSTONE_CLUSTER).sounds(BlockSoundGroup.LARGE_AMETHYST_BUD).luminance(state -> 3)
    );
    public static final Block SMALL_CRYSTALIZED_REDSTONE_BUD = register(
            "small_crystalized_redstone_bud",
            settings -> new AmethystClusterBlock(11.0F, 7.0F, settings),
            AbstractBlock.Settings.copyShallow(CRYSTALIZED_REDSTONE_CLUSTER).sounds(BlockSoundGroup.SMALL_AMETHYST_BUD).luminance(state -> 1)
    );

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, GuitaMod.id(name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
        return register(name, blockFactory, settings, true);
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, GuitaMod.id(name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, GuitaMod.id(name));
    }

    public static void init() {}
}
