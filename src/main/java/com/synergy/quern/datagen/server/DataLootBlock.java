package com.synergy.quern.datagen.server;

import java.util.*;

import com.synergy.quern.init.types.zBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {

                return List.of(zBlocks.QUERN.get());
        }

        @Override
        protected void generate() {

                dropSelf(zBlocks.QUERN.get());
        }

}
