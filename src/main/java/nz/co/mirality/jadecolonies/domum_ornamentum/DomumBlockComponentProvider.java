package nz.co.mirality.jadecolonies.domum_ornamentum;

import com.ldtteam.domumornamentum.block.IMateriallyTexturedBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.ArrayList;
import java.util.List;

import static nz.co.mirality.jadecolonies.JadeColonies.ID;

class DomumBlockComponentProvider implements IBlockComponentProvider
{
    public static final ResourceLocation UID = new ResourceLocation(ID, "domum.materials");
    private static final DomumBlockComponentProvider INSTANCE = new DomumBlockComponentProvider();

    public static DomumBlockComponentProvider getInstance()
    {
        return INSTANCE;
    }

    @Override
    public ResourceLocation getUid()
    {
        return UID;
    }

    @Override
    public int getDefaultPriority()
    {
        return -1000;
    }

    @Override
    public void appendTooltip(final ITooltip tooltip,
                              final BlockAccessor blockAccessor,
                              final IPluginConfig config)
    {
        if (!(blockAccessor.getBlock() instanceof final IMateriallyTexturedBlock domumBlock))
        {
            return;
        }

        final ItemStack stack = domumBlock.getBlock().getCloneItemStack(blockAccessor.getBlockState(),
                blockAccessor.getHitResult(), blockAccessor.getLevel(), blockAccessor.getPosition(),
                blockAccessor.getPlayer());

        final List<Component> components = new ArrayList<>();
        stack.getItem().appendHoverText(stack, blockAccessor.getLevel(), components, TooltipFlag.Default.NORMAL);

        // remove blank lines and the "crafted in an architect's cutter"
        components.removeIf(c -> (c.getContents() instanceof TranslatableContents t && t.getKey().equals("domum_ornamentum.origin.tooltip")) ||
                        (c.getContents() instanceof LiteralContents l && l.text().isEmpty()));

        tooltip.addAll(components);
    }
}
