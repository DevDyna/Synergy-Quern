package com.synergy.quern;

import com.synergy.quern.api.aspect.ItemStorageBlock;
import com.synergy.quern.init.types.zBlocks;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class Capability {

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {

        event.registerBlock(
                Capabilities.Item.BLOCK,
                (level, pos, state, be, side) -> {
                    if (be instanceof ItemStorageBlock s)
                        return s.getItemStorage();
                    return null;
                },
                zBlocks.QUERN.get());
                
    }
}
