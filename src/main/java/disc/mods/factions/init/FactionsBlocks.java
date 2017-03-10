package disc.mods.factions.init;

import disc.mods.core.block.CoreBlock;
import disc.mods.core.init.CoreBlocks;
import disc.mods.factions.block.BlockBlacksmith;
import disc.mods.factions.block.BlockFaction;
import disc.mods.factions.block.BlockHouse;
import disc.mods.factions.ref.Names;

public class FactionsBlocks extends CoreBlocks
{
    public static final CoreBlock Faction = new BlockFaction(Names.Blocks.Faction);
    public static final CoreBlock House = new BlockHouse(Names.Blocks.House);
    public static final CoreBlock Blacksmith = new BlockBlacksmith(Names.Blocks.Blacksmith);

    public static void init()
    {
        register(Faction);
        register(House);
        register(Blacksmith);
    }
}
