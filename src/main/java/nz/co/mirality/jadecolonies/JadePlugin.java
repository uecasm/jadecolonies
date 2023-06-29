package nz.co.mirality.jadecolonies;

import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

import java.util.ArrayList;
import java.util.List;

@WailaPlugin
public class JadePlugin implements IWailaPlugin
{
    static List<IWailaPlugin> PLUGINS = new ArrayList<>();

    @Override
    public void register(final IWailaCommonRegistration registration)
    {
        for (final IWailaPlugin plugin : PLUGINS)
        {
            plugin.register(registration);
        }
    }

    @Override
    public void registerClient(final IWailaClientRegistration registration)
    {
        for (final IWailaPlugin plugin : PLUGINS)
        {
            plugin.registerClient(registration);
        }
    }
}
