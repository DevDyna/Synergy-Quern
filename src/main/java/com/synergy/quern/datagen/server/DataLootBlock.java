package com.synergy.quern.datagen.server;

import java.util.*;

import com.synergy.quern.init.types.zBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        List<DeferredRegister.Blocks> blocktypes = List.of(
                        
                        zBlocks.zBlock,
                        zBlocks.zBlockItem
                       


        );

        @Override
        protected Iterable<Block> getKnownBlocks() {
                List<Block> blocks = new ArrayList<>();
                blocktypes.forEach(t -> blocks.addAll(t.getEntries().stream().map(DeferredHolder::get).toList()));
                return blocks;
        }

        @Override
        protected void generate() {

                dropSelf(zBlocks.QUERN.get());
        }
}
