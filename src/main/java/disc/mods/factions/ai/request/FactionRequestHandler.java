package disc.mods.factions.ai.request;

import disc.mods.factions.ai.task.CraftingTask;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.faction.Faction;
import disc.mods.factions.faction.buildings.JsonBuilding;
import disc.mods.factions.faction.buildings.JsonBuilding.FunctionalBlock;
import disc.mods.factions.registry.Registries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class FactionRequestHandler
{
    private Faction faction;

    public FactionRequestHandler(Faction faction)
    {
        this.faction = faction;
    }

    public EntityLivingAI findCrafterFor(String Id)
    {
        for (EntityLivingAI ai : faction.getAIs())
        {
            if (Registries.CrafterRegistry.get(ai).hasResult(Id))
            {
                return ai;
            }
        }
        return null;
    }

    public void requestItem(String Id)
    {
        EntityLivingAI ai = findCrafterFor(Id);
        if (ai == null)
            System.out.println("Unable to find crafter");
        JsonBuilding building = Registries.BuildingRegistry.get(ai.getFactionBuilding());
        BlockPos craftingBench = building.getFunctionalBlockPos(FunctionalBlock.Crafting, faction.factionTile.getPos());
        BlockPos storage = building.getFunctionalBlockPos(FunctionalBlock.Storage, faction.factionTile.getPos());
        ai.factionTasks.addTask(new CraftingTask(ai, new ItemStack(Item.getByNameOrId(Id)), craftingBench, storage));
    }
}
