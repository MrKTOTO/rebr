package ru.mrktoto.rebr.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.mrktoto.rebr.BlocksRegistry;
import ru.mrktoto.rebr.rebr;
import ru.mrktoto.rebr.blocks.tile.BlockTileEntity;
import ru.mrktoto.rebr.tiles.TileEntityCounterPersonal;

public class doorL_item extends Item {
    
    
    private Material doorMaterial;
    private static final String __OBFID = "CL_00000020";

    public doorL_item(Material material, String name, int maxStackSize)
    {
    	this.setUnlocalizedName(name);
        this.doorMaterial = material;
        this.canRepair = false;
        this.maxStackSize = maxStackSize;
        GameRegistry.registerItem(this, name);
        if (this.doorMaterial == Material.wood)
        {
            this.setTextureName(rebr.MODID + ":" + "doorL1_item");
        }
        else if (this.doorMaterial == Material.rock)
        {
            this.setTextureName(rebr.MODID + ":" + "doorL2_item");
        }else
        {
            this.setTextureName(rebr.MODID + ":" + "doorL3_item");
        }
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (side != 1)
        {
            return false;
        }
        else
        {
            ++y;
            Block var11;

            if (this.doorMaterial == Material.wood)
            {
                var11 = BlocksRegistry.doorL1;
            }
            else if (this.doorMaterial == Material.rock)
            {
                var11 = BlocksRegistry.doorL2;
            }else
            {
            	var11 = BlocksRegistry.doorL3;
            }

            if (entityplayer.canPlayerEdit(x, y, z, side, itemstack) && entityplayer.canPlayerEdit(x, y + 1, z, side, itemstack))
            {
                if (!var11.canPlaceBlockAt(world, x, y, z))
                {
                    return false;
                }
                else
                {
                    int var12 = MathHelper.floor_double((double)((entityplayer.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    func_150924_a(world, x, y, z, var12, var11);
                    --itemstack.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static void func_150924_a(World world, int x, int y, int z, int side, Block block)
    {
        byte var6 = 0;
        byte var7 = 0;

        if (side == 0)
        {
            var7 = 1;
        }

        if (side == 1)
        {
            var6 = -1;
        }

        if (side == 2)
        {
            var7 = -1;
        }

        if (side == 3)
        {
            var6 = 1;
        }

        int var8 = (world.getBlock(x - var6, y, z - var7).isNormalCube() ? 1 : 0) + (world.getBlock(x - var6, y + 1, z - var7).isNormalCube() ? 1 : 0);
        int var9 = (world.getBlock(x + var6, y, z + var7).isNormalCube() ? 1 : 0) + (world.getBlock(x + var6, y + 1, z + var7).isNormalCube() ? 1 : 0);
        boolean var10 = world.getBlock(x - var6, y, z - var7) == block || world.getBlock(x - var6, y + 1, z - var7) == block;
        boolean var11 = world.getBlock(x + var6, y, z + var7) == block || world.getBlock(x + var6, y + 1, z + var7) == block;
        boolean var12 = false;

        if (var10 && !var11)
        {
            var12 = true;
        }
        else if (var9 > var8)
        {
            var12 = true;
        }

        world.setBlock(x, y, z, block, side, 2);
        world.setBlock(x, y + 1, z, block, 8 | (var12 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block);
    }
    
}
