package com.synergy.quern.datagen.client;


import static com.synergy.quern.Main.ID;

import java.util.Arrays;
import java.util.List;

import com.synergy.quern.init.types.zBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, ID, "en_us");
        }

        public static final String TIP_COLOR = "§7";

        @Override
        protected void addTranslations() {

                addBlock(zBlocks.QUERN, "Quern");
        }

        

        private void advKey(String k, String title, String desc) {
                add(ID + ".advancement.branch." + k, title);
                add(ID + ".advancement.branch." + k + ".desc", desc);
        }

}
