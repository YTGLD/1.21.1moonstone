package com.moonstone.moonstonemod.entity;

import com.moonstone.moonstonemod.Handler;
import com.moonstone.moonstonemod.MoonStoneMod;
import com.moonstone.moonstonemod.init.Effects;
import com.moonstone.moonstonemod.init.Particles;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class flysword extends ThrowableItemProjectile {
    public int age = 0;
    private LivingEntity target;

    public flysword(EntityType<? extends flysword> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.IRON_SWORD;
    }
    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        age++;
        if (age > 100) {
            this.discard();
        }
        if (age > 20) {
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(Particles.blue.get(), this.getX(), this.getEyeY(), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0);
            }
        }
        if (target == null || !target.isAlive()) {
            findNewTarget();
        }

        if (target != null) {
            Vec3 targetPos = target.position().add(0, 0.5, 0);
            Vec3 currentPos = this.position();
            Vec3 direction = targetPos.subtract(currentPos).normalize();
            this.setDeltaMovement(direction.x *0.375f, direction.y *0.375f, direction.z *0.375f);
        }

    }
    private void findNewTarget() {
        AABB searchBox = this.getBoundingBox().inflate(16);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, searchBox);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            ResourceLocation name = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
            if (this.getOwner() != null) {
                if (!name.getNamespace().equals(MoonStoneMod.MODID) && !(entity.is(this.getOwner()))) {
                    double distance = this.distanceToSqr(entity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }

        this.target = closestEntity;
    }
    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        if (age > 10) {
            Entity entity = hitResult.getEntity();
            if (entity instanceof LivingEntity livingEntity){
                if (this.getOwner() instanceof LivingEntity living) {
                    if (Handler.hascurio(livingEntity, com.moonstone.moonstonemod.init.Items.doomeye.get())) {

                    } else {
                        livingEntity.invulnerableTime = 0;
                        entity.hurt(this.damageSources().magic(), 2 + living.getMaxHealth()/20);
                        (living).addEffect(new MobEffectInstance(Effects.fear,100,0));
                        this.discard();

                    }
                    if (Handler.hascurio(livingEntity, com.moonstone.moonstonemod.init.Items.doomswoud.get())) {

                    } else {
                        livingEntity.invulnerableTime = 0;
                        entity.hurt(this.damageSources().magic(), 2 + living.getMaxHealth()/20);
                        (living).addEffect(new MobEffectInstance(Effects.fear,100,0));
                        this.discard();

                    }
                }
            }
        }
        this.discard();
    }


}
