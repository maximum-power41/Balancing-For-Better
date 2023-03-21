package dev.power.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(TradeOffers.EnchantBookFactory.class)
public abstract class EnchantBookFactoryMixin {

    @ModifyVariable(method = "create", at = @At("STORE"))
    public List<Enchantment> modifyEnchantmentOffers(List<Enchantment> enchantmentList) {

        return enchantmentList.stream().filter(enchantment -> !enchantment.equals(Enchantments.MENDING)).toList();
    }
}
