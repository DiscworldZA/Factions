package disc.mods.factions.capabilities;

import disc.mods.factions.Factions;
import disc.mods.factions.faction.Faction;
import disc.mods.factions.faction.FactionHandler;
import disc.mods.factions.integration.jei.FactionsJEI;
import disc.mods.factions.network.packet.FactionCapabilityPacket;
import disc.mods.factions.ref.Names.NBT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class FactionCapability implements IFactionCapability
{
    private String factionName = "";

    @Override
    public IFactionCapability setFactionName(String factionName)
    {
        this.factionName = factionName;
        return this;
    }

    @Override
    public String getFactionName()
    {
        return factionName;
    }

    @Override
    public Faction getFaction()
    {
        return FactionHandler.getFaction(factionName);
    }

    @Override
    public boolean hasFaction()
    {
        return !factionName.isEmpty();
    }

    @Override
    public IFactionCapability sync(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            Factions.getInstance().getNetwork().SendToEntity(new FactionCapabilityPacket(factionName), (EntityPlayerMP) player);
        }
        return this;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString(NBT.FactionName, factionName);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        factionName = nbt.getString(NBT.FactionName);
    }

}
