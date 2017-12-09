package disc.mods.factions.utils.queue;

public interface IQueueable<T extends IQueueHandler>
{
    public void setHandler(T handler);
}
