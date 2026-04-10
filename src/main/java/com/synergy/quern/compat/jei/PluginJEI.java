package com.synergy.quern.compat.jei;

import java.util.List;

import com.synergy.quern.Client;
import com.synergy.quern.compat.jei.categories.QuernCategory;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;
import com.synergy.quern.utils.x;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

@JeiPlugin
public class PluginJEI implements IModPlugin {

    @Override
    public Identifier getPluginUid() {
        return x.rl("jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration r) {

        r.addCraftingStation(QuernCategory.TYPE, x.item(zBlocks.QUERN.get()));

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration r) {
        var helper = r.getJeiHelpers().getGuiHelper();

        r.addRecipeCategories(

                new QuernCategory(helper)

        );

    }

    @Override
    public void registerRecipes(IRecipeRegistration r) {

        r.addRecipes(QuernCategory.TYPE,
                getRecipes(zRecipeTypes.QUERN.getType()));

    }

    private <C extends RecipeInput, T extends Recipe<C>> List<RecipeHolder<T>> getRecipes(RecipeType<T> type) {
        return List.copyOf(Client.getRecipeCollector().byType(type));
    }

}
