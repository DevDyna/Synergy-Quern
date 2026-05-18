package com.synergy.quern.init.types;

import static com.synergy.quern.Main.ID;

import java.util.Arrays;
import java.util.function.Supplier;

import com.synergy.quern.init.builder.quern.QuernBE;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unchecked")
public class zBlockEntities {

    public static void register(IEventBus bus) {
        zTiles.register(bus);
    }

    public static final DeferredRegister<BlockEntityType<?>> zTiles = DeferredRegister
            .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuernBE>> QUERN = createBlockEntity("quern",
            QuernBE::new, () -> zBlocks.QUERN.get());

    @SuppressWarnings("null")
    public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> createBlockEntity(
            String name,
            BlockEntitySupplier<T> factory, Supplier<? extends Block>... validBlocks) {
        return zBlockEntities.zTiles.register(name,
                () -> BlockEntityType.Builder.of(factory, Arrays.stream(validBlocks)
                        .map(Supplier::get)
                        .toArray(Block[]::new)).build(null));
    }

}
