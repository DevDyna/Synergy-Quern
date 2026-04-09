package com.synergy.quern.init.builder.quern;

import java.util.Optional;

import com.synergy.quern.api.ItemInput;
import com.synergy.quern.api.aspect.ItemStorageBlock;
import com.synergy.quern.api.aspect.NoGuiStorage;
import com.synergy.quern.init.builder.quern.recipe.MillingRecipe;
import com.synergy.quern.init.types.zBlockEntities;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zHandlers;
import com.synergy.quern.init.types.zRecipeTypes;
import com.synergy.quern.utils.x;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

@SuppressWarnings("null")
public class QuernBE extends BlockEntity implements ItemStorageBlock, NoGuiStorage {

    private float rotation = 0f; // client & server rotation
    private float speed = 0f; // server authoritative speed

    private int minDelay;

    private static final float MAX_SPEED = 10f; // degrees per tick
    private static final float ACCEL = 0.1f;
    private static final float DECEL = 0.1f;

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
        return getData(zHandlers.ITEM_STORAGE);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(getSlots());
        inv.setItem(0, getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inv);
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
        var copy = stack.getItem();
        var remain = stack.getCount();
        try (Transaction tx = Transaction.openRoot()) {
            remain = getItemStorage().insert(0, ItemResource.of(stack), stack.getCount(), tx);
            tx.commit();
        } catch (Exception e) {
        }
        return x.item(copy, remain);

    }

    public ItemStack extractItem() {
        ItemStack extracted = getStackInSlot(0);
        var remain = extracted.getCount();
        try (Transaction tx = Transaction.openRoot()) {
            remain = getItemStorage().extract(0, ItemResource.of(extracted), 0, tx);
            tx.commit();
        } catch (Exception e) {
        }

        extracted.setCount(remain);

        if (!extracted.isEmpty())
            return extracted;
        return ItemStack.EMPTY;
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

        // if (cache == null)
        // return;

        var slot = getItemStorage();

        if (slot == null)
            return;

        var item = slot.getResource(0);

        if (!item.isEmpty()) {

            try (Transaction tx = Transaction.openRoot()) {

                Optional<RecipeHolder<MillingRecipe>> recipe = level.getServer().getRecipeManager()
                        .getRecipeFor(zRecipeTypes.QUERN.getType(),
                                new ItemInput(item.toStack()), level);

                var flag = !recipe.isEmpty();

                level.setBlockAndUpdate(getBlockPos(),
                        getBlockState().setValue(BlockStateProperties.ENABLED, flag));

                if (random.nextInt(100) <= 70)
                    if (flag) {

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
            if (speed < MAX_SPEED)
                speed += ACCEL;
        } else {
            if (speed > 0) {
                speed -= DECEL;

            }
            if (speed < 0)
                speed = 0;
        }

        rotation += speed;
        if (rotation >= 360f)
            rotation -= 360f;
    }

}
