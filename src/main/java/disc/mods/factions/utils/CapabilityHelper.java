package disc.mods.factions.utils;

import disc.mods.factions.capabilities.FactionCapabilityProvider;
import disc.mods.factions.capabilities.IFactionCapability;
import net.minecraft.entity.player.EntityPlayer;

public class CapabilityHelper
{
    public static IFactionCapability getFactionCapability(EntityPlayer player)
    {
        return player.getCapability(FactionCapabilityProvider.FACTIONCAP, null);
    }
}
