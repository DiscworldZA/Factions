package disc.mods.factions.ai.task;

import java.util.List;

import disc.mods.factions.ai.actions.GetItemAction;
import disc.mods.factions.ai.actions.PutItemAction;
import disc.mods.factions.ai.actions.TravelAction;
import disc.mods.factions.entity.EntityLivingAI;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class CraftingTask extends AITask
{
    private BlockPos craftingBench;
    private BlockPos chest;
    private ItemStack stackToCraft;
    private List<ItemStack> requiredItems;

    public CraftingTask(EntityLivingAI entity, ItemStack itemStack, BlockPos bench, BlockPos chest)
    {
        super(entity);
        this.craftingBench = bench;
        this.chest = chest;
        this.stackToCraft = itemStack;
    }

    @Override
    public void queueActions()
    {
        this.taskActions.addAction(new TravelAction(handler, chest));
        this.taskActions.addAction(new GetItemAction(handler, chest, stackToCraft));
        this.taskActions.addAction(new TravelAction(handler, craftingBench));
        this.taskActions.addAction(new TravelAction(handler, chest));
        this.taskActions.addAction(new PutItemAction(handler, chest, stackToCraft));
    }

    @Override
    public boolean shouldExecute()
    {
        return true;
    }

    @Override
    public void updateTask()
    {
        
    }

}
