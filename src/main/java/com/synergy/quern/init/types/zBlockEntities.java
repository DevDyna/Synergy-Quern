package com.synergy.quern.init.types;

import static com.synergy.quern.Main.ID;

import com.synergy.quern.init.Material;
import com.synergy.quern.init.builder.quern.QuernBE;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zBlockEntities {

    public static void register(IEventBus bus) {
        zTiles.register(bus);
    }

    public static final DeferredRegister<BlockEntityType<?>> zTiles = DeferredRegister
            .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ID);

    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<QuernBE>> QUERN = Material.createBlockEntity("quern", QuernBE::new, zBlocks.QUERN);

}
