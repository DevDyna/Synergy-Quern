package com.synergy.quern;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.synergy.quern.init.Material;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.ModContainer;

@Mod(Main.ID)
public class Main {

    public static final String ID = "quern";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Main(IEventBus bus, ModContainer c) {

        Material.register(bus);

        bus.addListener(Capability::register);

        bus.register(CreativeTabs.class);

        NeoForge.EVENT_BUS.register(BreakEvent.class);

    }

}
