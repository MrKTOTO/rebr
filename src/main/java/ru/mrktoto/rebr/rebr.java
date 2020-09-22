package ru.mrktoto.rebr;

import java.util.EnumMap;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import ru.mrktoto.rebr.blocks.sulfurgen.OreDictionaryBlockGenerator;
import ru.mrktoto.rebr.entity.EntityDStick;
import ru.mrktoto.rebr.entity.c4;
import ru.mrktoto.rebr.entity.dpacket;
import ru.mrktoto.rebr.items.buildingMallet_item;
import ru.mrktoto.rebr.items.dynamite_stick_item;
import ru.mrktoto.rebr.net.door_gui_handle;
import ru.mrktoto.rebr.net.eventhandlertimer;
import ru.mrktoto.rebr.net.handle;
import ru.mrktoto.rebr.items.dpacket_item;
import ru.mrktoto.rebr.proxy.CommonProxy;
import ru.mrktoto.rebr.tab.TabRebr;

@Mod(modid = rebr.MODID, name = rebr.MODNAME, version = rebr.VERSION)

public class rebr {
	
	public static final String 
	MODID = "REBR",
	MODNAME = "Raider Explosions and Base Reinforcement",
	VERSION = "1.4.1";
	
	private static Logger logger;
	
	@Instance(rebr.MODID)
	public static rebr instance;
	
	@SidedProxy(clientSide = "ru.mrktoto.rebr.proxy.ClientProxy", serverSide = "ru.mrktoto.rebr.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	public static final CreativeTabs REBR = new TabRebr(CreativeTabs.getNextID(), "tab_rebr");
	
	public static OreDictionaryBlockGenerator oreDictionaryBlockGenerator= new OreDictionaryBlockGenerator();
	
	public static int codeGUI = 2;
	
	public static EnumMap<Side, FMLEmbeddedChannel> channels;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
		
		channels = NetworkRegistry.INSTANCE.newChannel("Code", new handle());
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new door_gui_handle());
		
        this.logger = event.getModLog();
        
    	this.proxy.preInit(event);    	  	
    	
    	BlocksRegistry.register();
     }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	this.proxy.init(event);
    	Blocks.obsidian.setResistance(1.0F);
    	Blocks.anvil.setResistance(1.0F);
    	Blocks.water.setResistance(1.0F);
    	Blocks.lava.setResistance(1.0F);
    	
    	OreDictionary.registerOre("SulfurOre", BlocksRegistry.sulfur_ore);
    	OreDictionary.registerOre("Sulfur", BlocksRegistry.sulfur);
    	OreDictionary.registerOre("Potassium_NitrateOre", BlocksRegistry.Potassium_NitrateOre);
    	OreDictionary.registerOre("Potassium_Nitrate", BlocksRegistry.potassium_nitrate);
    	GameRegistry.registerWorldGenerator((IWorldGenerator) oreDictionaryBlockGenerator, 0);
    	
    	
    	GameRegistry.addSmelting(BlocksRegistry.sulfur_ore, new ItemStack(BlocksRegistry.sulfur, 1), 2.0F);
    	GameRegistry.addSmelting(BlocksRegistry.Potassium_NitrateOre, new ItemStack(BlocksRegistry.potassium_nitrate, 1), 3.0F);
    	
    	GameRegistry.addRecipe(new ItemStack(BlocksRegistry.forstess_block, 16), 
				new Object[]{"X#X", 
							 "#Y#", 
							 "X#X",
				Character.valueOf('X'), Items.iron_ingot, ('#'), Blocks.log, ('Y'), Blocks.clay});
    	
    	GameRegistry.addRecipe(new ItemStack(BlocksRegistry.forstess_blockL2, 16), 
				new Object[]{" # ", 
							 "#Y#", 
							 " # ",
				Character.valueOf('Y'), Blocks.iron_block, ('#'), Blocks.stone});
    	
    	GameRegistry.addRecipe(new ItemStack(BlocksRegistry.forstess_blockL3, 16), 
    			new Object[]{" # ", 
						 	 "XYX", 
						 	 " # ",
				Character.valueOf('X'), Items.iron_ingot, ('#'), Blocks.iron_block, ('Y'), Blocks.stone});
    	
    	GameRegistry.addRecipe(new ItemStack(BlocksRegistry.buildingmallet, 1), 
				new Object[]{"#Y#", 
							 " X ", 
							 " X ",
				Character.valueOf('X'), Items.stick, ('#'), BlocksRegistry.forstess_block, ('Y'), Blocks.lapis_block});
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.buildingmallet2, 1), 
				new Object[]{"#Y#", 
							 " X ", 
							 " X ",
				Character.valueOf('X'), Items.stick, ('#'), BlocksRegistry.forstess_blockL2, ('Y'), Blocks.lapis_block});
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.buildingmallet3, 1), 
				new Object[]{"#Y#", 
							 " X ", 
							 " X ",
				Character.valueOf('X'), Items.stick, ('#'), BlocksRegistry.forstess_blockL3, ('Y'), Blocks.lapis_block});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.bickford_fuse, 1), 
				new Object[]{"#X#", 
							 "#X#", 
							 "#X#",
				Character.valueOf('X'), BlocksRegistry.rope, ('#'), BlocksRegistry.potassium_nitrate});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.powder, 1), 
				new Object[]{"#XY", 
							 "#XY", 
							 "#XY",
				Character.valueOf('X'), Items.gunpowder, ('#'), BlocksRegistry.sulfur_briquette, ('Y'), Blocks.coal_block});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.sulfur_briquette, 1), 
				new Object[]{"XXX", 
							 "XXX", 
							 "XXX",
				Character.valueOf('X'), BlocksRegistry.sulfur});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.dynamite_stick, 1), 
				new Object[]{" # ", 
							 " X ", 
							 " X ",
				Character.valueOf('X'), BlocksRegistry.powder_briquette, ('#'), BlocksRegistry.bickford_fuse});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.powder_briquette, 1), 
				new Object[]{"XXX", 
							 "XXX", 
							 "XXX",
				Character.valueOf('X'), BlocksRegistry.powder});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.rope, 1), 
				new Object[]{" X ", 
							 "XXX", 
							 " X ",
				Character.valueOf('X'), Items.string});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.cloth, 1), 
				new Object[]{"X X", 
						 	 " X ", 
						 	 "X X",
			Character.valueOf('X'), Items.string});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.dpacket, 1), 
				new Object[]{" X ", 
							 "YUY", 
							 " # ",
				Character.valueOf('X'), BlocksRegistry.cloth, ('#'), BlocksRegistry.rope, ('Y'), BlocksRegistry.dynamite_stick, ('Z'), BlocksRegistry.bickford_fuse, ('U'), BlocksRegistry.powder_briquette});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.c4, 1), 
				new Object[]{"XYX", 
							 "YUY", 
							 "XYX",
				Character.valueOf('X'), BlocksRegistry.insulating_tape, ('Y'), BlocksRegistry.explosive, ('U'), BlocksRegistry.detonator});
		
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.insulating_tape, 1), 
				new Object[]{"   ", 
							 "XUX", 
							 " Y ",
				Character.valueOf('X'), Items.coal, ('Y'), Items.slime_ball, ('U'), BlocksRegistry.cloth});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.insulating_tape, 1), 
				new Object[]{"XUX", 
							 " Y ",
							 "   ",
				Character.valueOf('X'), Items.coal, ('Y'), Items.slime_ball, ('U'), BlocksRegistry.cloth});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.detonator, 1), 
				new Object[]{" Y ", 
							 "XUX", 
							 "XXX",
				Character.valueOf('X'), Items.iron_ingot, ('Y'), BlocksRegistry.lcd_display, ('U'), BlocksRegistry.chip});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.lcd_display, 1), 
				new Object[]{" Y ", 
							 "UUU", 
							 "XXX",
				Character.valueOf('X'), Items.iron_ingot, ('Y'), Items.glowstone_dust, ('U'), Blocks.glass});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.chip, 1), 
				new Object[]{" X ", 
							 "XUX", 
							 " X ",
				Character.valueOf('X'), Items.iron_ingot, ('U'), Blocks.lapis_block});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.explosive, 1), 
				new Object[]{" Y ", 
							 "XUX", 
							 " Y ",
				Character.valueOf('X'), BlocksRegistry.cloth, ('Y'), BlocksRegistry.sulfur_briquette, ('U'), BlocksRegistry.powder_briquette});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.door_remover, 1), 
				new Object[]{" Y ", 
							 " X ", 
							 " X ",
				Character.valueOf('X'), Items.stick, ('Y'), Blocks.lapis_block});
		
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.doorL_item, 1), 
				new Object[]{"Y# ", 
							 "#X ", 
							 "Y# ",
				Character.valueOf('X'), BlocksRegistry.detonator, ('#'), BlocksRegistry.forstess_block, ('Y'), Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.doorL2_item, 1), 
				new Object[]{"Y# ", 
							 "#X ", 
							 "Y# ",
				Character.valueOf('X'), BlocksRegistry.detonator, ('#'), BlocksRegistry.forstess_blockL2, ('Y'), Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(BlocksRegistry.doorL3_item, 1), 
				new Object[]{"Y# ", 
							 "#X ", 
							 "Y# ",
				Character.valueOf('X'), BlocksRegistry.detonator, ('#'), BlocksRegistry.forstess_blockL3, ('Y'), Items.iron_ingot});
		
		
    	
    	EntityRegistry.registerModEntity(EntityDStick.class, (String)"EntityDStick", (int)444, (Object)this, (int)80, (int)16, (boolean)true);
    	EntityRegistry.registerModEntity(dpacket.class, (String)"dpacket", (int)445, (Object)this, (int)80, (int)16, (boolean)true);
    	EntityRegistry.registerModEntity(c4.class, (String)"c4", (int)446, (Object)this, (int)80, (int)16, (boolean)true);
    	
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new eventhandlertimer());
    }
    
    public static Logger logger() {
    	
    	return logger;
    }
    
    @SideOnly(Side.CLIENT)
    public static void initClient(){
  
        RenderingRegistry.registerEntityRenderingHandler(EntityDStick.class, new net.minecraft.client.renderer.entity.RenderSnowball(BlocksRegistry.dynamite_stick));
        RenderingRegistry.registerEntityRenderingHandler(dpacket.class, new net.minecraft.client.renderer.entity.RenderSnowball(BlocksRegistry.dpacket));
        RenderingRegistry.registerEntityRenderingHandler(c4.class, new net.minecraft.client.renderer.entity.RenderSnowball(BlocksRegistry.c4));
     
    }
    
}
