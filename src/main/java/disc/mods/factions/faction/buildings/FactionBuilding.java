package disc.mods.factions.faction.buildings;

import java.util.ArrayList;
import java.util.List;

import disc.mods.core.utils.WorldHelper;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.faction.Faction;
import disc.mods.factions.tileentity.TileEntityBuilding;

public abstract class FactionBuilding
{
    public Faction faction;
    protected TileEntityBuilding tile;
    protected List<EntityLivingAI> ais = new ArrayList<EntityLivingAI>();

    public FactionBuilding(TileEntityBuilding tile)
    {
        this.tile = tile;
    }

    public void notifyAiLoaded(EntityLivingAI ai)
    {
        ais.add(ai);
    }

    public void spawnTestEntity()
    {
        EntityLivingAI entity = getAIType();
        entity.buildingBlockPos = tile.getPos();
        WorldHelper.SpawnEntity(entity, tile.getWorld(), tile.getPos().add(0, 1, 0));
    }

    public abstract void update();

    public abstract EntityLivingAI getAIType();
}
