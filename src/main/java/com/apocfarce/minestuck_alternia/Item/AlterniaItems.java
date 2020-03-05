package com.apocfarce.minestuck_alternia.Item;

import com.apocfarce.minestuck_alternia.Minestuck_alternia;
import com.apocfarce.minestuck_alternia.Item.block.GreenSnakeItem;
import com.apocfarce.minestuck_alternia.Item.block.PortalBaseItem;
import com.apocfarce.minestuck_alternia.Item.block.PortalCenterItem;
import com.apocfarce.minestuck_alternia.Item.block.PortalCrownItem;
import com.apocfarce.minestuck_alternia.Item.block.RedSnakeItem;
import com.apocfarce.minestuck_alternia.block.AlterniaBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class AlterniaItems
{
	//the tab for future reference
	//public static CreativeTabs modTab = TabAlternia.instance;
	
	//items
	public static Item cherubKey;
	//items used only in crafting
	public static Item redSnake;
	public static Item greenSnake;
	public static Item portalCrown;
	public static Item portalBody;
	public static Item portalBase;
	//multi colored items
	public static Item[] bloodPotions;
	
	//food
	//public static Item oblongMeatProduct=new ItemFood(6,true);
	
	
	
	public static void registerItems(Register<Item> event)
	{
		//get the registry
		IForgeRegistry<Item> registry = event.getRegistry();
		
		String modid = Minestuck_alternia.MOD_ID;
		/**-----------------------------------
		 * items
		 *-------------------------------------*/
		//items
		cherubKey=register(registry,modid+":cherub_key",new Item(new Item.Properties().group(ItemGroupAlternia.instance)));
		

		
		
		//food
		register(registry,modid+":oblong_meat_product",new Item(new Item.Properties().group(ItemGroupAlternia.instance).food(new Food.Builder().hunger(6).saturation(3).build())));
		//blood colored items
		bloodPotions=new Item[ENUM_BLOOD_COLOR.values().length];
		for(int i=0;i<bloodPotions.length;i++) {
			if(i!=ENUM_BLOOD_COLOR.GREY.ordinal()) {
				bloodPotions[i]=register(registry,modid+":blood_bottle_"+ENUM_BLOOD_COLOR.values()[i].name().toLowerCase(),new BloodBottle(new Item.Properties().group(ItemGroupAlternia.instance).maxStackSize(1),ENUM_BLOOD_COLOR.values()[i]));
			}
		}
		
		/**-----------------------------------
		 *blocks
		 -------------------------------------*/
		register(registry,AlterniaBlocks.darkCobble,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.darkStone,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.redCobble,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.redRock,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralLeaves,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralLog,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralWood,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.strippedPyralLog,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.strippedPyralWood,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralPlanks,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralStairs,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralSlab,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralFence,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.pyralFenceGate,ItemGroupAlternia.instance);

		register(registry,AlterniaBlocks.mirrageLeaves,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.mirrageLog,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.mirrageWood,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.strippedmirrageLog,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.strippedmirrageWood,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.mirragePlanks,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.mirrageStairs,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.mirrageSlab,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.mirrageFence,ItemGroupAlternia.instance);
		register(registry,AlterniaBlocks.mirrageFenceGate,ItemGroupAlternia.instance);
		
		register(registry,AlterniaBlocks.pyralGrass,ItemGroupAlternia.instance);
		
		
		register(registry,new PortalCenterItem(AlterniaBlocks.portalCenter, new Item.Properties().group(ItemGroupAlternia.instance)));
		register(registry,new PortalCrownItem(AlterniaBlocks.portalCrown, new Item.Properties().group(ItemGroupAlternia.instance)));
		register(registry,new PortalBaseItem(AlterniaBlocks.portalBase, new Item.Properties().group(ItemGroupAlternia.instance)));
		register(registry,new RedSnakeItem(AlterniaBlocks.redSnake, new Item.Properties().group(ItemGroupAlternia.instance)));
		register(registry,new GreenSnakeItem(AlterniaBlocks.greenSnake, new Item.Properties().group(ItemGroupAlternia.instance)));
	
			
		
		
		
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
	      return register(registry,new BlockItem(blockIn, new Item.Properties()));
	   }

	   private static Item register(IForgeRegistry<Item> registry,Block blockIn, ItemGroup group) {
	      return register(registry,new BlockItem(blockIn, (new Item.Properties()).group(group)));
	   }

	   private static Item register(IForgeRegistry<Item> registry,BlockItem itemBlockIn) {
	      return register(registry,itemBlockIn.getBlock(), itemBlockIn);
	   }

	   protected static Item register(IForgeRegistry<Item> registry,Block blockIn, Item itemIn) {
	      return register(registry,blockIn.getRegistryName(), itemIn);
	   }

	   private static Item register(IForgeRegistry<Item> registry,String id, Item itemIn) {
	      return register(registry,new ResourceLocation(id), itemIn);
	   }

	   private static Item register(IForgeRegistry<Item> registry,ResourceLocation key, Item itemIn) {
	      if (itemIn instanceof BlockItem) {
	         ((BlockItem)itemIn).addToBlockToItemMap( net.minecraftforge.registries.GameData.getBlockItemMap(), itemIn);
	      }
	      registry.register(itemIn.setRegistryName(key));
	      //IRegistry.field_212630_s.put(resourceLocationIn, itemIn);
	      return(itemIn);
	   }
}
