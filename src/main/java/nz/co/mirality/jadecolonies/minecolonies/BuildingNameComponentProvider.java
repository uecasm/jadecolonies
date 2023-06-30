package nz.co.mirality.jadecolonies.minecolonies;

import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.api.colony.buildings.IRSComponent;
import com.minecolonies.api.tileentities.AbstractTileEntityColonyBuilding;
import com.minecolonies.coremod.tileentities.TileEntityDecorationController;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import static nz.co.mirality.jadecolonies.JadeColonies.ID;

/**
 * If the building has a custom name, displays the custom name as the main block name, with the name & level below.
 * Otherwise, replaces the block name with the name & level of the building (i.e. appends the level).
 */
class BuildingNameComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor>
{
    private static final ResourceLocation UID = new ResourceLocation(ID, "colony.hut");
    private static final String OVERRIDE_NAME = "givenName";
    private static final String BUILDING_NAME = UID.toString();
    private static final BuildingNameComponentProvider INSTANCE = new BuildingNameComponentProvider();

    public static BuildingNameComponentProvider getInstance()
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
    public boolean isRequired()
    {
        return true;
    }

    @Override
    public int getDefaultPriority()
    {
        return -3000;
    }

    @Override
    public void appendServerData(@NotNull final CompoundTag compoundTag,
                                 @NotNull final BlockAccessor blockAccessor)
    {
        if (blockAccessor.getBlockEntity() instanceof final AbstractTileEntityColonyBuilding entity)
        {
            final IBuilding building = entity.getBuilding();
            if (building == null || building instanceof IRSComponent) { return; }

            final String name = building.getCustomName();
            final MutableComponent nameLevel = Component.translatable("%s %s",
                    Component.translatable(building.getBuildingType().getTranslationKey()),
                    Integer.toString(building.getBuildingLevel()));

            if (name.isEmpty())
            {
                compoundTag.putString(OVERRIDE_NAME, Component.Serializer.toJson(nameLevel));
            }
            else
            {
                compoundTag.putString(OVERRIDE_NAME, Component.Serializer.toJson(Component.literal(name)));
                compoundTag.putString(BUILDING_NAME, Component.Serializer.toJson(nameLevel.withStyle(ChatFormatting.GRAY)));
            }
        }
        else if (blockAccessor.getBlockEntity() instanceof final TileEntityDecorationController deco)
        {
            compoundTag.putString(BUILDING_NAME, Component.Serializer.toJson(Component.literal(deco.getBlueprintPath())));
        }
    }

    @Override
    public void appendTooltip(@NotNull final ITooltip tooltip,
                              @NotNull final BlockAccessor blockAccessor,
                              @NotNull final IPluginConfig pluginConfig)
    {
        final CompoundTag data = blockAccessor.getServerData();

        if (data.contains(BUILDING_NAME))
        {
            final Component name = Component.Serializer.fromJson(data.getString(BUILDING_NAME));
            tooltip.add(name);
        }
    }
}