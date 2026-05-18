package com.synergy.quern;

import static com.synergy.quern.api.utils.CapabilityUtils.*;

import com.synergy.quern.init.types.zBlocks;

import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class Capability {

        public static void register(RegisterCapabilitiesEvent event) {
                registerItemBlock(event,
                                zBlocks.QUERN.get());
        }

}
