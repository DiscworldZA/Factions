package disc.mods.factions.ai.faction;

import java.util.HashMap;
import java.util.Map;

import disc.mods.factions.tileentity.TileEntityFaction;
import net.minecraft.nbt.NBTTagCompound;

public class FactionHandler
{
    private static Map<String, Faction> factions = new HashMap<String, Faction>();

    public static Faction newFaction(TileEntityFaction tile, String Name)
    {
        Faction f = new Faction(Name, tile);
        factions.put(Name, f);
        return f;
    }

    public static Faction getFaction(String Name)
    {
        return factions.get(Name);
    }
}
