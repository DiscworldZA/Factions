package disc.mods.factions.faction;

import java.util.ArrayList;
import java.util.List;

import disc.mods.factions.ai.request.FactionRequestHandler;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.faction.buildings.FactionBuilding;
import disc.mods.factions.tileentity.TileEntityFaction;

public class Faction
{
    public List<FactionBuilding> buildings = new ArrayList<FactionBuilding>();
    public FactionRequestHandler RequestHandler;
    public TileEntityFaction factionTile;
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

    public List<EntityLivingAI> getAIs()
    {
        List<EntityLivingAI> entities = new ArrayList<EntityLivingAI>();
        for(FactionBuilding b : buildings)
        {
            entities.add(b.getAIType());
        }
        return entities;
    }
}
