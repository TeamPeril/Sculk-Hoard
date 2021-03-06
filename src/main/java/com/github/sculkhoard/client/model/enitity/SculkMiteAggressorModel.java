package com.github.sculkhoard.client.model.enitity;

import com.github.sculkhoard.common.entity.SculkMiteAggressorEntity;
import com.github.sculkhoard.common.entity.SculkMiteEntity;
import com.github.sculkhoard.core.SculkHoard;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SculkMiteAggressorModel extends AnimatedGeoModel<SculkMiteAggressorEntity> {
    @Override
    public ResourceLocation getModelLocation(SculkMiteAggressorEntity object) {
        return new ResourceLocation(SculkHoard.MOD_ID, "geo/sculk_mite_aggressor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SculkMiteAggressorEntity object) {
        return new ResourceLocation(SculkHoard.MOD_ID, "textures/entity/sculk_mite_aggressor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SculkMiteAggressorEntity animatable) {
        return null;
    }
}
