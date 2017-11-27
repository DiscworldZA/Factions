package disc.mods.factions.utils;

import disc.mods.factions.inventory.InventoryAI;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InventoryHelper
{

    public static ItemStack Add(IInventory inventoryAI, ItemStack itemStack)
    {
        for (int i = 0; i < inventoryAI.getSizeInventory(); i++)
        {
            ItemStack stackInInv = inventoryAI.getStackInSlot(i);
            if (stackInInv.isEmpty())
            {
                inventoryAI.setInventorySlotContents(i, itemStack);
                break;
            }
            else if (stackInInv.isItemEqual(itemStack))
            {
                if (stackInInv.getCount() + itemStack.getCount() > stackInInv.getMaxStackSize())
                {
                    itemStack.setCount(itemStack.getCount() - stackInInv.getCount());
                    stackInInv.setCount(stackInInv.getMaxStackSize());
                    continue;
                }
                else
                {
                    stackInInv.setCount(stackInInv.getCount() + itemStack.getCount());
                    itemStack.setCount(0);
                    break;
                }
            }
        }
        return itemStack.isEmpty() ? ItemStack.EMPTY : itemStack;
    }

    public static ItemStack Remove(InventoryAI inventoryAI, ItemStack itemStack)
    {
        for (int i = 0; i < inventoryAI.getSizeInventory(); i++)
        {
            ItemStack stackInInv = inventoryAI.getStackInSlot(i);
            if (stackInInv.isEmpty())
                continue;
            else if (stackInInv.isItemEqual(itemStack))
            {
                if (stackInInv.getCount() - itemStack.getCount() < 0)
                {
                    itemStack.setCount(itemStack.getCount() - stackInInv.getCount());
                    stackInInv.setCount(0);
                    continue;
                }
                else
                {
                    itemStack.setCount(0);
                    stackInInv.setCount(stackInInv.getCount() - itemStack.getCount());
                    break;
                }
            }
        }
        return itemStack.isEmpty() ? ItemStack.EMPTY : itemStack;
    }
}
