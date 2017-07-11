package disc.mods.factions.ai.action;

import disc.mods.factions.ai.crafting.FactionCrafter;
import disc.mods.factions.ai.crafting.recipes.JsonRecipe;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.registry.Registries;
import net.minecraft.item.ItemStack;

public class CraftingAction extends AIAction
{
    private ItemStack craft;
    private String craftName;

    public CraftingAction(EntityLivingAI entity, ItemStack stackToCraft)
    {
        super(entity);
        craft = stackToCraft;
        craftName = stackToCraft.getItem().getRegistryName().toString();
    }

    @Override
    public boolean shouldExecute()
    {
        return Registries.CrafterRegistry.get(handler).hasRecipe(craftName);
    }

    @Override
    public void startExecuting()
    {
        FactionCrafter crafter = Registries.CrafterRegistry.get(handler);
        JsonRecipe recipe = crafter.getRecipe(craftName);
        for (ItemStack stack : recipe.getIngredients(handler.inventory))
        {
            handler.inventory.remove(stack);
        }
        handler.inventory.addToInventory(recipe.getResult());
    }

    @Override
    public boolean updateAction()
    {
        return true;
    }

}
