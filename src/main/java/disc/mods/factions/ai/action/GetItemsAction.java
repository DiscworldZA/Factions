package disc.mods.factions.ai.action;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.utils.InventoryHelper;
import disc.mods.factions.utils.NavigationHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class GetItemsAction extends AIAction
{
    private BlockPos fromInventory;
    private NonNullList<ItemStack> stacksToGet;
    private boolean done;

    private int tick;

    public GetItemsAction(BlockPos pos, NonNullList<ItemStack> item)
    {
        fromInventory = pos;
        stacksToGet = item;
    }

    @Override
    public boolean shouldExecute()
    {
        return NavigationHelper.IsEntityCloseTo(handler, fromInventory);
    }

    @Override
    public void startExecuting()
    {
        TileEntity tile = handler.getEntityWorld().getTileEntity(fromInventory);
        if (tile instanceof IInventory)
        {
            IInventory inventory = (IInventory) tile;
            inventory.openInventory(FakePlayerFactory.getMinecraft((WorldServer) handler.world));
        }
    }

    @Override
    public boolean continueExecuting()
    {
        return !done;
    }

    @Override
    public boolean updateAction()
    {
        tick++;
        NavigationHelper.MakeEntityLookAt(handler, fromInventory);
        if (tick == 20 && !done)
        {

            TileEntity tile = handler.getEntityWorld().getTileEntity(fromInventory);
            if (tile instanceof IInventory)
            {
                for (ItemStack stackToGet : stacksToGet)
                {
                    IInventory inventory = (IInventory) tile;
                    for (int i = 0; i < inventory.getSizeInventory(); i++)
                    {
                        ItemStack stackInv = inventory.getStackInSlot(i);
                        if (stackInv.isItemEqual(stackToGet))
                        {
                            if (stackToGet.getCount() > stackInv.getCount())
                            {
                                stackToGet.setCount(stackToGet.getCount() - stackInv.getCount());
                                InventoryHelper.Add(handler.inventory, stackInv);
                                inventory.removeStackFromSlot(i);
                                if (stackToGet.getCount() == 0)
                                {
                                    done = true;
                                    break;
                                }
                            }
                            else
                            {
                                inventory.decrStackSize(i, stackToGet.getCount());
                                InventoryHelper.Add(handler.inventory, stackToGet);
                                done = true;
                            }
                        }
                    }
                }
                return false;
            }
            tick = 0;
        }
        return true;
    }

    @Override
    public void resetAction()
    {
        TileEntity tile = handler.getEntityWorld().getTileEntity(fromInventory);
        if (tile instanceof IInventory)
        {
            IInventory inventory = (IInventory) tile;
            inventory.closeInventory(FakePlayerFactory.getMinecraft((WorldServer) handler.world));
        }
    }

}
