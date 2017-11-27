package disc.mods.factions.ai.task;

import disc.mods.factions.ai.action.CraftingAction;
import disc.mods.factions.ai.action.GetItemsAction;
import disc.mods.factions.ai.action.PutItemsAction;
import disc.mods.factions.ai.action.TravelAction;
import disc.mods.factions.ai.crafting.recipes.JsonRecipe;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.registry.Registries;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class CraftingTask extends AITask
{
    private BlockPos craftingBench;
    private BlockPos chest;
    private ItemStack stackToCraft;
    private JsonRecipe recipe;

    public CraftingTask(EntityLivingAI entity, ItemStack itemStack, BlockPos bench, BlockPos chest)
    {
        craftingBench = bench;
        this.chest = chest;
        stackToCraft = itemStack;
        recipe = Registries.CrafterRegistry.get(entity).getRecipe(itemStack.getItem().getRegistryName().toString());
    }

    @Override
    public void queueActions()
    {
        taskActions.add(new TravelAction(chest));
        taskActions.add(new GetItemsAction(chest, recipe.getIngredients(handler.inventory)));
        taskActions.add(new TravelAction(craftingBench));
        taskActions.add(new CraftingAction(stackToCraft));
        taskActions.add(new TravelAction(chest));
        taskActions.add(new PutItemsAction(chest, recipe.getResult()));
    }

    @Override
    public boolean shouldExecute()
    {
        return true;
    }

    @Override
    public boolean canBeFullfilled()
    {
        return true;
    }

}
