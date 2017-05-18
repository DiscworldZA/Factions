package disc.mods.factions.registry;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Registry<T, O> implements IRegistry<T, O>
{
    private Map<Class<? extends T>, O> registry = new HashMap<Class<? extends T>, O>();

    @Override
    public void Register(Class<? extends T> registerClass, Class<? extends O> container)
    {
        try
        {
            registry.put(registerClass, (O) container.getConstructors()[0].newInstance(registerClass));
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public O get(Class<? extends T> registerClass)
    {
        return registry.get(registerClass);
    }

    @Override
    public O get(T register)
    {
        return registry.get(register.getClass());
    }

}
