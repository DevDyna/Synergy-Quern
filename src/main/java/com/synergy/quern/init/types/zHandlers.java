package com.synergy.quern.init.types;


import static com.synergy.quern.Main.ID;
import com.synergy.quern.api.beLogic.*;

import java.util.function.Supplier;



import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class zHandlers {
    public static void register(IEventBus bus) {
        zHandler.register(bus);
    }

    // ---------------------------------------------------------------------------------------//

    public static final DeferredRegister<AttachmentType<?>> zHandler = DeferredRegister.create(
            Keys.ATTACHMENT_TYPES,
            ID);

    // ---------------------------------------------------------------------------------------//

    

    public static final Supplier<AttachmentType<ItemStackHandler>> ITEM_STORAGE = zHandler.register(
            "item_storage", () -> AttachmentType.serializable(h -> {
                if (h instanceof ItemStorageBlock be)
                    return new ItemStackHandler(be.MachineSlots());
                return null;
            }).build());


}
