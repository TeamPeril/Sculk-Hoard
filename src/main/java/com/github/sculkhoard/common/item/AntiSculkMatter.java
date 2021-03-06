package com.github.sculkhoard.common.item;

import com.github.sculkhoard.common.block.BlockAlgorithms;
import com.github.sculkhoard.core.SculkHoard;
import com.github.sculkhoard.util.ForgeEventSubscriber;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeItem;
import org.lwjgl.glfw.GLFW;
import java.util.List;

import static com.github.sculkhoard.core.SculkHoard.DEBUG_MODE;

public class AntiSculkMatter extends Item implements IForgeItem {

    /**
     * The Constructor that takes in properties
     * @param properties The Properties
     */
    public AntiSculkMatter(Properties properties) {
        super(properties);
    }

    /**
     * A simpler constructor that does not take in properties.<br>
     * I made this so that registering items in ItemRegistry.java can look cleaner
     */
    public AntiSculkMatter() {
        this(getProperties());
    }

    /**
     * Determines the properties of an item.<br>
     * I made this in order to be able to establish a item's properties from within the item class and not in the ItemRegistry.java
     * @return The Properties of the item
     */
    public static Properties getProperties()
    {
        return new Item.Properties()
                .tab(SculkHoard.SCULK_GROUP)
                .durability(1)
                .rarity(Rarity.EPIC);
    }

    //This changes the text you see when hovering over an item
    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        super.appendHoverText(stack, worldIn, tooltip, flagIn); //Not sure why we need this
        tooltip.add(new TranslationTextComponent("tooltip.sculkhoard.anti_sculk_matter")); //Text that displays if not holding shift

    }

    @Override
    public Rarity getRarity(ItemStack itemStack) {
        return Rarity.EPIC;
    }

    /**
     * This function occurs when the item is right-clicked on a block.
     * This will then add every block within a sphere of a specified radius if it isnt air
     * and then add it to the convversion queue to be processed in {@link ForgeEventSubscriber#WorldTickEvent}
     * @param worldIn The world
     * @param playerIn The player entity who used it
     * @param handIn The hand they used it in
     * @return
     */
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        boolean DEBUG_THIS = DEBUG_MODE && false;

        //Get the item the player is holding
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        //If Item not on cool down
        if(!playerIn.getCooldowns().isOnCooldown(this) && !worldIn.isClientSide())
        {

            //Do a ray trace to see what block the player is looking at
            BlockRayTraceResult blockraytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
            //If our ray trace hits a block
            if (blockraytraceresult.getType() == RayTraceResult.Type.BLOCK)
            {
                BlockPos origin_pos = blockraytraceresult.getBlockPos();
                SculkHoard.infestationConversionTable.convertToVictimQueue.addAll(BlockAlgorithms.getBlockPosInCircle((ServerWorld) worldIn, origin_pos, 10, true, false));
                playerIn.getCooldowns().addCooldown(this, 20*5); //Cool down for second (20 ticks per second)
                return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
            }
        }
        return ActionResult.fail(itemstack);
    }
}
