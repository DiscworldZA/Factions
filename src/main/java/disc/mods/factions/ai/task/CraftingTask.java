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
        super(entity);
        craftingBench = bench;
        this.chest = chest;
        stackToCraft = itemStack;
        recipe = Registries.CrafterRegistry.get(entity).getRecipe(itemStack.getItem().getRegistryName().toString());
        queueActions();
    }

    @Override
    public void queueActions()
    {
        taskActions.addAction(new TravelAction(chest));
        taskActions.addAction(new GetItemsAction(chest, recipe.getIngredients(handler.inventory)));
        taskActions.addAction(new TravelAction(craftingBench));
        taskActions.addAction(new CraftingAction(stackToCraft));
        taskActions.addAction(new TravelAction(chest));
        taskActions.addAction(new PutItemsAction(chest, recipe.getResult()));
    }

    @Override
    public boolean shouldExecute()
    {
        return true;
    }

}
