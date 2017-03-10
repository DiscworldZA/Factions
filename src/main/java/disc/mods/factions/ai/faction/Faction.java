package disc.mods.factions.ai.faction;

import java.util.ArrayList;
import java.util.List;

import disc.mods.factions.ai.faction.buildings.FactionBuilding;
import disc.mods.factions.ai.request.FactionRequestHandler;
import disc.mods.factions.ref.Names;
import disc.mods.factions.tileentity.TileEntityFaction;
import net.minecraft.nbt.NBTTagCompound;

public class Faction
{
    private List<FactionBuilding> buildings = new ArrayList<FactionBuilding>();
    public FactionRequestHandler RequestHandler;
    protected TileEntityFaction factionTile;
    public String Name;

    public Faction(String Name, TileEntityFaction tile)
    {
        factionTile = tile;
        this.Name = Name;
        RequestHandler = new FactionRequestHandler(this);
    }

    public void update()
    {
        
    }
}
