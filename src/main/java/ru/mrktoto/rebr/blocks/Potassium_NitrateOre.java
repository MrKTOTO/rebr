package ru.mrktoto.rebr.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.rebr;

public class Potassium_NitrateOre extends Block {
	public Potassium_NitrateOre(Material material, String name, String texture) {
		super(material);
		this.setBlockName(name);
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setResistance(10.0F);
		this.setHarvestLevel("pickaxe", 2);
		this.setStepSound(soundTypeStone);
		this.setBlockTextureName(rebr.MODID+":"+texture);
		GameRegistry.registerBlock(this, name);
	}
	
 
}
