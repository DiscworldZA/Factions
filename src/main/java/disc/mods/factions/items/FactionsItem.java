package disc.mods.factions.items;

import disc.mods.core.items.CoreItem;
import disc.mods.factions.Factions;
import disc.mods.factions.creativetab.Tabs;

public class FactionsItem extends CoreItem
{

    public FactionsItem(String name)
    {
        super(name);
        setCreativeTab(Tabs.Factions);
    }

    public void registerItemModel()
    {
        Factions.proxy.registerItemRenderer(this, 0);
    }
    
}
