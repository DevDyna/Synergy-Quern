package com.synergy.quern.datagen.client;

import static com.synergy.quern.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.datagen.LangUtils;

import static com.devdyna.cakesticklib.api.datagen.LangUtils.*;

import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                addBlock(zBlocks.QUERN, "Quern");

                zItems.zItem.getEntries().forEach(i -> addItem(i, named(i, MODULE_ID)));

                LangUtils.advKey(this, MODULE_ID, "quern", "It spin!", "Craft a quern to process resources into dusts");

                add(MODULE_ID + ".jei.quern", "Milling Recipes");

        }

        }

}
