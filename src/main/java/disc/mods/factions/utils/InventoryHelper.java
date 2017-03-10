package disc.mods.factions.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class InventoryHelper
{

    public static ItemStack addToInventory(IInventory inventory, ItemStack itemStack)
    {
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack stackInInv = inventory.getStackInSlot(i);
            if (stackInInv.isEmpty())
            {
                inventory.setInventorySlotContents(i, itemStack);
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
}
