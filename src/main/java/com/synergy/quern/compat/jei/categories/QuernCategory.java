package com.synergy.quern.compat.jei.categories;



import com.synergy.quern.compat.jei.api.BaseRecipeCategory;
import com.synergy.quern.init.builder.quern.recipe.MillingRecipe;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;
import com.synergy.quern.utils.Size;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class QuernCategory extends BaseRecipeCategory<MillingRecipe> {

    public QuernCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<MillingRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.QUERN.getType());

    @Override
    public IRecipeType<RecipeHolder<MillingRecipe>> getRecipeType() {
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
    public void setRecipe(IRecipeLayoutBuilder builder, MillingRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
        .add(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 2)
        .add(recipe.getOutput().create());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(MillingRecipe recipe) {
        return recipe.getTime();
    }

}