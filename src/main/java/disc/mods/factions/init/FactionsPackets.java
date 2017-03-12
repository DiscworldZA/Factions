package disc.mods.factions.init;

import disc.mods.factions.Factions;
import disc.mods.factions.network.packet.FactionCapabilityPacket;
import net.minecraftforge.fml.relauncher.Side;

public class FactionsPackets
{
    public static void init()
    {
        Factions.getNetwork().RegisterMessage(FactionCapabilityPacket.class, FactionCapabilityPacket.class, Side.CLIENT);
    }
}
