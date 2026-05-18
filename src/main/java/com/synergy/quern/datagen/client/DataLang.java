package com.synergy.quern.datagen.client;

import static com.synergy.quern.Main.ID;

import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

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

                zItems.zDusts.getEntries().forEach(i -> addItem(i, named(i)));
                zItems.zGears.getEntries().forEach(i -> addItem(i, named(i)));
                zItems.zSimple.getEntries().forEach(i -> addItem(i, named(i)));

                add(ID + ".jei.quern",
                                "Quern Milling");

                advKey("quern", "It spin!", "Craft a quern to process resources into dusts");

        }

        public String named(DeferredHolder<?, ?> b) {

                StringBuilder result = new StringBuilder();
                for (String word : b.getRegisteredName()
                                .replace(ID + ":", "")
                                .replaceAll("_", " ")
                                .split(" "))
                        if (!word.isEmpty())
                                result.append(Character.toUpperCase(word.charAt(0)))
                                                .append(word.substring(1))
                                                .append(" ");
                return result.toString().trim();
        }

        private void advKey(String k, String title, String desc) {
                add(ID + ".advancement.branch." + k, title);
                add(ID + ".advancement.branch." + k + ".desc", desc);
        }

}
