package nz.co.mirality.jadecolonies.minecolonies;

import com.minecolonies.core.blocks.MinecoloniesCropBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import static nz.co.mirality.jadecolonies.JadeColonies.ID;

/**
 * Shows growth progress about a MineColonies crop.
 */
class CropAgeComponentProvider implements IBlockComponentProvider
{
    private static final ResourceLocation UID = new ResourceLocation(ID, "crop.age");
    private static final CropAgeComponentProvider INSTANCE = new CropAgeComponentProvider();

    public static CropAgeComponentProvider getInstance()
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
    public void appendTooltip(@NotNull final ITooltip tooltip,
                              @NotNull final BlockAccessor blockAccessor,
                              @NotNull final IPluginConfig pluginConfig)
    {
    	final float growthValue = (blockAccessor.getBlockState().getValue(MinecoloniesCropBlock.AGE) / 6.0F) * 100.0F;
    	if (growthValue < 100.0F)
    	{
    		tooltip.add(Component.translatable("tooltip.jade.crop_growth", String.format("%.0f%%", growthValue)));
    	}
    	else
    	{
    		tooltip.add(Component.translatable("tooltip.jade.crop_growth", Component.translatable("tooltip.jade.crop_mature").withStyle(ChatFormatting.GREEN)));
    	}
    }
}