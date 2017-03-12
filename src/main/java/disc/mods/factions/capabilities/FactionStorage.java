package disc.mods.factions.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class FactionStorage implements IStorage<IFactionCapability>
{
    @Override
    public NBTBase writeNBT(Capability<IFactionCapability> capability, IFactionCapability instance, EnumFacing side)
    {
        return new NBTTagString(instance.getFactionName());
    }

    @Override
    public void readNBT(Capability<IFactionCapability> capability, IFactionCapability instance, EnumFacing side, NBTBase nbt)
    {
        instance.setFactionName(((NBTTagString) nbt).getString());
    }
}
