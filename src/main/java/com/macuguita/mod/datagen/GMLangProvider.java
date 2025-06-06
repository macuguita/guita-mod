/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod.datagen;

import com.macuguita.mod.reg.GMObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class GMLangProvider extends FabricLanguageProvider {

    protected GMLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        generateBlockAndItemTranslations(GMObjects.CLOVERS, translationBuilder);
        generateBlockAndItemTranslations(GMObjects.LAVENDER_SPROUTS, translationBuilder);
        generateItemTranslations(GMObjects.CRYSTALIZED_REDSTONE, translationBuilder);
        generateBlockAndItemTranslations(GMObjects.CRYSTALIZED_REDSTONE_BLOCK, translationBuilder);
        generateBlockAndItemTranslations(GMObjects.BUDDING_CRYSTALIZED_REDSTONE, translationBuilder);
        generateBlockAndItemTranslations(GMObjects.CRYSTALIZED_REDSTONE_CLUSTER, translationBuilder);
        generateBlockAndItemTranslations(GMObjects.MEDIUM_CRYSTALIZED_REDSTONE_BUD, translationBuilder);
        generateBlockAndItemTranslations(GMObjects.SMALL_CRYSTALIZED_REDSTONE_BUD, translationBuilder);
    }

    private String capitalizeString(String string) {
        char[] chars = string.toLowerCase(Locale.getDefault()).toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
                found = false;
            }
        }
        return new String(chars);
    }

    private void generateBlockAndItemTranslations(Block block, TranslationBuilder translationBuilder) {
        generateBlockTranslations(block, translationBuilder);
        generateItemTranslations(block.asItem(), translationBuilder);
    }

    private void generateBlockTranslations(Block block, TranslationBuilder translationBuilder) {
        String temp = capitalizeString(Registries.BLOCK.getId(block).getPath().replace("_", " "));
        translationBuilder.add(block, temp);
    }

    private void generateItemTranslations(Item item, TranslationBuilder translationBuilder) {
        String temp = capitalizeString(Registries.ITEM.getId(item).getPath().replace("_", " "));
        translationBuilder.add(item, temp);
    }

    private void generateItemGroupTranslations(ItemGroup itemGroup, TranslationBuilder translationBuilder) {
        Identifier id = Objects.requireNonNull(Registries.ITEM_GROUP.getId(itemGroup));
        String temp = capitalizeString(id.getPath().replace("_", " "));
        translationBuilder.add("itemGroup." + id.getNamespace() + "." + id.getPath(), temp);
    }
}
