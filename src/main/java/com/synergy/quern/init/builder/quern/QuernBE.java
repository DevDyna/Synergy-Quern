package com.synergy.quern.init.builder.quern;

import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.*;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.api.templates.SynchedBlockEntity;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.quern.Config;
import com.synergy.quern.init.builder.quern.recipe.QuernRecipe;
import com.synergy.quern.init.types.zBlockEntities;
import com.synergy.quern.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class QuernBE extends SynchedBlockEntity implements ItemStorageBlock, NoGuiStorage, TickerDelay, Tickable {

    public QuernBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public QuernBE(BlockPos pos, BlockState state) {
        this(zBlockEntities.QUERN.get(), pos, state);
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    // @Override
    // protected void saveAdditional(ValueOutput output) {
    // getItemStorage().serialize(output);
    // super.saveAdditional(output);
    // }

    // @Override
    // protected void loadAdditional(ValueInput input) {
    // getItemStorage().deserialize(input);
    // super.loadAdditional(input);
    // }

    public ItemStack insertItem(ItemStack stack) {
        return simpleInsertItem(stack, 0);
    }

    public ItemStack extractItem() {
        return simpleExtractItemByIndex(0);
    }

    private double rotation = 0f;
    private double speed = 0f;

    private Ticker ticker = null;

    public double getRotation(float t) {
        return rotation + speed * t;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void tickBoth() {

        if (isActive()) {
            if (speed <= Config.MAX_QUERN_SPEED.get())
                speed = Math.min(Config.MAX_QUERN_SPEED.get(),
                        speed + Config.QUERN_SPEED_INCREASER.get());
        } else {
            if (speed > 0)
                speed = Math.max(0, speed - Config.QUERN_SPEED_INCREASER.get());

            if (speed < 0)
                speed = 0;
        }

        rotation += speed;

        if (rotation >= 360f)
            rotation -= 360f;

        // if (!level.isClientSide())
        if (ticker != null)
            if (RandomUtil.chance(level, 25))
                level.addParticle(ParticleTypes.CRIT,
                        getBlockPos().getX() + 0.5f, getBlockPos().getY() + 0.5f, getBlockPos().getZ() + 0.5f,
                        RandomUtil.between(level, -0.25f, 0.25f),
                        RandomUtil.between(level, -0.25f, 0.25f),
                        RandomUtil.between(level, -0.25f, 0.25f));
    }

    @Override
    public void tickServer() {

        var storage = getItemStorage();

        if (storage == null)
            return;

        var item = storage.getResource(0);

        if (item.isEmpty()) {
            set(false);
            ticker = null;
            // update();
            return;
        }

        Optional<RecipeHolder<QuernRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.QUERN_MILLING.getType(),
                        ItemInput.simple.of(item.toStack()), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (recipe.getInput().count() > getItemStorage().getAmountAsInt(0))
            return;

        set(true);
        // update();

        if (RandomUtil.chance(level, 70))
            if (level.getGameTime() % 15 == 0)
                level.playSound(null, getBlockPos(),
                        SoundEvents.GRINDSTONE_USE,
                        SoundSource.BLOCKS, 0.25F
                                * (RandomUtil.rnd50(level) ? 1f : 0.75f),
                        1);

        if (!recipe.getSpeed().isEmpty())
            if (speed < recipe.getSpeed().get())
                return;

        if (ticker == null)
            ticker = Ticker.of(recipe.getTime());

        if (!ticker.commit())
            return;

        try (var tx = Transaction.openRoot()) {
            dropInWorldResult(recipe.getOutput().create(), level, getBlockPos());
            storage.extract(0, storage.getResource(0), recipe.getInput().count(), tx);
            tx.commit();
        }

        level.playSound(null, getBlockPos(),
                SoundEvents.ITEM_FRAME_REMOVE_ITEM,
                SoundSource.BLOCKS, 0.5F * (RandomUtil.rnd50(level) ? 1f : 0.75f), 1);

        update();

    }

    public void set(boolean v) {
        if (isActive() != v)
            level.setBlockAndUpdate(getBlockPos(),
                    getBlockState().setValue(QuernBlock.ENABLED, v));
    }

    public Boolean isActive() {
        return getBlockState().getValue(QuernBlock.ENABLED);
    }

    @Override
    public TimerStyle getStyle() {
        return TimerStyle.PROGRESS_BAR;
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

}
