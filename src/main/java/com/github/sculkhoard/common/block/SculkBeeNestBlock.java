package com.github.sculkhoard.common.block;

import com.github.sculkhoard.common.block.BlockInfestation.SpreadingBlock;
import com.github.sculkhoard.common.block.BlockInfestation.SpreadingTile;
import com.github.sculkhoard.common.tileentity.SculkBeeNestTile;
import com.github.sculkhoard.core.TileEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tileentity.BeehiveTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;

public class SculkBeeNestBlock extends BeehiveBlock {

    /**
     * MATERIAL is simply what the block is made up. This affects its behavior & interactions.<br>
     * MAP_COLOR is the color that will show up on a map to represent this block
     */
    public static Material MATERIAL = Material.PLANT;
    public static MaterialColor MAP_COLOR = MaterialColor.COLOR_CYAN;

    /**
     * HARDNESS determines how difficult a block is to break<br>
     * 0.6f = dirt<br>
     * 1.5f = stone<br>
     * 2f = log<br>
     * 3f = iron ore<br>
     * 50f = obsidian
     */
    public static float HARDNESS = 0.6f;

    /**
     * BLAST_RESISTANCE determines how difficult a block is to blow up<br>
     * 0.5f = dirt<br>
     * 2f = wood<br>
     * 6f = cobblestone<br>
     * 1,200f = obsidian
     */
    public static float BLAST_RESISTANCE = 0.5f;

    /**
     * PREFERRED_TOOL determines what type of tool will break the block the fastest and be able to drop the block if possible
     */
    public static ToolType PREFERRED_TOOL = ToolType.HOE;

    /**
     *  Harvest Level Affects what level of tool can mine this block and have the item drop<br>
     *
     *  -1 = All<br>
     *  0 = Wood<br>
     *  1 = Stone<br>
     *  2 = Iron<br>
     *  3 = Diamond<br>
     *  4 = Netherite
     */
    public static int HARVEST_LEVEL = -1;


    /**
     * The Constructor that takes in properties
     * @param prop The Properties
     */
    public SculkBeeNestBlock(AbstractBlock.Properties prop) {
        super(prop);
    }

    /**
     * A simpler constructor that does not take in properties.<br>
     * I made this so that registering blocks in BlockRegistry.java can look cleaner
     */
    public SculkBeeNestBlock() {
        this(getProperties());
    }

    /**
     * Determines the properties of a block.<br>
     * I made this in order to be able to establish a block's properties from within the block class and not in the BlockRegistry.java
     * @return The Properties of the block
     */
    public static AbstractBlock.Properties getProperties()
    {
        return AbstractBlock.Properties.of(MATERIAL, MAP_COLOR)
                .strength(HARDNESS, BLAST_RESISTANCE)
                .harvestTool(PREFERRED_TOOL)
                .harvestLevel(HARVEST_LEVEL)
                .sound(SoundType.SLIME_BLOCK)
                .noOcclusion()
                .noDrops();
    }


    /**
     * Just returns the tile entity
     * @param world The world to check
     * @param thisBlockPos The position to check
     * @return The tile entity
     */
    @Nullable
    public SculkBeeNestTile getTileEntity(World world, BlockPos thisBlockPos)
    {
        //Get tile entity for this block
        if(world.getBlockState(thisBlockPos).getBlock() instanceof SpreadingBlock)
        {
            SculkBeeNestTile thisTile = (SculkBeeNestTile) world.getBlockEntity(thisBlockPos);

            //If tile entity not yet exist, create it
            if(thisTile == null || !(thisTile instanceof SculkBeeNestTile))
            {
                thisTile = (SculkBeeNestTile) createTileEntity(world.getBlockState(thisBlockPos), world);
            }
            return thisTile;
        }
        return null;
    }

    @Nullable
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new SculkBeeNestTile();
    }


    /**
     * A function called by forge to create the tile entity.
     * @param state The current blockstate
     * @param world The world the block is in
     * @return Returns the tile entity.
     */
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return newBlockEntity(world);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext pContext)
    {
        return this.defaultBlockState();
    }



}
