package com.apocfarce.minestuck_alternia.Item.block;

import com.apocfarce.minestuck_alternia.block.PortalBase;
import com.apocfarce.minestuck_alternia.block.PortalBase.EnumParts;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class PortalBaseItem extends MultiBlockItem
{
	public PortalBaseItem(Block block,Properties properties)
	{
		super(block, properties);
		
	}
	
	@Override
	public boolean canPlaceAt(BlockItemUseContext context, Direction facing,BlockState state)
	{
		facing=facing.getOpposite();
		for(int x=0;x<=3;x++) {
				for(int z=0;z<=3;z++) {
					if(!context.getWorld().getBlockState(context.getPos().offset(facing, x).up(0).offset(facing.rotateY(),z)).isReplaceable(context)) {
						context.getPlayer().sendStatusMessage(new TranslationTextComponent("message.cant_place.portal_base"), true);
						return false;
					}
				}
		}	
		return true;
	}
	@Override
	public boolean placeBlock(BlockItemUseContext context, BlockState state)	
	{
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		Direction facing = context.getPlayer().getHorizontalFacing();
		EnumProperty<EnumParts> PART = PortalBase.PART;
		EnumProperty<Direction> HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
		if(!worldIn.isRemote)
		{
			//base
			worldIn.setBlockState(pos.offset(facing.rotateY(),0).offset(facing,0).up(0),state.with(PART, EnumParts.BASE_CORNER).with(HORIZONTAL_FACING,facing.getOpposite()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),1).offset(facing,0).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing.getOpposite()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),2).offset(facing,0).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing.getOpposite()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),3).offset(facing,0).up(0),state.with(PART, EnumParts.BASE_CORNER).with(HORIZONTAL_FACING,facing.rotateY()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),3).offset(facing,1).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing.rotateY()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),3).offset(facing,2).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing.rotateY()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),3).offset(facing,3).up(0),state.with(PART, EnumParts.BASE_CORNER).with(HORIZONTAL_FACING,facing));
			worldIn.setBlockState(pos.offset(facing.rotateY(),2).offset(facing,3).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing));
			worldIn.setBlockState(pos.offset(facing.rotateY(),1).offset(facing,3).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing));
			worldIn.setBlockState(pos.offset(facing.rotateY(),0).offset(facing,3).up(0),state.with(PART, EnumParts.BASE_CORNER).with(HORIZONTAL_FACING,facing.rotateYCCW()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),0).offset(facing,2).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing.rotateYCCW()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),0).offset(facing,1).up(0),state.with(PART, EnumParts.BASE_EDGE).with(HORIZONTAL_FACING,facing.rotateYCCW()));
			
			worldIn.setBlockState(pos.offset(facing.rotateY(),1).offset(facing,1).up(0),state.with(PART, EnumParts.BASE_CENTER).with(HORIZONTAL_FACING,facing.getOpposite()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),2).offset(facing,1).up(0),state.with(PART, EnumParts.BASE_CENTER).with(HORIZONTAL_FACING,facing.rotateY()));
			worldIn.setBlockState(pos.offset(facing.rotateY(),2).offset(facing,2).up(0),state.with(PART, EnumParts.BASE_CENTER).with(HORIZONTAL_FACING,facing));
			worldIn.setBlockState(pos.offset(facing.rotateY(),1).offset(facing,2).up(0),state.with(PART, EnumParts.BASE_CENTER).with(HORIZONTAL_FACING,facing.rotateYCCW()));

			
	
			if(context.getPlayer() instanceof ServerPlayerEntity)
				CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) context.getPlayer(), pos, context.getItem());
		}
		return true;
	}
}


