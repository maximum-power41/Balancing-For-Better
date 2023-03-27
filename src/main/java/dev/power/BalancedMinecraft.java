package dev.power;

import dev.power.events.TradeOfferEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancedMinecraft implements ModInitializer {

    public static final String MOD_ID = "balanced-minecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        registerEvents();
    }

    private void registerEvents() {
        LOGGER.info("Registering Events");
        TradeOfferEvents.MODIFY_ENCHANTMENT_TRADE_LIST.register((enchantmentList) -> enchantmentList.stream().filter(enchantment -> !enchantment.equals(Enchantments.MENDING)).toList());
        LOGGER.info("Finished Registering Events");
    }
}
