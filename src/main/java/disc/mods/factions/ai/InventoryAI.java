package disc.mods.factions.ai;

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
    protected EntityLivingAI aiEntity;
    protected NonNullList<ItemStack> inventory;

    private boolean inventoryChanged;

    public InventoryAI(EntityLivingAI entity, int size)
    {
        aiEntity = entity;
        inventory = NonNullList.<ItemStack> withSize(size, ItemStack.EMPTY);
    }

    public void setHeldItem(EntityEquipmentSlot slot, ItemStack item)
    {
        ItemStack stackInSlot = aiEntity.getItemStackFromSlot(slot);
        if (!stackInSlot.isEmpty())
        {
            InventoryHelper.addToInventory(this, stackInSlot);
            clearHeldItem(slot);
        }

        for (ItemStack itemStack : inventory)
        {
            if (itemStack.isItemEqual(item))
            {
                aiEntity.setItemStackToSlot(slot, item);
                removeFromInventory(itemStack);
                break;
            }
        }
    }

    public void clearHeldItem(EntityEquipmentSlot slot)
    {
        aiEntity.setItemStackToSlot(slot, ItemStack.EMPTY);
    }

    public void removeFromInventory(ItemStack itemStack)
    {
        inventory.remove(itemStack);
    }

    @Override
    public String getName()
    {
        return Names.Inventory.AI + "." + aiEntity.getName();
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
