package disc.mods.factions.ai.action;

import disc.mods.factions.ai.task.AITask;
import disc.mods.factions.entity.EntityLivingAI;
import disc.mods.factions.ref.FactionsSettings;
import disc.mods.factions.utils.NavigationHelper;
import net.minecraft.entity.player.EntityPlayer;

public class FollowAction extends AIAction
{
    private EntityPlayer player;

    private double followDistance = 6.25D;

    public FollowAction setPlayer(EntityPlayer player)
    {
        this.player = player;
        return this;
    }

    @Override
    public boolean shouldExecute()
    {
        return (player != null) && FactionsSettings.AI.CanAIMove.Value;
    }

    @Override
    public boolean continueExecuting()
    {
        return player != null;
    }

    @Override
    public boolean updateAction()
    {
        if (player != null)
        {
            handler.getLookHelper().setLookPositionWithEntity(player, (float) (handler.getHorizontalFaceSpeed() + 20), (float) handler.getVerticalFaceSpeed());

            if (handler.getDistanceSqToEntity(player) < followDistance)
            {
                handler.getNavigator().clearPathEntity();
            }
            else
            {
                NavigationHelper.NavigateEntityTo(handler, player.getPosition());
            }
        }
        return true;
    }

    @Override
    public void resetAction()
    {
        player = null;
        handler.getNavigator().clearPathEntity();
    }

    @Override
    public void startExecuting()
    {
        
    }

}
