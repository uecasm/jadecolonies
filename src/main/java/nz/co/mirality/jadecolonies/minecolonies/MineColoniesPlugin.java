package nz.co.mirality.jadecolonies.minecolonies;

import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding;
import com.minecolonies.coremod.blocks.BlockDecorationController;
import com.minecolonies.coremod.tileentities.TileEntityDecorationController;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;

public class MineColoniesPlugin implements IWailaPlugin
{
    @Override
    public void register(final IWailaCommonRegistration registration)
    {
        registration.registerBlockDataProvider(BuildingNameComponentProvider.getInstance(), AbstractTileEntityColonyBuilding.class);
        registration.registerBlockDataProvider(BuildingPackComponentProvider.getInstance(), AbstractTileEntityColonyBuilding.class);
        registration.registerBlockDataProvider(BuildingNameComponentProvider.getInstance(), TileEntityDecorationController.class);
        registration.registerBlockDataProvider(BuildingPackComponentProvider.getInstance(), TileEntityDecorationController.class);
    }

    @Override
    public void registerClient(final IWailaClientRegistration registration)
    {
        registration.registerBlockComponent(BuildingNameComponentProvider.getInstance(), AbstractBlockHut.class);
        registration.registerBlockComponent(BuildingPackComponentProvider.getInstance(), AbstractBlockHut.class);
        registration.registerBlockComponent(BuildingNameComponentProvider.getInstance(), BlockDecorationController.class);
        registration.registerBlockComponent(BuildingPackComponentProvider.getInstance(), BlockDecorationController.class);
        registration.registerBlockComponent(CitizenListComponentProvider.getInstance(), AbstractBlockHut.class);

        registration.registerEntityComponent(ColonistComponentProvider.getInstance(), AbstractEntityCitizen.class);
        registration.registerEntityComponent(VisitorRecruitmentComponentProvider.getInstance(), AbstractEntityCitizen.class);
    }
}
