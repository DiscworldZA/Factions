package disc.mods.factions.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class FactionCapabilityProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IFactionCapability.class)
    public static final Capability<IFactionCapability> FACTIONCAP = null;

    private IFactionCapability instance = FACTIONCAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == FACTIONCAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == FACTIONCAP ? FACTIONCAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return FACTIONCAP.getStorage().writeNBT(FACTIONCAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        FACTIONCAP.getStorage().readNBT(FACTIONCAP, this.instance, null, nbt);
    }

}
