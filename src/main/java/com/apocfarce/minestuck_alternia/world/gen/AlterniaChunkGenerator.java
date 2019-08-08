package main.java.com.apocfarce.minestuck_alternia.world.gen;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.PhantomSpawner;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.AbstractChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.SwampHutStructure;

public class AlterniaChunkGenerator extends AbstractChunkGenerator<AlterniaGenSettings> {
	
	
	
	
	   private static final Logger LOGGER = LogManager.getLogger();
	   private NoiseGeneratorOctaves minLimitPerlinNoise;
	   private NoiseGeneratorOctaves maxLimitPerlinNoise;
	   private NoiseGeneratorOctaves mainPerlinNoise;
	   private NoiseGeneratorPerlin surfaceNoise;
	   private final AlterniaGenSettings settings;
	   private NoiseGeneratorOctaves scaleNoise;
	   private NoiseGeneratorOctaves depthNoise;
	   private final WorldType terrainType;
	   private final float[] biomeWeights;
	   private final PhantomSpawner phantomSpawner = new PhantomSpawner();
	   private final IBlockState defaultBlock;
	   private final IBlockState defaultFluid;
	   
	   
	   

	public AlterniaChunkGenerator(IWorld worldIn, BiomeProvider provider,AlterniaGenSettings settingsIn) {
	      super(worldIn, provider);
	      this.terrainType = worldIn.getWorldInfo().getTerrainType();
	      SharedSeedRandom sharedseedrandom = new SharedSeedRandom(this.seed);
	      this.minLimitPerlinNoise = new NoiseGeneratorOctaves(sharedseedrandom, 16);
	      this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(sharedseedrandom, 16);
	      this.mainPerlinNoise = new NoiseGeneratorOctaves(sharedseedrandom, 8);
	      this.surfaceNoise = new NoiseGeneratorPerlin(sharedseedrandom, 4);
	      this.scaleNoise = new NoiseGeneratorOctaves(sharedseedrandom, 10);
	      this.depthNoise = new NoiseGeneratorOctaves(sharedseedrandom, 16);
	      this.biomeWeights = new float[25];

	      for(int i = -2; i <= 2; ++i) {
	         for(int j = -2; j <= 2; ++j) {
	            float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
	            this.biomeWeights[i + 2 + (j + 2) * 5] = f;
	         }
	      }

	      this.settings = settingsIn;
	      this.defaultBlock = this.settings.getDefaultBlock();
	      this.defaultFluid = this.settings.getDefaultFluid();
	      
	      net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx =
	              new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise);
	      ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, sharedseedrandom, ctx);
	      this.minLimitPerlinNoise = ctx.getLPerlin1();
	      this.maxLimitPerlinNoise = ctx.getLPerlin2();
	      this.mainPerlinNoise = ctx.getPerlin();
	      this.surfaceNoise = ctx.getHeight();
	      this.scaleNoise = ctx.getScale();
	      this.depthNoise = ctx.getDepth();
	}

	@Override
	public void makeBase(IChunk chunkIn) {
		ChunkPos chunkpos = chunkIn.getPos();
		int i = chunkpos.x;
		int j = chunkpos.z;
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setBaseChunkSeed(i, j);
		Biome[] abiome = this.biomeProvider.getBiomeBlock(i * 16, j * 16, 16, 16);
		chunkIn.setBiomes(abiome);
//		this.setBlocksInChunk(i, j, chunkIn);
		chunkIn.createHeightMap(Heightmap.Type.WORLD_SURFACE_WG, Heightmap.Type.OCEAN_FLOOR_WG);
		this.buildSurface(chunkIn, abiome, sharedseedrandom, this.world.getSeaLevel());
	    this.makeBedrock(chunkIn, sharedseedrandom);
	    chunkIn.createHeightMap(Heightmap.Type.WORLD_SURFACE_WG, Heightmap.Type.OCEAN_FLOOR_WG);
	    chunkIn.setStatus(ChunkStatus.BASE);
			
	}

	@Override
	public void spawnMobs(WorldGenRegion region) {
	      int i = region.getMainChunkX();
	      int j = region.getMainChunkZ();
	      Biome biome = region.getChunk(i, j).getBiomes()[0];
	      SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
	      sharedseedrandom.setDecorationSeed(region.getSeed(), i << 4, j << 4);
	      WorldEntitySpawner.performWorldGenSpawning(region, biome, i, j, sharedseedrandom);
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
		if (creatureType == EnumCreatureType.MONSTER && ((SwampHutStructure)Feature.SWAMP_HUT).func_202383_b(this.world, pos)) {
			return Feature.SWAMP_HUT.getSpawnList();
		} else {
			return creatureType == EnumCreatureType.MONSTER && Feature.OCEAN_MONUMENT.isPositionInStructure(this.world, pos) ? Feature.OCEAN_MONUMENT.getSpawnList() : biome.getSpawns(creatureType);
		}
	}

	@Override
	public int spawnMobs(World worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {
		int i = 0;
		i = i + this.phantomSpawner.spawnMobs(worldIn, spawnHostileMobs, spawnPeacefulMobs);
		return i;
	}

	@Override
	public int getGroundHeight() {
	      return this.world.getSeaLevel() + 1;
	}

	@Override
	public AlterniaGenSettings getSettings() {
	      return settings;
	}

	@Override
	public double[] generateNoiseRegion(int x, int z) {

	      return this.surfaceNoise.generateRegion((double)(x << 4), (double)(z << 4), 16, 16, 0.0625D, 0.0625D, 1.0D);
	}

}
