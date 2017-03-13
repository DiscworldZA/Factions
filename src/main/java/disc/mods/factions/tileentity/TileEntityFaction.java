package disc.mods.factions.tileentity;

import disc.mods.factions.faction.Faction;
import disc.mods.factions.faction.FactionHandler;
import disc.mods.factions.faction.buildings.FactionBuilding;
import disc.mods.factions.ref.Names;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityFaction extends FactionsTileEntity
{
    public Faction faction;
    private String factionName = "";

    public TileEntityFaction()
    {
    }

    public Faction getFaction()
    {
        return faction;
    }

    @Override
    public void update()
    {
        if (faction != null)
        {
            faction.update();
        }
        else if(!factionName.isEmpty())
        {
            faction = FactionHandler.newFaction(this, factionName);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound.hasKey(Names.NBT.FactionName))
        {
            factionName = nbtTagCompound.getString(Names.NBT.FactionName);
            faction = FactionHandler.getFaction(factionName);
        }
        super.readFromNBT(nbtTagCompound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        if (faction != null)
            nbtTagCompound.setString(Names.NBT.FactionName, faction.Name);
        return super.writeToNBT(nbtTagCompound);
    }

}
