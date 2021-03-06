package com.github.sculkhoard.client.model.enitity;

import com.github.sculkhoard.common.entity.SculkBeeHarvesterEntity;
import com.github.sculkhoard.core.SculkHoard;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SculkBeeHarvesterModel extends AnimatedGeoModel<SculkBeeHarvesterEntity> {
    @Override
    public ResourceLocation getModelLocation(SculkBeeHarvesterEntity object) {
        return new ResourceLocation(SculkHoard.MOD_ID, "geo/sculk_bee_temp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SculkBeeHarvesterEntity object) {
        return new ResourceLocation(SculkHoard.MOD_ID, "textures/entity/sculk_bee_temp.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SculkBeeHarvesterEntity animatable) {
        return null;
    }
}
