package disc.mods.factions.ai.queue;

public interface IQueueable<T extends IQueueHandler>
{
    public void setHandler(T handler);
}
