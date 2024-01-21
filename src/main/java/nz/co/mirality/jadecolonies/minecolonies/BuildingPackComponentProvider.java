package nz.co.mirality.jadecolonies.minecolonies;

import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.api.colony.buildings.IRSComponent;
import com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding;
import com.minecolonies.core.tileentities.TileEntityDecorationController;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import static nz.co.mirality.jadecolonies.JadeColonies.ID;

/**
 * Adds the style pack that the building comes from.
 */
class BuildingPackComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor>
{
    private static final ResourceLocation UID = new ResourceLocation(ID, "colony.pack");
    private static final String PACK_NAME = UID.toString();
    private static final BuildingPackComponentProvider INSTANCE = new BuildingPackComponentProvider();

    public static BuildingPackComponentProvider getInstance()
    {
        return INSTANCE;
    }

    @NotNull
    @Override
    public ResourceLocation getUid()
    {
        return UID;
    }

    @Override
    public int getDefaultPriority()
    {
        return -2999;
    }

    @Override
    public void appendServerData(@NotNull final CompoundTag compoundTag,
                                 @NotNull final BlockAccessor blockAccessor)
    {
        if (blockAccessor.getBlockEntity() instanceof final AbstractTileEntityColonyBuilding entity)
        {
            final IBuilding building = entity.getBuilding();
            if (building == null || building instanceof IRSComponent) { return; }

            compoundTag.putString(PACK_NAME, building.getStructurePack());
        }
        else if (blockAccessor.getBlockEntity() instanceof final TileEntityDecorationController deco)
        {
            compoundTag.putString(PACK_NAME, deco.getPackName());
        }
    }

    @Override
    public void appendTooltip(@NotNull final ITooltip tooltip,
                              @NotNull final BlockAccessor blockAccessor,
                              @NotNull final IPluginConfig pluginConfig)
    {
        final String name = blockAccessor.getServerData().getString(PACK_NAME);

        if (!name.isEmpty())
        {
            tooltip.add(Component.literal(name).withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}