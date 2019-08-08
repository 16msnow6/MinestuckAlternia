package main.java.com.apocfarce.minestuck_alternia.block;

import main.java.com.apocfarce.minestuck_alternia.Item.ENUM_BLOOD_COLOR;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaBlocks
{
	//Blocks
	public static Block block;
	public static Block darkStone;
	public static Block darkCobble;
	public static Block redRock;
	public static Block redCobble;
	//BloodColoredBlocks
	public static Block hiveGlass[];
	
	
	public static void registerBlocks(Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		
		//blood colored blocks
		hiveGlass=new Block[ENUM_BLOOD_COLOR.values().length];
		for(int i=0;i<hiveGlass.length;i++) {
			if(i!=ENUM_BLOOD_COLOR.MUTANT.ordinal()) {
				hiveGlass[i]=register(registry,"minestuck_alternia:hiveglass_"+ENUM_BLOOD_COLOR.values()[i].name().toLowerCase(), new HiveGlass(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS), ENUM_BLOOD_COLOR.values()[i]));
			}
		}


		block=register(registry,"minestuck_alternia:block",new ExampleBlock(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 10.0F)));
		darkStone=register(registry,"minestuck_alternia:dark_stone",new DarkStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 10.0F)));
		darkCobble=register(registry,"minestuck_alternia:dark_cobble",new DarkCobble(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 10.0F)));
		redRock=register(registry,"minestuck_alternia:red_rock",new RedRock(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 10.0F)));
		redCobble=register(registry,"minestuck_alternia:red_cobble",new RedCobble(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 10.0F)));

	}
	
	private static Block register(IForgeRegistry<Block> registry,ResourceLocation key, Block blockIn){
		registry.register(blockIn.setRegistryName(key));
//		IRegistry.field_212618_g.put(key, blockIn);
		return(blockIn);
	}	
	private static Block register(IForgeRegistry<Block> registry,String key, Block blockIn){
		return register(registry,new ResourceLocation(key), blockIn);
	}

}