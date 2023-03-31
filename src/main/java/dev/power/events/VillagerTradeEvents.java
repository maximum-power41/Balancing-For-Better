package dev.power.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;

import java.util.List;

public abstract class VillagerTradeEvents {

    public static final Event<GetAvailableEnchantments> MODIFY_AVAILABLE_ENCHANTMENTS_LIST = EventFactory.createArrayBacked(GetAvailableEnchantments.class, callbacks -> (enchantmentList) -> {

        for (GetAvailableEnchantments callback : callbacks) enchantmentList = callback.onGetAvailableEnchantments(enchantmentList);

        return enchantmentList;
    });

    public static final Event<AddingVillagerTradeOffers> ADD_VILLAGER_TRADE_OFFERS = EventFactory.createArrayBacked(AddingVillagerTradeOffers.class, callbacks -> (currentOffers, newOffers, count, villagerData) -> {

        for (AddingVillagerTradeOffers callback : callbacks) newOffers = callback.onAddingVillagerTradeOffers(currentOffers, newOffers, count, villagerData);

        return newOffers;
    });

    @FunctionalInterface
    public interface GetAvailableEnchantments {

        List<Enchantment> onGetAvailableEnchantments(List<Enchantment> enchantmentList);
    }

    @FunctionalInterface
    public interface AddingVillagerTradeOffers {

        TradeOffers.Factory[] onAddingVillagerTradeOffers(TradeOffer[] currentOffers, TradeOffers.Factory[] newOffers, final int count, final VillagerData villagerData);
    }
}
