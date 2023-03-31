package dev.power;

import dev.power.events.VillagerTradeEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancingForBetter implements ModInitializer {

    public static final String MOD_ID = "balancing_for_better";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        registerEvents();
    }

    private void registerEvents() {
        LOGGER.info("Registering Events");
        VillagerTradeEvents.MODIFY_ENCHANTMENT_TRADE_LIST.register((enchantmentList) -> enchantmentList.stream().filter(enchantment -> !enchantment.equals(Enchantments.MENDING)).toList());
        LOGGER.info("Finished Registering Events");
    }
}
