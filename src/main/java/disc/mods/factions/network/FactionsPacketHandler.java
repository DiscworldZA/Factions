package disc.mods.factions.network;

import disc.mods.core.network.CorePacketHandler;
import disc.mods.factions.ref.References;

public class FactionsPacketHandler extends CorePacketHandler
{
    @Override
    public String getModId()
    {
        return References.Mod.Id;
    }

}
