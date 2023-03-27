package dev.power.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.enchantment.Enchantment;

import java.util.List;

public class TradeOfferEvents {

    public static final Event<EnchantmentBookTradeList> MODIFY_ENCHANTMENT_TRADE_LIST = EventFactory.createArrayBacked(EnchantmentBookTradeList.class, callbacks -> (enchantmentList) -> {
        List<Enchantment> tempList = enchantmentList;
        for (EnchantmentBookTradeList callback : callbacks) {
            tempList = callback.OnRetrieveTradeList(tempList);
        }

        return tempList;
    });

    @FunctionalInterface
    public interface EnchantmentBookTradeList {

        List<Enchantment> OnRetrieveTradeList(List<Enchantment> enchantmentList);
    }


}
