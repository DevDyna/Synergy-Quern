package com.synergy.quern;

import com.synergy.quern.api.utils.x;
import com.synergy.quern.init.builder.quern.QuernRendering;
import com.synergy.quern.init.types.zBlockEntities;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(value = Dist.CLIENT)
@Mod(value = Main.ID, dist = Dist.CLIENT)
public class Client {

    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(zBlockEntities.QUERN.get(), QuernRendering::new);
    }

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.standalone(x.rl("block/quern/render")));

    }

}
