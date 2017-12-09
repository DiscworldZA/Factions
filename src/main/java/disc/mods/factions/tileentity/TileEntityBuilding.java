package disc.mods.factions.tileentity;

import disc.mods.factions.faction.Faction;
import disc.mods.factions.faction.FactionHandler;
import disc.mods.factions.faction.buildings.FactionBuilding;
import disc.mods.factions.ref.Names;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityBuilding extends FactionsTileEntity
{
    protected FactionBuilding building;
    private String factionName = "";

    public TileEntityBuilding()
    {
    }

    public FactionBuilding getFactionBuilding()
    {
        return building;
    }

    @Override
    public void update()
    {
        if (building.faction != null)
        {
            building.update();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound.hasKey(Names.NBT.FactionName))
        {
            factionName = nbtTagCompound.getString(Names.NBT.FactionName);
            building.faction = FactionHandler.getFaction(factionName);
        }
        super.readFromNBT(nbtTagCompound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        if (building.faction != null)
            nbtTagCompound.setString(Names.NBT.FactionName, building.faction.Name);
        return super.writeToNBT(nbtTagCompound);
    }

}
