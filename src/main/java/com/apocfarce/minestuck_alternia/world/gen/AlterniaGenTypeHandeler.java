
package main.java.com.apocfarce.minestuck_alternia.world.gen;

import net.minecraft.world.gen.ChunkGeneratorType;

public class AlterniaGenTypeHandeler
{
	public static final ChunkGeneratorType<AlterniaGenSettings, AlterniaChunkGenerator> ALTERNIA =ChunkGeneratorType.func_212676_a("minestuck_alternia:alternia", AlterniaChunkGenerator::new, AlterniaGenSettings::new, false);
	
}