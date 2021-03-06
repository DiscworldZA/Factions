package disc.mods.factions.inventory;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.Names;
import disc.mods.factions.utils.InventoryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryAI implements IInventory
{
    protected EntityLivingAI entity;
    protected NonNullList<ItemStack> inventory;

    private boolean inventoryChanged;

    public InventoryAI(EntityLivingAI entity, int size)
    {
        this.entity = entity;
        inventory = NonNullList.<ItemStack> withSize(size, ItemStack.EMPTY);
    }

    public void setHeldItem(EntityEquipmentSlot slot, ItemStack item)
    {
        ItemStack stackInSlot = entity.getItemStackFromSlot(slot);
        if (!stackInSlot.isEmpty())
        {
            InventoryHelper.Add(this, stackInSlot);
            clearHeldItem(slot);
        }

        for (ItemStack itemStack : inventory)
        {
            if (itemStack.isItemEqual(item))
            {
                entity.setItemStackToSlot(slot, item);
                remove(itemStack);
                break;
            }
        }
    }

    public void clearHeldItem(EntityEquipmentSlot slot)
    {
        entity.setItemStackToSlot(slot, ItemStack.EMPTY);
    }

    public void addToInventory(ItemStack stack)
    {
        InventoryHelper.Add(this, stack);
    }
    
    public void addToInventory(NonNullList<ItemStack> stacks)
    {
    	for(int i = 0; i < stacks.size(); i++)
    	{
    		addToInventory(stacks.get(0));
    	}
    }

    public void remove(ItemStack itemStack)
    {
        InventoryHelper.Remove(this, itemStack);
    }

    public boolean hasItem(ItemStack stack)
    {
        for (int i = 0; i < this.getSizeInventory(); i++)
        {
            if (getStackInSlot(i).isItemEqual(stack))
            {
                return true;
            }
        }
        return false;
    }

    public ItemStack oreDictMatch(NonNullList<ItemStack> itemStacks)
    {
        for (ItemStack stack : itemStacks)
        {
            if (hasItem(stack))
            {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public String getName()
    {
        return Names.Inventory.AI + "." + entity.getName();
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return (ITextComponent) (this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.size();
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return inventory.get(index).isEmpty() ? ItemStackHelper.getAndSplit(inventory, index, count) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return inventory.set(index, ItemStack.EMPTY);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        inventory.set(index, stack);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty()
    {
        inventoryChanged = true;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {
    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        ItemStackHelper.saveAllItems(compound, this.inventory);
    }

}
