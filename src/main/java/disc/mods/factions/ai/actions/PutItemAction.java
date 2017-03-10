package disc.mods.factions.ai.actions;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.utils.InventoryHelper;
import disc.mods.factions.utils.NavigationHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class PutItemAction extends AIAction
{
    private BlockPos toInventory;
    private ItemStack stackToPut;
    private boolean done = false;

    private int tick = 0;

    public PutItemAction(EntityLivingAI entity, BlockPos pos, ItemStack item)
    {
        super(entity);
        toInventory = pos;
        stackToPut = item;
    }

    @Override
    public boolean shouldExecute()
    {
        return NavigationHelper.IsEntityCloseTo(handler, toInventory);
    }

    @Override
    public void startExecuting()
    {
        TileEntity tile = handler.getEntityWorld().getTileEntity(toInventory);
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
    public void updateAction()
    {
        tick++;
        NavigationHelper.MakeEntityLookAt(handler, toInventory);
        if (tick == 20 && !done)
        {
            TileEntity tile = handler.getEntityWorld().getTileEntity(toInventory);
            if (tile instanceof IInventory)
            {
                IInventory inventory = (IInventory) tile;
                for (int i = 0; i < handler.inventory.getSizeInventory(); i++)
                {
                    ItemStack stackInv = handler.inventory.getStackInSlot(i);
                    if (stackInv.isItemEqual(stackToPut))
                    {
                        if (stackToPut.getCount() > stackInv.getCount())
                        {
                            stackToPut.setCount(stackToPut.getCount() - stackInv.getCount());
                            InventoryHelper.addToInventory(inventory, stackInv);
                            handler.inventory.removeStackFromSlot(i);
                            if (stackToPut.getCount() == 0)
                            {
                                done = true;
                                break;
                            }
                        }
                        else
                        {
                            handler.inventory.decrStackSize(i, stackToPut.getCount());
                            InventoryHelper.addToInventory(inventory, stackToPut);
                            done = true;
                        }
                    }
                }
            }
            tick = 0;
        }
    }

    @Override
    public void resetAction()
    {
        TileEntity tile = handler.getEntityWorld().getTileEntity(toInventory);
        if (tile instanceof IInventory)
        {
            IInventory inventory = (IInventory) tile;
            inventory.closeInventory(FakePlayerFactory.getMinecraft((WorldServer) handler.world));
        }
    }

}
