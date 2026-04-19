package com.synergy.quern.common;

import com.devdyna.cakesticklib.api.CapabilityUtils;
import com.synergy.quern.init.types.zBlocks;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class Capability {

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {

        CapabilityUtils.registerItemBlock(event ,zBlocks.QUERN.get());
                
    }
}
