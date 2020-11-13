package com.apocfarce.minestuck_alternia.world;

import java.util.function.BiFunction;

import javax.annotation.Nullable;

import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBioimeProviderHandeler;
import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBiomeProvider;
import com.apocfarce.minestuck_alternia.world.biome.provider.AlterniaBiomeProviderSettings;
import com.apocfarce.minestuck_alternia.world.gen.AlterniaChunkGenerator;
import com.apocfarce.minestuck_alternia.world.gen.AlterniaGenSettings;
import com.apocfarce.minestuck_alternia.world.gen.GenTypesHandeler;

import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ModDimension;

public class AlterniaDimension extends Dimension {
	
	public AlterniaDimension(World worldIn, DimensionType typeIn){
		super(worldIn, typeIn);
	}
	@Override
	public ChunkGenerator<?> createChunkGenerator() {

//	   --------------------------------MINESTUCK ALGORYTHIM------------------------
//		AlterniaGenSettings settings = GenTypesHandeler.ALTERNIA.createSettings();
//		settings.setDefaultBlock(Blocks.STONE.getDefaultState());
//		settings.setDefaultFluid(Blocks.AIR.getDefaultState());
//		return GenTypesHandeler.ALTERNIA.create(this.world, BiomeProviderType.FIXED.create(BiomeProviderType.FIXED.createSettings().setBiome(Biomes.BADLANDS)), settings);

		
//		-------------------------------OVERWORLD ALGORyTHIM-------------------------		
		ChunkGeneratorType<AlterniaGenSettings,AlterniaChunkGenerator> alterniaChunkGenerator = GenTypesHandeler.ALTERNIA;
		BiomeProviderType<AlterniaBiomeProviderSettings, AlterniaBiomeProvider> biomeprovidertype = AlterniaBioimeProviderHandeler.ALTERNIA;
		
	      
		AlterniaGenSettings alterniagensettings = alterniaChunkGenerator.createSettings();
		alterniagensettings.setDefaultBlock(AlterniaBlocks.darkStone.getDefaultState());;
        AlterniaBiomeProviderSettings alterniabiomeprovidersettings = biomeprovidertype.createSettings().setWorldInfo(this.world.getWorldInfo()).setGeneratorSettings(alterniagensettings);
		return alterniaChunkGenerator.create(this.world, biomeprovidertype.create(alterniabiomeprovidersettings), alterniagensettings);
	   
//		------------------------------MIDNIGHT ALGORYTHEM-----------------------------	   
//	   long seed = world.getSeed();
//	   AlterniaGenSettings settings = AlterniaGenTypeHandeler.ALTERNIA.createSettings();
//	   BiomeLayers<Biome> surfaceLayers = BiomeLayerHandeler.SURFACE.make(seed);
//	   BiomeLayers<CavernousBiome> undergroundLayers = BiomeLayerHandeler.UNDERGROUND.make(seed);

//	   return new AlterniaChunkGenerator(this.world, surfaceLayers, undergroundLayers, settings);

	 
  }

   @Nullable
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

   @Nullable
   public BlockPos findSpawn(int p_206921_1_, int p_206921_2_, boolean checkValid) {
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_206921_1_, 0, p_206921_2_);
      Biome biome = this.world.getBiome(blockpos$mutableblockpos);
      BlockState iblockstate = biome.getSurfaceBuilderConfig().getTop();
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
               BlockState iblockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
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

   /**
    * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
    */
   public float calculateCelestialAngle(long worldTime, float partialTicks) {
	      double d0 = MathHelper.frac((double)worldTime / 24000.0D - 0.25D);
	      double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
	      return (float)(d0 * 2.0D + d1) / 3.0F;
   }

   /**
    * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
    */
   public boolean isSurfaceWorld() {
      return true;
   }

   /**
    * Return Vec3D with biome specific fog color
    */
   @OnlyIn(Dist.CLIENT)
   public Vec3d getFogColor(float CelestialAngle, float partialTicks) {
	  float dayRed=1.0F;
	  float dayGreen=0.5F;
	  float dayBlue=0.2F;
	  float nightRed=0.2F;
	  float nightGreen=0.3F;
	  float nightBlue=0.3F; 
	   
      float time = MathHelper.cos(CelestialAngle * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
      time = MathHelper.clamp(time, 0.0F, 1.0F);
      float RRange=dayRed-nightRed;
      float GRange=dayGreen-nightGreen;
      float BRange = dayBlue-nightBlue;
      
      float red = time*RRange+nightRed;
      float green = time*GRange+nightGreen;
      float blue = time*BRange+nightBlue;
      
      return new Vec3d((double)red, (double)green, (double)blue);
   }

   /**
    * True if the player can respawn in this dimension (true = overworld, false = nether).
    */
   public boolean canRespawnHere() {
      return true;
   }

   /**
    * Returns true if the given X,Z coordinate should show environmental fog.
    */
   @OnlyIn(Dist.CLIENT)
   public boolean doesXZShowFog(int x, int z) {
      return false;
   }
   
   
	
	public static class Type extends ModDimension
	{
		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
		{
			return AlterniaDimension::new;
		}
	}
}
