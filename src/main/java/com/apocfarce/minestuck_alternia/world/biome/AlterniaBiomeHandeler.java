package main.java.com.apocfarce.minestuck_alternia.world.biome;

import main.java.com.apocfarce.minestuck_alternia.Minestuck_alternia;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaBiomeHandeler {
	
	public static Biome alterniaPlaines;

	public static void registerBiomes(Register<Biome> event)
	{
		String modid = Minestuck_alternia.MODID;
		IForgeRegistry<Biome> registry = event.getRegistry();
		
		alterniaPlaines = register(registry,modid+":alternia_plaines",new AlterniaPlanesBiome());
		
	}
	
	private static Biome register(IForgeRegistry<Biome> registry,String key, Biome biomeIn){
		return register(registry,new ResourceLocation(key), biomeIn);
	}
	private static Biome register(IForgeRegistry<Biome> registry,ResourceLocation key, Biome biomeIn){
		registry.register(biomeIn.setRegistryName(key));
		return(biomeIn);
	}

}
