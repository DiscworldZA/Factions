package disc.mods.factions.ai.action;

import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.utils.NavigationHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TravelAction extends AIAction
{
    public TravelAction(EntityLivingAI entity, BlockPos pos)
    {
        super(entity);
        destination = pos;
    }

    private BlockPos destination;

    private boolean isTraveling()
    {
        return destination != null;
    }

    @Override
    public boolean shouldExecute()
    {
        return destination != null;
    }

    @Override
    public boolean updateAction()
    {
        if (isTraveling())
        {
            NavigationHelper.NavigateEntityTo(handler, destination);
            if (NavigationHelper.IsEntityCloseTo(handler, destination))
            {
                destination = null;
                handler.getNavigator().clearPathEntity();
            }
        }
        return true;
    }

    @Override
    public boolean continueExecuting()
    {
        return isTraveling();
    }

}
