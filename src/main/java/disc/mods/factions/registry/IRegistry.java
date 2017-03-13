package disc.mods.factions.registry;

public interface IRegistry<T extends IRegisterable, O extends IRegistryProperty>
{
    void Register(Class<? extends T> registerClass, Class<? extends O> container);

    O get(Class<? extends T> registerClass);

    O get(T register);
}
