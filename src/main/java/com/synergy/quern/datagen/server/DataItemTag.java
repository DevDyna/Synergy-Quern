package com.synergy.quern.datagen.server;

import static com.synergy.quern.Main.ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.quern.init.types.zItemTags;
import com.synergy.quern.init.types.zItems;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class DataItemTag extends ItemTagsProvider {

        public DataItemTag(PackOutput output, CompletableFuture<Provider> lookupProvider) {
                super(output, lookupProvider, ID);
        }

        @Override
        protected void addTags(Provider p) {
                tag(Tags.Items.DUSTS)
                                .add(
                                                zItems.DUST_COPPER.get(),
                                                zItems.DUST_GOLD.get(),
                                                zItems.DUST_IRON.get());

                tag(zItemTags.DUST_COPPER).add(zItems.DUST_COPPER.get());
                tag(zItemTags.DUST_GOLD).add(zItems.DUST_GOLD.get());
                tag(zItemTags.DUST_IRON).add(zItems.DUST_IRON.get());

        }

}