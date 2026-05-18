package com.synergy.quern.compat.jei.categories;



import com.synergy.quern.api.utils.Size;
import com.synergy.quern.compat.jei.categories.core.BaseRecipeCategory;
import com.synergy.quern.init.builder.quern.QuernMillingRecipe;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class QuernCategory extends BaseRecipeCategory<QuernMillingRecipe> {

    public QuernCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<QuernMillingRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.QUERN_MILLING.getType());

    @Override
    public RecipeType<RecipeHolder<QuernMillingRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return "quern";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.QUERN.get();
    }

    @Override
    public Size setXY() {
        return Size.of(75, 20);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/simple.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, QuernMillingRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 2).addItemStack(recipe.getOutput());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(QuernMillingRecipe recipe) {
        return recipe.getTime();
    }

}
