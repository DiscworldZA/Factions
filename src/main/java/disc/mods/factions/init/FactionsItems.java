package disc.mods.factions.init;

import disc.mods.core.init.CoreItems;
import disc.mods.core.items.CoreItem;
import disc.mods.factions.items.ItemController;

public class FactionsItems extends CoreItems
{
    public static final CoreItem itemController = new ItemController();
    
    public static void init()
    {
        register(itemController);
    }
}
