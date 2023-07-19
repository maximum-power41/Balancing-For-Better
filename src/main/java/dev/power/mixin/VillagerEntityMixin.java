package dev.power.mixin;

import dev.power.events.VillagerTradeEvents;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {

    @Shadow public abstract VillagerData getVillagerData();

    @ModifyArgs(method = "fillRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/VillagerEntity;fillRecipesFromPool(Lnet/minecraft/village/TradeOfferList;[Lnet/minecraft/village/TradeOffers$Factory;I)V"))
    private void modifyFillRecipesFromPoolParameters(@NotNull Args args) {
        TradeOfferList currentOffers = args.get(0);
        TradeOffers.Factory[] newOffers = args.get(1);
        VillagerData villagerData = this.getVillagerData();

        VillagerTradeEvents.MODIFY_VILLAGER_TRADE_OFFERS.invoker().modifyVillagerTradeOffers(currentOffers.toArray(new TradeOffer[0]), newOffers, villagerData);
    }
}
