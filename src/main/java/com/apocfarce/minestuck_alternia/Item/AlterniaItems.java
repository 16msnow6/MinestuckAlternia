package main.java.com.apocfarce.minestuck_alternia.Item;

import main.java.com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import net.minecraft.block.Block;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaItems
{
	//the tab for future reference
	//public static CreativeTabs modTab = TabAlternia.instance;
	
	//items
	public static Item ExampleItem;
	//multi colored items
	public static Item[] bloodPotions;
	
	//food
	//public static Item oblongMeatProduct=new ItemFood(6,true);
	
	
	
	public static void registerItems(Register<Item> event)
	{
		//get the registry
		IForgeRegistry<Item> registry = event.getRegistry();
		/**-----------------------------------
		 * items
		 *-------------------------------------*/
		//items
		ExampleItem=register(registry,"minestuck_alternia:example_item",new ExampleItem(new Item.Properties().group(ItemGroupAlternia.instance)));
		//food
		register(registry,"minestuck_alternia:oblong_meat_product",new ItemFood(6, 3, true, new Item.Properties().group(ItemGroupAlternia.instance)));
		//blood colored items
		bloodPotions=new Item[ENUM_BLOOD_COLOR.values().length];
		for(int i=0;i<bloodPotions.length;i++) {
			if(i!=ENUM_BLOOD_COLOR.GREY.ordinal()) {
				bloodPotions[i]=register(registry,"minestuck_alternia:blood_bottle_"+ENUM_BLOOD_COLOR.values()[i].name().toLowerCase(),new BloodBottle(new Item.Properties().group(ItemGroupAlternia.instance).maxStackSize(1),ENUM_BLOOD_COLOR.values()[i]));
			}
		}
		
		/**-----------------------------------
		 *blocks
		 -------------------------------------*/
		register(registry,AlterniaBlocks.darkCobble,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.darkStone,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.redCobble,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.redRock,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.block,ItemGroupAlternia.instance);
		for(int i=0;i<AlterniaBlocks.hiveGlass.length;i++) {
			if(i!= ENUM_BLOOD_COLOR.MUTANT.ordinal()) {
				register(registry,AlterniaBlocks.hiveGlass[i],ItemGroupAlternia.instance);
			}
		}
	}

	

	

	   /*------------------------------------------
	    * coppied from net.minecraft.item.Item
	    --------------------------------------------*/
	   private static Item register(IForgeRegistry<Item> registry,Block blockIn) {
	      return register(registry,new ItemBlock(blockIn, new Item.Properties()));
	   }

	   private static Item register(IForgeRegistry<Item> registry,Block blockIn, ItemGroup group) {
	      return register(registry,new ItemBlock(blockIn, (new Item.Properties()).group(group)));
	   }

	   private static Item register(IForgeRegistry<Item> registry,ItemBlock itemBlockIn) {
	      return register(registry,itemBlockIn.getBlock(), itemBlockIn);
	   }

	   protected static Item register(IForgeRegistry<Item> registry,Block blockIn, Item itemIn) {
	      return register(registry,blockIn.getRegistryName(), itemIn);
	   }

	   private static Item register(IForgeRegistry<Item> registry,String id, Item itemIn) {
	      return register(registry,new ResourceLocation(id), itemIn);
	   }

	   private static Item register(IForgeRegistry<Item> registry,ResourceLocation key, Item itemIn) {
	      if (itemIn instanceof ItemBlock) {
	         ((ItemBlock)itemIn).addToBlockToItemMap( net.minecraftforge.registries.GameData.getBlockItemMap(), itemIn);
	      }
	      registry.register(itemIn.setRegistryName(key));
	      //IRegistry.field_212630_s.put(resourceLocationIn, itemIn);
	      return(itemIn);
	   }
}
