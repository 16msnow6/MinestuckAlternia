package main.java.com.apocfarce.minestuck_alternia.Item;

import main.java.com.apocfarce.minestuck_alternia.block.AlterniaBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupAlternia extends ItemGroup{
	public static final ItemGroupAlternia instance = new ItemGroupAlternia("Alternia");
	
	
	public ItemGroupAlternia(String label) {
		super(label);
	}
	@Override
	public ItemStack createIcon() {
		return new ItemStack(AlterniaBlocks.redCobble);
	}
}
