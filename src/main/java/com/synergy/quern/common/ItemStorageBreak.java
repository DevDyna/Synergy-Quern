package com.synergy.quern.common;

import com.synergy.quern.api.aspect.ItemStorageBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class ItemStorageBreak {

    @SubscribeEvent
    public static void inventoryDestroy(BlockEvent.BreakEvent event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var be = level.getBlockEntity(pos);

        if (be instanceof ItemStorageBlock storage)
            for (int i = 0; i < storage.getSlots(); i++)
                Block.popResource((Level) level, pos, storage.getStackInSlot(i));

    }

}
