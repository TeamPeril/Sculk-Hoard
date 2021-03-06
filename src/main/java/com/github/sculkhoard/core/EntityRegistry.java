package com.github.sculkhoard.core;

import com.github.sculkhoard.common.entity.*;
import com.github.sculkhoard.common.entity.projectile.CustomItemProjectileEntity;
import com.github.sculkhoard.common.entity.projectile.SculkAcidicProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class EntityRegistry {

    /** ENTITY TYPES **/

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, SculkHoard.MOD_ID);
    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

    public static EntityType<CustomItemProjectileEntity> CUSTOM_ITEM_PROJECTILE_ENTITY = buildEntityType(CustomItemProjectileEntity::new, "custom_item_projectile", 0.45F, 0.45F, EntityClassification.MISC, b -> b.clientTrackingRange(4).updateInterval(10));
    public static EntityType<SculkAcidicProjectileEntity> SCULK_ACIDIC_PROJECTILE_ENTITY = buildEntityType(SculkAcidicProjectileEntity::new, "sculk_acidic_projectile", 0.45F, 0.45F, EntityClassification.MISC, b -> b.clientTrackingRange(4).updateInterval(10));

    public static EntityType<SculkZombieEntity> SCULK_ZOMBIE = buildEntityType(SculkZombieEntity::new, "sculk_zombie", 0.6f, 1.95f, EntityClassification.MONSTER, b -> b.clientTrackingRange(9));
    public static EntityType<SculkMiteEntity> SCULK_MITE = buildEntityType(SculkMiteEntity::new, "sculk_mite", 0.6f, 0.6f, EntityClassification.MONSTER, b -> b.clientTrackingRange(9));
    public static EntityType<SculkMiteAggressorEntity> SCULK_MITE_AGGRESSOR = buildEntityType(SculkMiteAggressorEntity::new, "sculk_mite_aggressor", 0.6f, 0.6f, EntityClassification.MONSTER, b -> b.clientTrackingRange(4));
    public static EntityType<SculkSpitterEntity> SCULK_SPITTER = buildEntityType(SculkSpitterEntity::new, "sculk_spitter", 0.6f, 1.95f, EntityClassification.MONSTER, b -> b.clientTrackingRange(9));
    public static EntityType<SculkBeeInfectorEntity> SCULK_BEE_INFECTOR = buildEntityType(SculkBeeInfectorEntity::new, "sculk_bee_infector", 0.7f, 0.6f, EntityClassification.MONSTER, b -> b.clientTrackingRange(9));
    public static EntityType<SculkBeeHarvesterEntity> SCULK_BEE_HARVESTER = buildEntityType(SculkBeeHarvesterEntity::new, "sculk_bee_harvester", 0.7f, 0.6f, EntityClassification.CREATURE, b -> b.clientTrackingRange(9));


    /** REGISTRY METHODS **/

    /**
     * Trying out new method for registering entities
     * @param event
     */
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
        //Projectiles
        event.getRegistry().register(CUSTOM_ITEM_PROJECTILE_ENTITY.setRegistryName(SculkHoard.MOD_ID, "custom_item_projectile"));
        event.getRegistry().register(SCULK_ACIDIC_PROJECTILE_ENTITY.setRegistryName(SculkHoard.MOD_ID, "sculk_acidic_projectile"));

        //Mobs
        event.getRegistry().register(SCULK_ZOMBIE.setRegistryName(SculkHoard.MOD_ID, "sculk_zombie"));
        event.getRegistry().register(SCULK_MITE.setRegistryName(SculkHoard.MOD_ID, "sculk_mite"));
        event.getRegistry().register(SCULK_MITE_AGGRESSOR.setRegistryName(SculkHoard.MOD_ID, "sculk_mite_aggressor"));
        event.getRegistry().register(SCULK_SPITTER.setRegistryName(SculkHoard.MOD_ID, "sculk_spitter"));
        event.getRegistry().register(SCULK_BEE_INFECTOR.setRegistryName(SculkHoard.MOD_ID, "sculk_bee_infector"));
        event.getRegistry().register(SCULK_BEE_HARVESTER.setRegistryName(SculkHoard.MOD_ID, "sculk_bee_harvester"));
    }

    /** HELPER METHODS **/

    /**
     * Builds and returns (but does not register) an entity type with the given information
     *
     * @param <T>            a class that inherits from Entity
     * @param factoryIn      the entity factory, usually [EntityClass]::new
     * @param name           the entity name for use in registration later
     * @param width          the horizontal size of the entity
     * @param height         the vertical size of the entity
     * @param classification the entity classification
     * @param builderSpecs   a consumer to add other arguments to the builder before the entity type is built
     * @return an entity type
     **/
    private static <T extends Entity> EntityType<T> buildEntityType(final EntityType.IFactory<T> factoryIn, final String name, final float width, final float height,
                                                                    final EntityClassification classification, final Consumer<EntityType.Builder<T>> builderSpecs) {
        EntityType.Builder<T> entityTypeBuilder = EntityType.Builder.of(factoryIn, classification).sized(width, height).clientTrackingRange(8);
        builderSpecs.accept(entityTypeBuilder);
        EntityType<T> entityType = entityTypeBuilder.build(name);
        return entityType;
    }


}
