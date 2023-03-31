package dev.power.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;

import java.util.List;

public abstract class VillagerTradeEvents {

    public static final Event<EnchantmentBookTradeList> MODIFY_ENCHANTMENT_TRADE_LIST = EventFactory.createArrayBacked(EnchantmentBookTradeList.class, callbacks -> (enchantmentList) -> {

        for (EnchantmentBookTradeList callback : callbacks) enchantmentList = callback.OnRetrieveTradeList(enchantmentList);

        return enchantmentList;
    });

    public static final Event<VillagerTradeOffers> MODIFY_VILLAGER_TRADE_LIST = EventFactory.createArrayBacked(VillagerTradeOffers.class, callbacks -> (villagerData, offerList) -> {

        for (VillagerTradeOffers callback : callbacks) offerList = callback.OnRetrieveTradeList(villagerData, offerList);

        return offerList;
    });

    @FunctionalInterface
    public interface EnchantmentBookTradeList {

        List<Enchantment> OnRetrieveTradeList(List<Enchantment> enchantmentList);
    }

    @FunctionalInterface
    public interface VillagerTradeOffers {

        TradeOffers.Factory[] OnRetrieveTradeList(VillagerData villagerData, TradeOffers.Factory[] offerList);
    }
}
