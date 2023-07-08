package dev.power;

import dev.power.events.VillagerTradeEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;

public class BalancingForBetter implements ModInitializer {

    public static final String MOD_ID = "balancing_for_better";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        registerEvents();
    }

    private void registerEvents() {
        LOGGER.info("Registering Events");

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 5, new Consumer<List<TradeOffers.Factory>>() {
            @Override
            public void accept(List<TradeOffers.Factory> factories) {
                factories.add(new TradeOffers.SellEnchantedToolFactory(Items.IRON_SWORD, 8, 3, 30, 0.2f));
            }
        });
        VillagerTradeEvents.MODIFY_AVAILABLE_ENCHANTMENTS_LIST.register(REMOVE_MENDING_TRADE);
        VillagerTradeEvents.ADD_VILLAGER_TRADE_OFFERS.register(MODIFY_DIAMOND_SWORD_TRADE);

        LOGGER.info("Finished Registering");
    }

    private static final VillagerTradeEvents.ModifyAvailableEnchantments REMOVE_MENDING_TRADE = enchantmentList -> enchantmentList.stream().filter(enchantment -> !enchantment.equals(Enchantments.MENDING)).toList();
    private static final VillagerTradeEvents.AddVillagerTradeOffers MODIFY_DIAMOND_SWORD_TRADE = (currentOffers, newOffers, count, villagerData) -> {

        if ( !(villagerData == null || villagerData.getProfession().equals(VillagerProfession.WEAPONSMITH) || villagerData.getLevel() == 5 || newOffers.length > 0) ) return;

        newOffers[0] = new TradeOffers.SellEnchantedToolFactory(Items.IRON_SWORD, 8, 3, 30, 0.2f);
    };
}
