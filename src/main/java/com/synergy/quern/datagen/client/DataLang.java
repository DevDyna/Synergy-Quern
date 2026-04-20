package com.synergy.quern.datagen.client;

import static com.synergy.quern.Main.MODULE_ID;

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

                advKey(MODULE_ID, "quern", "It spin!", "Craft a quern to process resources into dusts");

                add(MODULE_ID + ".jei.quern", "Milling Recipes");

        }

        @Deprecated
        private void advKey(String modid, String k, String title, String desc) {
                add(MODULE_ID + ".advancement.branch." + k, title);
                add(MODULE_ID + ".advancement.branch." + k + ".desc", desc);
        }

}
