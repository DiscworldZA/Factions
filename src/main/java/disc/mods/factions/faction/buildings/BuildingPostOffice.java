package disc.mods.factions.faction.buildings;

import disc.mods.factions.entity.EntityDeliveryMan;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.tileentity.TileEntityBuilding;

public class BuildingPostOffice extends FactionBuilding
{
    public BuildingPostOffice(TileEntityBuilding tile)
    {
        super(tile);
    }

    @Override
    public void update()
    {

    }

    @Override
    public EntityLivingAI getAIType()
    {
        return new EntityDeliveryMan(tile.getWorld());
    }

}
