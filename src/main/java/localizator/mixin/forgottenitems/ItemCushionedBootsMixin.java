package localizator.mixin.forgottenitems;

import localizator.data.Production;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tschipp.forgottenitems.items.ItemCushionedBoots;

import java.util.List;

@Mixin(ItemCushionedBoots.class)
public abstract class ItemCushionedBootsMixin {
    /**
     * @author KameiB
     * @reason Localize item description
     */
    @Overwrite(remap = Production.inProduction) // FALSE ONLY IN DEBUGGING!
    @SideOnly(Side.CLIENT)
    // Line 54
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
    {        
        tooltip.add(I18n.format(((Item)((Object)this)).getUnlocalizedNameInefficiently(stack) + ".desc"));
    }
}
