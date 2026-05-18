package com.synergy.quern.datagen.server;


import static com.synergy.quern.Main.ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.quern.init.types.zBlocks;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataBlockTag extends BlockTagsProvider {

        public DataBlockTag(PackOutput output, CompletableFuture<Provider> lookupProvider,ExistingFileHelper e) {
                super(output, lookupProvider, ID, e);
        }

        @Override
        protected void addTags(Provider p) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(zBlocks.QUERN.get());
        }

}