package com.synergy.quern.init.builder.quern;

import java.util.Optional;

import com.devdyna.cakesticklib.api.aspect.logic.*;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.zLibrary;
import com.synergy.quern.api.ItemInput;

import com.synergy.quern.init.builder.quern.recipe.MillingRecipe;
import com.synergy.quern.init.types.zBlockEntities;
import com.synergy.quern.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class QuernBE extends BlockEntity implements ItemStorageBlock, NoGuiStorage {

    private float rotation = 0f;
    private float speed = 0f;

    private int minDelay;

    public QuernBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public QuernBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.QUERN.get(), pos, blockState);
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(zLibrary.zHandlers.ITEM_STORAGE);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        getItemStorage().serialize(output);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        getItemStorage().deserialize(input);
        super.loadAdditional(input);
    }

    public ItemStack insertItem(ItemStack stack) {
        long inserted = 0;

        try (Transaction tx = Transaction.openRoot()) {
            inserted = getItemStorage().insert(0, ItemResource.of(stack), stack.getCount(), tx);
            tx.commit();
        } catch (Exception e) {
        }

        return x.item(stack.getItem(), stack.getCount() - (int) inserted);
    }

    public ItemStack extractItem() {
        var resource = getItemStorage().getResource(0);

        if (resource.isEmpty())
            return ItemStack.EMPTY;

        try (Transaction tx = Transaction.openRoot()) {

            long extracted = getItemStorage()
                    .extract(0, resource, getItemStorage().getAmountAsInt(0), tx);
            tx.commit();

            return resource.toStack((int) extracted);
        }
    }

    public float getRotation(float partialTicks) {
        return rotation + speed * partialTicks;
    }

    public void tickServer() {

        if (level == null)
            return;

        var random = level.getRandom();

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            minDelay++;
        } else {
            minDelay = 0;
        }

        var slot = getItemStorage();

        if (slot == null)
            return;

        var item = slot.getResource(0);

        if (!item.isEmpty()) {

            try (Transaction tx = Transaction.openRoot()) {

                Optional<RecipeHolder<MillingRecipe>> recipe = level.getServer().getRecipeManager()
                        .getRecipeFor(zRecipeTypes.QUERN.getType(),
                                new ItemInput(item.toStack()), level);

                if (recipe.isEmpty())
                    return;

                level.setBlockAndUpdate(getBlockPos(),
                        getBlockState().setValue(BlockStateProperties.ENABLED, true));

                if (random.nextInt(100) <= 70) {

                    if (level.getGameTime() % 15 == 0) {
                        level.playSound(null, getBlockPos(),
                                SoundEvents.GRINDSTONE_USE,
                                SoundSource.BLOCKS, 0.25F * (random.nextInt(100) <= 50 ? 1f : 0.75f), 1);
                    }

                    if (minDelay >= recipe.get().value().getTime()
                            && minDelay % recipe.get().value().getTime() == 0) {
                        var output = recipe.get().value().getOutput();
                        dropInWorldResult(output.create(), level, getBlockPos());

                        slot.extract(0, slot.getResource(0), 1, tx);
                        tx.commit();

                        level.playSound(null, getBlockPos(),
                                SoundEvents.ITEM_FRAME_REMOVE_ITEM,
                                SoundSource.BLOCKS, 0.5F * (random.nextInt(100) <= 50 ? 1f : 0.75f), 1);
                        setChanged(level, getBlockPos(), getBlockState());
                    }
                }
            } catch (Exception e) {

            }
        } else {
            level.setBlockAndUpdate(getBlockPos(),
                    getBlockState().setValue(BlockStateProperties.ENABLED, false));
        }

    }

    public void tickClient() {

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {
            if (speed < 10f)// max speed
                speed += 0.1f;
        } else {
            if (speed > 0) {
                speed -= 0.1f;

            }
            if (speed < 0)
                speed = 0;
        }

        rotation += speed;
        if (rotation >= 360f)
            rotation -= 360f;
    }

}
