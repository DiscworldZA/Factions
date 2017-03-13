package disc.mods.factions.registry;

import disc.mods.factions.ai.crafting.FactionCrafter;
import disc.mods.factions.entity.EntityBlacksmith;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.entity.EntityLumberjack;
import disc.mods.factions.faction.buildings.BuildingBlacksmith;
import disc.mods.factions.faction.buildings.BuildingLumberJack;
import disc.mods.factions.faction.buildings.BuildingPostOffice;
import disc.mods.factions.faction.buildings.FactionBuilding;
import disc.mods.factions.faction.buildings.JsonBuilding;

public class Registries
{
    public static Registry<FactionBuilding, JsonBuilding> BuildingRegistry = new Registry<FactionBuilding, JsonBuilding>();
    public static Registry<EntityLivingAI, FactionCrafter> CrafterRegistry = new Registry<EntityLivingAI, FactionCrafter>();

    public static void init()
    {
        // Buildings
        BuildingRegistry.Register(BuildingBlacksmith.class, JsonBuilding.class);
        BuildingRegistry.Register(BuildingLumberJack.class, JsonBuilding.class);
        BuildingRegistry.Register(BuildingPostOffice.class, JsonBuilding.class);

        // Crafters
        CrafterRegistry.Register(EntityBlacksmith.class, FactionCrafter.class);
        CrafterRegistry.Register(EntityLumberjack.class, FactionCrafter.class);
    }
}
