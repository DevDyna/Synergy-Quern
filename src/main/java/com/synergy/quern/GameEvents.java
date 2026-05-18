package com.synergy.quern;

import net.neoforged.bus.api.IEventBus;

public class GameEvents {

    public static void register(IEventBus bus) {

        bus.addListener(Capability::register);
        bus.addListener(CreativeTab::register);

    }

}
