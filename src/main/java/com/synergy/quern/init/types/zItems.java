package com.synergy.quern.init.types;

import static com.synergy.quern.Main.ID;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {

    public static void register(IEventBus bus) {
        zItem.register(bus);
        zBlockItem.register(bus);
    }

    public static final DeferredRegister.Items zItem = DeferredRegister.createItems(ID);
    public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(ID);

}
