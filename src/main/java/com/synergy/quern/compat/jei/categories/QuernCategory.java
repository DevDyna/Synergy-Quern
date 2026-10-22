package com.synergy.quern.compat.jei.categories;

import static com.synergy.quern.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.quern.init.builder.quern.recipe.MillingRecipe;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
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
    public String getTraslationKey() {
        return MODULE_ID + ".jei.quern.title";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.QUERN.get();
    }

    @Override
    public Size setXY() {
        return Size.of(96, 52);
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/quern.png");
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MillingRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 1).addItemStacks(x.getItemStacksFromIngredient(recipe.getInput()));
        builder.addOutputSlot(78, 25).add(recipe.getOutput());
    }

    @Override
    public void draw(MillingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics,
            double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        guiGraphics.text(this.font,
                Component.translatable(MODULE_ID + ".jei.quern.speed",
                        "" + (recipe.getSpeed().isPresent()
                                ? ((int) (recipe.getSpeed().get() * 100.0)) / 100.0
                                : 0.1)),
                0, 45, ColorUtils.WHITE.WHITE.getRGB());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickColor(MillingRecipe recipe) {
        return ColorUtils.WHITE.WHITE.getRGB();
    }

    @Override
    public boolean enableTimerRender(MillingRecipe recipe) {
        return true;
    }

    @Override
    public int tickValue(MillingRecipe recipe) {
        return recipe.getTime();
    }

    @Override
    public Size tickPos(MillingRecipe recipe) {
        return Size.of(58, 4);
    }

}