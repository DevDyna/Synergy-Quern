package com.synergy.quern.api.recipe.builders.api;

public interface BuilderAttach<BUILDER extends BaseRecipeBuilder> {
    public abstract BUILDER getBuilder();

}
