package com.synergy.quern.api.utils;

import com.synergy.quern.init.types.zHandlers;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilityUtils {

    public static void registerItemBlock(RegisterCapabilitiesEvent e, Block... blocks) {
        e.registerBlock(Capabilities.ItemHandler.BLOCK,
                (level, pos, state, be, side) -> {

                    return (be != null) ? be.getData(zHandlers.ITEM_STORAGE) : null;

                },
                blocks

        );
    }

}
