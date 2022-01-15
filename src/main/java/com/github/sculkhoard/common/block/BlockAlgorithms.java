package com.github.sculkhoard.common.block;

import com.github.sculkhoard.core.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.core.jmx.Server;

public class BlockAlgorithms {

    /**
     * A Jank solution to spawning flora. Given a random chance, spawn flora.
     * @param targetPos The BlockPos to spawn it at
     * @param world The world to spawn it in.
     */
    public static void placeSculkFlora(BlockPos targetPos, ServerWorld world)
    {
        Block[] commonFlora = {
                BlockRegistry.GRASS.get(),
                BlockRegistry.GRASS_SHORT.get()
        };
        Block targetBlock = world.getBlockState(targetPos).getBlock();

        if(targetBlock == Blocks.AIR)
        {
            Block selectedFlora;
            // A 1/100 chance to be a spike
            if(world.random.nextInt(100) == 0) selectedFlora = BlockRegistry.SPIKE.get();
            // A 1/100 chance to be a cocoon root
            else if(world.random.nextInt(100) == 1) selectedFlora = BlockRegistry.SMALL_SHROOM.get();
            // Else just a random common flora
            else selectedFlora = commonFlora[world.random.nextInt(commonFlora.length)];

            world.setBlockAndUpdate(targetPos, selectedFlora.defaultBlockState());

        }
    }
}