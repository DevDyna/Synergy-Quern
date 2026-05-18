package com.synergy.quern.datagen.client;

import static com.synergy.quern.Main.ID;

import com.synergy.quern.api.utils.DataGenUtil;
import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataItemModel extends ItemModelProvider {

        public DataItemModel(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerModels() {

                withExistingParent(zBlocks.QUERN.getRegisteredName(),
                                modLoc("block/quern/item"));

                zItems.zDusts.getEntries().forEach(item -> DataGenUtil.itemModel( item.get(),  this));
                zItems.zGears.getEntries().forEach(item -> DataGenUtil.itemModel( item.get(),  this));
                zItems.zSimple.getEntries().forEach(item -> DataGenUtil.itemModel( item.get(),  this));

        }

}
