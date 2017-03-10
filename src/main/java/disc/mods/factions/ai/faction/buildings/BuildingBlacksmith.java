package disc.mods.factions.ai.faction.buildings;

import disc.mods.factions.ai.faction.Faction;
import disc.mods.factions.entity.EntityBuilder;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.tileentity.TileEntityBuilding;

public class BuildingBlacksmith extends FactionBuilding
{
    public BuildingBlacksmith(TileEntityBuilding building)
    {
        super(building);
    }

    @Override
    public void update()
    {
    }

    @Override
    public EntityLivingAI getAIType()
    {
        return new EntityBuilder(tile.getWorld());
    }

}
