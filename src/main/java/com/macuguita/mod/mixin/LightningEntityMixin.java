package com.macuguita.mod.mixin;

import com.macuguita.mod.blocks.CrystalizedRedstoneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin extends Entity {

    public LightningEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LightningEntity;cleanOxidation(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void guita$transformBudding(CallbackInfo ci) {
        LightningEntity lightingEntity = (LightningEntity)(Object)this;
        CrystalizedRedstoneBlock.transformToBudding(lightingEntity.getWorld(), ((LightningEntityAccessor) lightingEntity).guita$invokeGetAffectedBlockPos());
    }
}
