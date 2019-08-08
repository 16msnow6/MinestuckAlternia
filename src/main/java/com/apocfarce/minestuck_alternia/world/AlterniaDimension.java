package main.java.com.apocfarce.minestuck_alternia.world;

import java.util.function.Function;

import main.java.com.apocfarce.minestuck_alternia.world.gen.AlterniaChunkGenerator;
import main.java.com.apocfarce.minestuck_alternia.world.gen.AlterniaGenSettings;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.ModDimension;

public class AlterniaDimension extends Dimension {
	DimensionType type;

	public static ChunkGeneratorType<AlterniaGenSettings, AlterniaChunkGenerator> alterniaGenType;
	
	
	public AlterniaDimension(DimensionType type)
	{
		this.type = type;
	}

	@Override
	protected void init() {
		this.hasSkyLight = true;
	}

	@Override
	public IChunkGenerator<?> createChunkGenerator() {
		alterniaGenType =ChunkGeneratorType.func_212676_a("surface", AlterniaChunkGenerator::new, AlterniaGenSettings::new, true);
		
		BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeprovidertype = BiomeProviderType.FIXED;

		AlterniaGenSettings alterniaGenSettings = alterniaGenType.createSettings();
		SingleBiomeProviderSettings singlebiomeprovidersettings1 = biomeprovidertype.createSettings();
		return alterniaGenType.create(this.world, biomeprovidertype.create(singlebiomeprovidersettings1), alterniaGenSettings);

	}

	@Override
	public BlockPos findSpawn(ChunkPos p_206920_1_, boolean checkValid) {
		for(int i = p_206920_1_.getXStart(); i <= p_206920_1_.getXEnd(); ++i) {
			for(int j = p_206920_1_.getZStart(); j <= p_206920_1_.getZEnd(); ++j) {
				BlockPos blockpos = this.findSpawn(i, j, checkValid);
				if (blockpos != null) {
	                return blockpos;
	             }
	          }
	       }

	       return null;
	}

	@Override
	public BlockPos findSpawn(int p_206921_1_, int p_206921_2_, boolean checkValid) {
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_206921_1_, 0, p_206921_2_);
		Biome biome = this.world.getBiome(blockpos$mutableblockpos);
		IBlockState iblockstate = biome.getSurfaceBuilderConfig().getTop();
		if (checkValid && !iblockstate.getBlock().isIn(BlockTags.VALID_SPAWN)) {
			return null;
		} else {
			Chunk chunk = this.world.getChunk(p_206921_1_ >> 4, p_206921_2_ >> 4);
			int i = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, p_206921_1_ & 15, p_206921_2_ & 15);
			if (i < 0) {
				return null;
			} else if (chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, p_206921_1_ & 15, p_206921_2_ & 15) > chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR, p_206921_1_ & 15, p_206921_2_ & 15)) {
				return null;
			} else {
				for(int j = i + 1; j >= 0; --j) {
					blockpos$mutableblockpos.setPos(p_206921_1_, j, p_206921_2_);
					IBlockState iblockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
					if (!iblockstate1.getFluidState().isEmpty()) {
						break;
					}
					
					if (iblockstate1.equals(iblockstate)) {
						return blockpos$mutableblockpos.up().toImmutable();
					}	
				}

				return null;
			}
		}
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		int i = (int)(worldTime % 24000L);
		float f = ((float)i + partialTicks) / 24000.0F - 0.25F;
		if (f < 0.0F) {
			++f;
		}	

		if (f > 1.0F) {
			--f;
		}

		float f1 = 1.0F - (float)((Math.cos((double)f * Math.PI) + 1.0D) / 2.0D);
		f = f + (f1 - f) / 3.0F;
		return f;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		float f = MathHelper.cos(p_76562_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		float f1 = 0.7529412F;
		float f2 = 0.84705883F;
		float f3 = 1.0F;
		f1 = f1 * (f * 0.94F + 0.06F);
		f2 = f2 * (f * 0.94F + 0.06F);
		f3 = f3 * (f * 0.91F + 0.09F);
		return new Vec3d((double)f1, (double)f2, (double)f3);
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@Override
	public DimensionType getType() {
		return type;
	}

	
	
	public static class type extends ModDimension
	{
		@Override
		public Function<DimensionType, ? extends Dimension> getFactory()
		{
			return AlterniaDimension::new;
		}
	}
}
