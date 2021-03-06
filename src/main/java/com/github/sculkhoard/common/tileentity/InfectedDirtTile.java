package com.github.sculkhoard.common.tileentity;

import com.github.sculkhoard.common.block.BlockInfestation.SpreadingTile;
import com.github.sculkhoard.core.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeTileEntity;

public class InfectedDirtTile extends SpreadingTile {

    /**
     * The Constructor that takes in properties
     * @param type The Tile Entity Type
     */
    public InfectedDirtTile(TileEntityType<?> type) {
        super(type);
    }

    /**
     * A simpler constructor that does not take in entity type.<br>
     * I made this so that registering tile entities can look cleaner
     */
    public InfectedDirtTile() {
        this(TileEntityRegistry.INFECTED_DIRT_TILE.get());
    }

}
