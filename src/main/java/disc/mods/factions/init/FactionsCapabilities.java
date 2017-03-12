package disc.mods.factions.init;

import disc.mods.factions.capabilities.FactionCapability;
import disc.mods.factions.capabilities.FactionStorage;
import disc.mods.factions.capabilities.IFactionCapability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class FactionsCapabilities
{
    public static void init()
    {
        CapabilityManager.INSTANCE.register(IFactionCapability.class, new FactionStorage(), FactionCapability.class);
    }
}
