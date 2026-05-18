package com.synergy.quern.init.types;


import static com.synergy.quern.Main.ID;

import com.synergy.quern.Main;
import com.synergy.quern.api.RecipeRegister;
import com.synergy.quern.init.builder.quern.QuernMillingRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zRecipeTypes {

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        TYPES.register(bus);
    }

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, Main.ID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ID);

    public static final RecipeRegister<QuernMillingRecipe> QUERN_MILLING = RecipeRegister.of("milling",
            QuernMillingRecipe.Serializer::new);

}
