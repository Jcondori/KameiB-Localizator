package localizator.mixin.roughtweaks;

import lellson.roughTweaks.RoughTweaks;
import lellson.roughTweaks.items.ItemHeal;
import localizator.handlers.ForgeConfigHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemHeal.class)
public abstract class ItemHealMixin extends Item {
    @Inject(
            method = "<init>(Ljava/lang/String;IIFLnet/minecraft/potion/PotionEffect;Lnet/minecraft/item/ItemStack;)V",
            at = @At(value = "TAIL"),
            remap = false
    )
    // Line 38: this.setTranslationKey(name);
    private void localizator_RoughTweaks_ItemHeal_setTranslationKey(String name, int useCount, int healRate, float healAmount, PotionEffect effect, ItemStack returnStack, CallbackInfo ci) {
        this.setTranslationKey(RoughTweaks.MODID + "." + name);
    }

    @Mutable @Final @Shadow(remap = false) private float healAmount;
    /**
     * @author KameiB
     * @reason Localize item Heal amount. 
     * Optional: Remove the need of pressing Shift to show the item's Heal amount.
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    // Line 91
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        if (GuiScreen.isShiftKeyDown() || ForgeConfigHandler.clientConfig.roughtweaksTooltip) {
            float hearts = this.healAmount / 2.0F;
            if ((double)hearts % 1.0 == 0.0) {
                tooltip.add(TextFormatting.BLUE + I18n.format("tooltip.roughtweaks.itemheal.heal_amount") + " " + (int)hearts + " " + I18n.format("tooltip.roughtweaks.itemheal.hearts"));
            } else {
                tooltip.add(TextFormatting.BLUE + I18n.format("tooltip.roughtweaks.itemheal.heal_amount") + " " + hearts + " " + I18n.format("tooltip.roughtweaks.itemheal.hearts"));
            }
        }
        /*else {
            tooltip.add(I18n.format("tooltip.roughtweaks.itemheal.shift"));
        }*/
    }
}
