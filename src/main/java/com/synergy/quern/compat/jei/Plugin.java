package com.synergy.quern.compat.jei;


import static com.synergy.quern.Main.ID;

import com.synergy.quern.api.utils.RecipeUtils;
import com.synergy.quern.api.utils.x;
import com.synergy.quern.compat.jei.categories.QuernCategory;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings({  "null" })
@JeiPlugin
public class Plugin implements IModPlugin {

        @Override
        public ResourceLocation getPluginUid() {
                return x.rl(ID, "jei_plugin");
        }


        @Override
        public void registerRecipeCatalysts(IRecipeCatalystRegistration r) {
            
                r.addRecipeCatalyst(x.item(zBlocks.QUERN), QuernCategory.TYPE);

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


               
                r.addRecipes(QuernCategory.TYPE, RecipeUtils.getRecipes(zRecipeTypes.QUERN_MILLING));


        }



}