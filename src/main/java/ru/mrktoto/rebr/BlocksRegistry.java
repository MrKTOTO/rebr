package ru.mrktoto.rebr;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import ru.mrktoto.rebr.blocks.*;
import ru.mrktoto.rebr.entity.dpacket;
import ru.mrktoto.rebr.items.*;


public class BlocksRegistry {

	public static final Block
	sulfur_ore = new SulfurOre(Material.rock, "sulfur_ore", "sulfur_ore").setCreativeTab(rebr.REBR),
	Potassium_NitrateOre = new Potassium_NitrateOre(Material.rock, "potassium_nitrateOre", "potassium_nitrateOre").setCreativeTab(rebr.REBR),
	forstess_block = new forstess_block("forstess_block", Material.ground, 1000000.0F, 1000000.0F, Block.soundTypeWood).setCreativeTab(rebr.REBR),
	forstess_blockL2 = new forstess_blockL2("forstess_blockL2", Material.ground, 1000000.0F, 1000000.0F, Block.soundTypeStone).setCreativeTab(rebr.REBR),
	forstess_blockL3 = new forstess_blockL3("forstess_blockL3", Material.ground, 1000000.0F, 1000000.0F, Block.soundTypeMetal).setCreativeTab(rebr.REBR),
	doorL1 = new doorL1("doorL1", Material.ground, 1000000.0F, 1000000.0F, Block.soundTypeWood),
	doorL2 = new doorL2("doorL2", Material.ground, 1000000.0F, 1000000.0F, Block.soundTypeStone),
	doorL3 = new doorL3("doorL3", Material.ground, 1000000.0F, 1000000.0F, Block.soundTypeMetal);

	public static final Item 
	
	dynamite_stick = new dynamite_stick_item("dynamite_stick", "dstick", 16).setCreativeTab(rebr.REBR),
	dpacket = new dpacket_item("dpacket", "dpacket", 16).setCreativeTab(rebr.REBR),
	c4 = new c4_item("c4", "c4", 16).setCreativeTab(rebr.REBR),
	buildingmallet = new buildingMallet_item("buildingmallet", "bmallet", 1).setCreativeTab(rebr.REBR),
	buildingmallet2 = new buildingMallet_item2("buildingmallet2", "bmallet2", 1).setCreativeTab(rebr.REBR),
	buildingmallet3 = new buildingMallet_item3("buildingmallet3", "bmallet3", 1).setCreativeTab(rebr.REBR),
	sulfur = new sulfur_item("sulfur", "sulfur", 64).setCreativeTab(rebr.REBR),
	explosive = new explosive("explosive", "explosive", 64).setCreativeTab(rebr.REBR),
	insulating_tape = new insulating_tape("insulating_tape", "insulating_tape", 64).setCreativeTab(rebr.REBR),
	detonator = new detonator("detonator", "detonator", 64).setCreativeTab(rebr.REBR),
	lcd_display = new lcd_display("lcd_display", "lcd_display", 64).setCreativeTab(rebr.REBR),
	potassium_nitrate = new potassium_nitrate("potassium_nitrate", "potassium_nitrate", 64).setCreativeTab(rebr.REBR),
	chip = new chip("chip", "chip", 64).setCreativeTab(rebr.REBR),
	rope = new rope("rope", "rope", 64).setCreativeTab(rebr.REBR),
	cloth = new cloth("cloth", "cloth", 64).setCreativeTab(rebr.REBR),
	bickford_fuse = new bickford_fuse("bickford_fuse", "bickford_fuse", 64).setCreativeTab(rebr.REBR),
	powder = new powder("powder", "powder", 64).setCreativeTab(rebr.REBR),
	sulfur_briquette = new sulfur_briquette("sulfur_briquette", "sulfur_briquette", 64).setCreativeTab(rebr.REBR),
	powder_briquette = new powder_briquette("powder_briquette", "powder_briquette", 64).setCreativeTab(rebr.REBR),
	door_remover = new door_remover("door_remover", "door_remover", 64).setCreativeTab(rebr.REBR),
	doorL_item = new doorL_item(Material.wood, "doorL1_item", 2).setCreativeTab(rebr.REBR),
	doorL2_item = new doorL_item(Material.rock, "doorL2_item", 2).setCreativeTab(rebr.REBR),
	doorL3_item = new doorL_item(Material.iron, "doorL3_item", 2).setCreativeTab(rebr.REBR);
	
	public static final Block[] BLOCKS = new Block[] {
			
			forstess_block,
			forstess_blockL2,
			forstess_blockL3,
			doorL1,
			doorL2,
			doorL3
	};
	
	public static final Item[] ITEMS = new Item[] {
			
			buildingmallet,
			dynamite_stick,
			dpacket,
			c4,
			buildingmallet2,
			buildingmallet3,
			sulfur,
			explosive,
			insulating_tape,
			detonator,
			lcd_display,
			potassium_nitrate,
			chip,
			rope,
			cloth,
			bickford_fuse,
			powder,
			sulfur_briquette,
			powder_briquette,
			door_remover,
			doorL_item,
			doorL2_item,
			doorL3_item
	};
	
	public static void register() {
		
    	registerBlocks(BLOCKS);
	}
	
	public static void register2() {
		
    	registerItems(ITEMS);
	}
	
    private static void registerBlocks(Block... blocks) {
    	
		for (int i = 0; i < blocks.length; i++) {

			GameRegistry.registerBlock(blocks[i], blocks[i].getUnlocalizedName());
		}
    }
    
    private static void registerItems(Item... items) {
    	
		for (int i = 0; i < items.length; i++) {

			GameRegistry.registerItem(items[i], items[i].getUnlocalizedName());
		}
    }
}
