package com.synergy.quern.datagen.client;

import static com.synergy.quern.Main.ID;

import com.synergy.quern.api.utils.x;
import com.synergy.quern.init.types.zBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DataBlockModelState extends BlockStateProvider {

        public DataBlockModelState(PackOutput o, ExistingFileHelper f) {
                super(o, ID, f);
        }

        @Override
        protected void registerStatesAndModels() {

                simpleBlock(zBlocks.QUERN.get(), models().getExistingFile(x.rl("block/quern/base")));

        }

}
