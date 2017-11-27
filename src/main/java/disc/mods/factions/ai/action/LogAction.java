package disc.mods.factions.ai.action;

import disc.mods.factions.Factions;

public class LogAction extends AIAction
{
    public String msg;
    
    public LogAction(String msg)
    {
        this.msg = msg;
    }
    
    @Override
    public boolean continueExecuting()
    {
        return false;
    }
    
    @Override
    public void startExecuting()
    {
        Factions.logger.info(msg);
    }
    
    @Override
    public boolean shouldExecute()
    {
        return true;
    }

    @Override
    public boolean updateAction()
    {
        return false;
    }
}
