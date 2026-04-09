package com.synergy.quern;

import com.synergy.quern.init.builder.quern.QuernRendering;
import com.synergy.quern.init.types.zBlockEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Main.ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Main.ID, value = Dist.CLIENT)
public class Client {

    public Client(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(zBlockEntities.QUERN.get(),QuernRendering::new);
    }

}
