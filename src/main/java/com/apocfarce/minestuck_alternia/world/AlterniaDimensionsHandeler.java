package main.java.com.apocfarce.minestuck_alternia.world;

import main.java.com.apocfarce.minestuck_alternia.Minestuck_alternia;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaDimensionsHandeler {
	public static DimensionType alterniaType;
	public static ModDimension alternia;
	private static ResourceLocation AlterniaID = new ResourceLocation(Minestuck_alternia.MODID,"alternia");
	
	public static void registerDimensions(Register<ModDimension> event) {
		IForgeRegistry<ModDimension> registry = event.getRegistry();
		
		alternia = new AlterniaDimension.Type();
		registry.register(alternia.setRegistryName(AlterniaID));
	}
	
	
	public static void registerDimensionTypes() {
		alterniaType =  DimensionType.byName(AlterniaID);
		if(alterniaType==null) {
			alterniaType = DimensionManager.registerDimension(AlterniaID, alternia, null);
		}
		
		
	}
}
