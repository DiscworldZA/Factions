package disc.mods.factions.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Tabs
{
    public static final CreativeTabs Factions = new CreativeTabs(
            "factions.Factions")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Items.ARROW);
        }

        @Override
        public boolean hasSearchBar()
        {
            return true;
        }
    };
}
