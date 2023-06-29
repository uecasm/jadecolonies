package nz.co.mirality.jadecolonies;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import nz.co.mirality.jadecolonies.domum_ornamentum.DomumOrnamentumPlugin;
import nz.co.mirality.jadecolonies.minecolonies.MineColoniesPlugin;
import snownee.jade.api.IWailaPlugin;

import java.util.function.Supplier;

@Mod(JadeColonies.ID)
@Mod.EventBusSubscriber(modid = JadeColonies.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JadeColonies
{
    public static final String ID = "jadecolonies";

    public static final String DOMUM_ID = com.ldtteam.domumornamentum.util.Constants.MOD_ID;
    public static final String COLONY_ID = com.minecolonies.api.util.constant.Constants.MOD_ID;

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event)
    {
        addPlugin(DOMUM_ID, DomumOrnamentumPlugin::new);
        addPlugin(COLONY_ID, MineColoniesPlugin::new);
    }

    private static void addPlugin(final String id, final Supplier<IWailaPlugin> supplier)
    {
        if (ModList.get().isLoaded(id))
        {
            JadePlugin.PLUGINS.add(supplier.get());
        }
    }
}
