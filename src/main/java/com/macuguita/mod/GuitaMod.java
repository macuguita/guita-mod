/*
 * Copyright (c) 2025 macuguita. All Rights Reserved.
 */

package com.macuguita.mod;

import com.macuguita.mod.reg.GMObjects;
import com.macuguita.mod.reg.GMWorldGen;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuitaMod implements ModInitializer {

	public static final String MOD_ID = "guita";

	public static final Logger LOGGER = LoggerFactory.getLogger("guita mod");

	@Override
	public void onInitialize() {
		GMObjects.init();
		GMWorldGen.init();
	}

	public static Identifier id(String name) {
		return Identifier.of(MOD_ID, name);
	}
}