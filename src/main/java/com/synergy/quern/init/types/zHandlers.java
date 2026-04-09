package com.synergy.quern.init.types;

import static com.synergy.quern.Main.ID;

import java.util.function.Supplier;

import com.synergy.quern.api.aspect.ItemStorageBlock;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class zHandlers {
    public static void register(IEventBus bus) {
        zHandler.register(bus);
    }

    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(
            Keys.ATTACHMENT_TYPES,
            ID);

    public static final Supplier<AttachmentType<ItemStacksResourceHandler>> ITEM_STORAGE = zHandler.register(
            "item_storage", () -> AttachmentType.serializable(h -> {
                if (h instanceof ItemStorageBlock be)
                    return new ItemStacksResourceHandler(be.getSlots());
                return null;
            }).build());

}