package dev.power.mixin;

import dev.power.events.VillagerTradeEvents;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {

    private VillagerData villagerData = null;

    @Inject(method = "fillRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/VillagerEntity;getOffers()Lnet/minecraft/village/TradeOfferList;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void getLocalVariables(CallbackInfo ci, VillagerData villagerData, Int2ObjectMap<TradeOffers.Factory[]> int2ObjectMap, TradeOffers.Factory[] offersList) {
        this.villagerData = villagerData;
    }

    @ModifyArgs(method = "fillRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/VillagerEntity;fillRecipesFromPool(Lnet/minecraft/village/TradeOfferList;[Lnet/minecraft/village/TradeOffers$Factory;I)V"))
    private void modifyFillRecipesFromPoolParameters(@NotNull Args args) {
        TradeOfferList currentOffers = args.get(0);
        TradeOffers.Factory[] newOffers = args.get(1);
        int count = args.get(2);
        VillagerData villagerData = this.villagerData;

        VillagerTradeEvents.ADD_VILLAGER_TRADE_OFFERS.invoker().addVillagerTradeOffers(currentOffers.toArray(new TradeOffer[0]), newOffers.clone(), count, villagerData);
    }
}
