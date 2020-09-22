package ru.mrktoto.rebr.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.mrktoto.rebr.BlocksRegistry;

public class TabRebr extends CreativeTabs {

	public TabRebr(int index, String label) {
		
		super(index, label);
	}

	@Override
	public Item getTabIconItem() {
		
		return Item.getItemFromBlock(BlocksRegistry.forstess_block);
	}
}
