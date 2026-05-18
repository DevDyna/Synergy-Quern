package com.synergy.quern;

import com.synergy.quern.init.Material;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(Main.ID)
public class Main {

    public static final String ID = "quern";

    public Main(IEventBus bus, ModContainer mc) {

        Material.register(bus);
        GameEvents.register(bus);
    }

}
