package ru.mrktoto.rebr.blocks;

import java.util.List;
import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.blocks.tile.BlockTileEntity;
import ru.mrktoto.rebr.tiles.TileEntityCounterPersonal;

public class forstess_blockL3 extends BlockTileEntity<TileEntityCounterPersonal> {

	public static final String[] metadata = new String[] {
			"forstess_blockL3",
	        "forstess_blockL3_meta_1", 
	        "forstess_blockL3_meta_2", 
	        "forstess_blockL3_meta_3", 
	        "forstess_blockL3_meta_4", 
	        "forstess_blockL3_meta_5", 
	        "forstess_blockL3_meta_6", 
	        "forstess_blockL3_meta_7", 
	        "forstess_blockL3_meta_8", 
	        "forstess_blockL3_meta_9", 
	        "forstess_blockL3_meta_10", 
	        "forstess_blockL3_meta_11", 
	    };
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon_array;
	
	@Override
    public int damageDropped(int metadata) {
        return metadata;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (metadata >= 0 && metadata < forstess_blockL3.metadata.length) {
            return icon_array[metadata];
        }
        return super.getIcon(side, metadata);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs table, List list) {
        for (int j = 0; j < metadata.length; ++j) {
            list.add(new ItemStack(id, 1, j));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        super.registerBlockIcons(ir);
        icon_array = new IIcon[metadata.length];
        for (int i = 0; i < metadata.length; ++i) {
            icon_array[i] = ir.registerIcon(getTextureName() + "/" + metadata[i]);
        }
    }

	
	public forstess_blockL3(String name, Material material, float hardness, float resistanse, SoundType soundType) {
		
		super(name, material, hardness, resistanse, soundType);
		
		this.setHarvestLevel("axe", 10);	
		this.setBlockUnbreakable();
		this.setResistance(resistanse);
		this.setBlockTextureName(rebr.MODID + ":" + "forstess_block/forstess_blockL3");
	}

	@Override
	public Class<TileEntityCounterPersonal> getTileEntityClass() {
		
		return TileEntityCounterPersonal.class;
	}

	@Override
	public TileEntityCounterPersonal createTileEntity(World world, int meta) {
		
		return new TileEntityCounterPersonal();
	}
	
	@Override
    public void onBlockPlacedBy(World world, int xPos, int yPos, int zPos, EntityLivingBase placer, ItemStack itemStack) {
    	
		if (!world.isRemote) {
		
	    	if (this.getTileEntity(world, xPos, yPos, zPos) != null) {
	    		
	    		if (placer instanceof EntityPlayer) {
	    			
	    			this.getTileEntity(world, xPos, yPos, zPos).setOwner((EntityPlayer) placer);
	    		}
	    	}
		}
    }
	
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		TileEntityCounterPersonal tileEntity = getTileEntity(world, x, y, z);
		String playerID = player.getPersistentID().toString();
		String entityID = tileEntity.getOwnerUUID().toString();
		if (!(player.inventory.getCurrentItem() == null) && (!(playerID == null)) && (!(entityID == null)) && (!world.isRemote)) {
			ItemStack stack = player.inventory.getCurrentItem();
	
		      if (stack != null && ((stack.getItem() == BlocksRegistry.buildingmallet3)) && entityID.equals(playerID))
		      {
		         return 1F;
		      } else if (!entityID.equals(playerID)) {
		    	  player.addChatMessage(new ChatComponentTranslation("tile.counter.imposter", tileEntity.getOwnerName()));
		      } else {
		    	  return ForgeHooks.blockStrength(this, player, world, x, y, z);
		      }
		}
		return ForgeHooks.blockStrength(this, player, world, x, y, z);
	}
}
