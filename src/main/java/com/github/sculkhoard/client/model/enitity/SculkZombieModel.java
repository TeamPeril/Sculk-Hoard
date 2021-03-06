package com.github.sculkhoard.client.model.enitity;

import com.github.sculkhoard.common.entity.SculkZombieEntity;
import com.github.sculkhoard.core.SculkHoard;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SculkZombieModel extends AnimatedGeoModel<SculkZombieEntity> {
    @Override
    public ResourceLocation getModelLocation(SculkZombieEntity object) {
        return new ResourceLocation(SculkHoard.MOD_ID, "geo/sculk_zombie.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SculkZombieEntity object) {
        return new ResourceLocation(SculkHoard.MOD_ID, "textures/entity/sculk_zombie.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SculkZombieEntity animatable) {
        return null;
    }
}
