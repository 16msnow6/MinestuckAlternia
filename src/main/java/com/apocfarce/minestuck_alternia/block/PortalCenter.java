package com.apocfarce.minestuck_alternia.block;

import com.apocfarce.minestuck_alternia.Item.AlterniaItems;
import com.apocfarce.minestuck_alternia.world.DimensionsHandeler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;

public class PortalCenter extends Portal{
	public static final BooleanProperty IsOpen = BooleanProperty.create("is_open");

	
	
	public PortalCenter(Properties properties) {
		super(properties);
	}
	
	
	

	@Override
	protected MutableBoundingBox getBaseBox(IWorld worldIn, BlockPos pos) {
		Portal blockDown = (Portal)worldIn.getBlockState(pos.down()).getBlock();
		return blockDown.getBaseBox(worldIn, pos.down());
	}


	@Override
	public BlockPos getMainCorner(IWorld worldIn, BlockPos pos,Direction playerFacing) {
		Portal blockDown = (Portal)worldIn.getBlockState(pos.down()).getBlock();
		return blockDown.getMainCorner(worldIn, pos.down(),playerFacing);
	}


	@Override
	public void DestroyPart(World worldIn, BlockPos mainCorner, Direction facing,boolean isCreative) {
		if (!worldIn.isRemote&&!isCreative) {
			spawnDrops(this.getDefaultState(), worldIn, mainCorner.offset(facing,2).up(1).offset(facing.rotateY(),1));
		}
		worldIn.destroyBlock(mainCorner.offset(facing,2).up(1).offset(facing.rotateY(),1),false);
		worldIn.destroyBlock(mainCorner.offset(facing,2).up(1).offset(facing.rotateY(),2),false);
		worldIn.destroyBlock(mainCorner.offset(facing,2).up(2).offset(facing.rotateY(),2),false);
		worldIn.destroyBlock(mainCorner.offset(facing,2).up(2).offset(facing.rotateY(),1),false);
		
		
	}
	

	
	private BlockPos[] GetPortalBlocks(BlockPos pos, IWorld worldIn) {
	
		boolean up =(worldIn.getBlockState(pos.up()).has(IsOpen));
		boolean north =(worldIn.getBlockState(pos.north()).has(IsOpen));
		boolean east =(worldIn.getBlockState(pos.east()).has(IsOpen));
		boolean west =(worldIn.getBlockState(pos.west()).has(IsOpen));

		
		if(east||west) {
			if(up) {
				if(east) {
					return new BlockPos[]{pos,pos.east(),pos.up(),pos.east().up()};
				}else {
					return new BlockPos[]{pos,pos.west(),pos.up(),pos.west().up()};
				}
			}else {
				if(east) {
					return new BlockPos[]{pos,pos.east(),pos.down(),pos.east().down()};
				}else {
					return new BlockPos[]{pos,pos.west(),pos.down(),pos.west().down()};
				}
			}
		}else {
			if(up) {
				if(north) {
					return new BlockPos[]{pos,pos.north(),pos.up(),pos.north().up()};
				}else {
					return new BlockPos[]{pos,pos.south(),pos.up(),pos.south().up()};
				}
			}else {
				if(north) {
					return new BlockPos[]{pos,pos.north(),pos.down(),pos.north().down()};
				}else {
					return new BlockPos[]{pos,pos.south(),pos.down(),pos.south().down()};
				}
			}
		}
	}
	
	
	
	
	public void ClosePortal(BlockPos pos, World worldIn) {
		BlockPos[] blocks = GetPortalBlocks(pos,worldIn);
		for(int i = 0; i<blocks.length;i++) {
			worldIn.setBlockState(blocks[i], worldIn.getBlockState(blocks[i]).with(IsOpen, false));
		}
	}
	public void OpenPortal(BlockPos pos,World worldIn) {
		BlockPos[] blocks = GetPortalBlocks(pos,worldIn);
		for(int i = 0; i<blocks.length;i++) {
			worldIn.setBlockState(blocks[i], worldIn.getBlockState(blocks[i]).with(IsOpen, true));
		}
	}
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(isComplete(worldIn,pos)&&!state.get(IsOpen)&&player.getHeldItem(handIn).getItem()==AlterniaItems.cherubKey) {
			OpenPortal(pos,worldIn);
			return true;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof ServerPlayerEntity&&worldIn instanceof ServerWorld){
			if(state.get(IsOpen)) {
				ServerPlayerEntity player = (ServerPlayerEntity) entityIn;
				ServerWorld from = (ServerWorld)worldIn;
				ServerWorld to;
				
				if(from==from.getServer().getWorld(DimensionType.OVERWORLD)) {
					to=from.getServer().getWorld(DimensionsHandeler.alterniaType);
				}else{
					to=from.getServer().getWorld(DimensionType.OVERWORLD);
				}
				
				teliport(from,to,entityIn.getPosition(),player);
				ClosePortal(pos,from);

			}
		}
	}
	public static void teliport(ServerWorld from, ServerWorld to, BlockPos spawnPos,ServerPlayerEntity player) {
		from.getChunkProvider().func_217228_a(TicketType.POST_TELEPORT, new ChunkPos(spawnPos), 1, player.getEntityId());
		while(to.isAirBlock(spawnPos)){
			spawnPos=spawnPos.down();
		}
		while(!to.canBlockSeeSky(spawnPos)) {
			spawnPos=spawnPos.up();
		}
		spawnPos=spawnPos.up();
		
		if(player.isSleeping()) {
			player.wakeUpPlayer(true, true, false);
		}
		player.stopRiding();
		
		player.teleport(to, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), player.rotationYaw, player.rotationPitch);

	}
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);	
	}
	
	
	
	
	
	
	
//FACING STUFF	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING,IsOpen);
	}
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(IsOpen,false);
	}




	
	

}
