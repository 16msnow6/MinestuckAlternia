package com.apocfarce.minestuck_alternia.Item.block;

import com.apocfarce.minestuck_alternia.block.GreenSnake;
import com.apocfarce.minestuck_alternia.block.Portal;
import com.apocfarce.minestuck_alternia.block.PortalCenter;
import com.apocfarce.minestuck_alternia.block.GreenSnake.EnumParts;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class PortalCenterItem extends MultiBlockItem
{
	public PortalCenterItem(Block block,Properties properties)
	{
		super(block, properties);
		
	}
	
	@Override
	public boolean canPlaceAt(BlockItemUseContext context, Direction facing,BlockState stateOn)
	{
		if (stateOn.getBlock() instanceof Portal) {
			World worldIn = context.getWorld();
			Portal portal = (Portal)stateOn.getBlock();		
			BlockPos mainCorner = portal.getMainCorner(worldIn, context.getPos().offset(context.getFace().getOpposite()), facing);
			if(!portal.hasPart(Portal.PortalPart.CENTER, worldIn, mainCorner)){
				if(portal.hasPart(Portal.PortalPart.GREEN_SNAKE, worldIn, mainCorner)
					&&portal.hasPart(Portal.PortalPart.RED_SNAKE,worldIn,mainCorner)
					&&portal.hasPart(Portal.PortalPart.CROWN, worldIn, mainCorner)) {
					return true;
				}
				context.getPlayer().sendStatusMessage(new TranslationTextComponent("message.cant_place.portal_center"), true);
			}
		}
		return false;
	}
	@Override
	public boolean placeBlock(BlockItemUseContext context, BlockState state)	
	{
		World worldIn = context.getWorld();
		BlockPos posOn = context.getPos().offset(context.getFace().getOpposite());
		Portal portal =(Portal)worldIn.getBlockState(posOn).getBlock();
		Direction facing = portal.getOrientation(worldIn, posOn);
		if(facing == null) {
			facing=context.getPlayer().getHorizontalFacing();
		}
		BlockPos pos = portal.getMainCorner(worldIn, posOn,facing);
		BooleanProperty ISOPEN = PortalCenter.IsOpen;
		EnumProperty<Direction> HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
		if(!worldIn.isRemote)
		{
			worldIn.setBlockState(pos.offset(facing.rotateY(),1).offset(facing,2).up(1),state.with(ISOPEN, false).with(HORIZONTAL_FACING, facing.rotateY()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),2).offset(facing,2).up(1),state.with(ISOPEN, false).with(HORIZONTAL_FACING, facing.rotateYCCW()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),1).offset(facing,2).up(2),state.with(ISOPEN, false).with(HORIZONTAL_FACING, facing.getOpposite()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),2).offset(facing,2).up(2),state.with(ISOPEN, false).with(HORIZONTAL_FACING, facing));
			
			
			if(context.getPlayer() instanceof ServerPlayerEntity)
				CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) context.getPlayer(), pos, context.getItem());
		}
		return true;
	}
}


