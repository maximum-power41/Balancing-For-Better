package dev.power.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;

import java.util.List;

public abstract class VillagerTradeEvents {

    public static final Event<ModifyAvailableEnchantments> MODIFY_AVAILABLE_ENCHANTMENTS_LIST = EventFactory.createArrayBacked(ModifyAvailableEnchantments.class, callbacks -> (enchantmentList) -> {

        for (ModifyAvailableEnchantments callback : callbacks) enchantmentList = callback.modifyAvailableEnchantments(enchantmentList);

        return enchantmentList;
    });

    public static final Event<AddVillagerTradeOffers> ADD_VILLAGER_TRADE_OFFERS = EventFactory.createArrayBacked(AddVillagerTradeOffers.class, callbacks -> (currentOffers, newOffers, count, villagerData) -> {

        for (AddVillagerTradeOffers callback : callbacks) callback.addVillagerTradeOffers(currentOffers, newOffers, count, villagerData);

    });

    @FunctionalInterface
    public interface ModifyAvailableEnchantments {

        List<Enchantment> modifyAvailableEnchantments(List<Enchantment> enchantmentList);
    }

    @FunctionalInterface
    public interface AddVillagerTradeOffers {

        void addVillagerTradeOffers(TradeOffer[] currentOffers, TradeOffers.Factory[] newOffers, final int count, final VillagerData villagerData);
    }
}
