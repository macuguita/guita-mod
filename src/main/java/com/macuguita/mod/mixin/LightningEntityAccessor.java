package com.macuguita.mod.mixin;

import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LightningEntity.class)
public interface LightningEntityAccessor {

    @Invoker("getAffectedBlockPos")
    public BlockPos guita$invokeGetAffectedBlockPos();
}
