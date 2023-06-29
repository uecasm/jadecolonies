package nz.co.mirality.jadecolonies.domum_ornamentum;

import net.minecraft.world.level.block.Block;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;

public class DomumOrnamentumPlugin implements IWailaPlugin
{
    @Override
    public void registerClient(final IWailaClientRegistration registration)
    {
        registration.registerBlockComponent(DomumBlockComponentProvider.getInstance(), Block.class);
    }
}
