package com.synergy.quern.compat.jade;

import com.synergy.quern.compat.jade.provider.*;

import net.minecraft.world.level.block.Block;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration r) {
        r.registerBlockComponent(QuernSpeedProvider.ClientProvider.INSTANCE, Block.class);
    }

    @Override
    public void register(IWailaCommonRegistration r) {
        r.registerBlockDataProvider(QuernSpeedProvider.ServerProvider.INSTANCE, Block.class);
    }
}