package com.apocfarce.minestuck_alternia.world.gen.surfacebuilder;

import com.mojang.datafixers.Dynamic;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class ColoredSandsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	
	public ColoredSandsSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> configCodec) {
		super(configCodec);
	}
	
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		int xInChunk = x & 15;
		int zInChunk = z & 15;
		int color = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		BlockState surfaceBlock;
		int depth = 0;
		
		for (int y = startHeight; y >= 0; --y) {
			mutablePos.setPos(xInChunk, y, zInChunk);
			BlockState worldBlock = chunkIn.getBlockState(mutablePos);
			if (worldBlock.isAir(chunkIn, mutablePos))
				depth = 0;
			else if (worldBlock.getBlock() == defaultBlock.getBlock()) {
				
				if (depth < 8) {
					surfaceBlock = depth < 3 ? getPowder(color) : getConcrete(color);
					
					chunkIn.setBlockState(mutablePos, surfaceBlock, false);
					depth++;
				}
			}
		}
	}
	
	private BlockState getPowder(int color) {
		switch (color) {
			case 2:
				return Blocks.RED_CONCRETE_POWDER.getDefaultState();
			case 3:
				return Blocks.PINK_CONCRETE_POWDER.getDefaultState();
			case 1:
			case 4:
				return Blocks.CYAN_CONCRETE_POWDER.getDefaultState();
			default:
				return Blocks.LIGHT_BLUE_CONCRETE_POWDER.getDefaultState();
		}
	}
	
	private BlockState getConcrete(int color) {
		switch (color) {
			case 2:
				return Blocks.RED_CONCRETE.getDefaultState();
			case 3:
				return Blocks.PINK_CONCRETE.getDefaultState();
			case 1:
			case 4:
				return Blocks.CYAN_CONCRETE.getDefaultState();
			default:
				return Blocks.LIGHT_BLUE_CONCRETE.getDefaultState();
		}
	}
}