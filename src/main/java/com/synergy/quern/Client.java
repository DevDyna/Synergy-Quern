package com.synergy.quern;

import static com.synergy.quern.Main.ID;

import com.synergy.quern.init.builder.quern.QuernBE;
import com.synergy.quern.init.builder.quern.QuernRendering;
import com.synergy.quern.init.types.zBlockEntities;
import com.synergy.quern.utils.x;

import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.model.standalone.SimpleUnbakedStandaloneModel;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;

@Mod(value = Main.ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Main.ID, value = Dist.CLIENT)
public class Client {
    public Client(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SuppressWarnings("null")
    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(zBlockEntities.QUERN.get(),QuernRendering::new);
    }

    // public static StandaloneModelKey<QuadCollection> QUERN_KEY = new StandaloneModelKey<>(
    //         new ModelDebugName() {
    //             @Override
    //             public String debugName() {
    //                 return ID + ": Quern Render";
    //             }
    //         });

    // public static SimpleUnbakedStandaloneModel<QuadCollection> model = SimpleUnbakedStandaloneModel.quadCollection(
    //         x.rl("block/render"));

    // @SubscribeEvent
    // public static void registerAdditional(ModelEvent.RegisterStandalone event) {
    //     event.register(
    //             QUERN_KEY,
    //             model);
    // }
}
