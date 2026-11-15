package com.synergy.quern.datagen.client;

import static com.synergy.quern.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.datagen.LangGenerators;

import com.synergy.quern.init.types.zBlocks;
import com.synergy.quern.init.types.zItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DataLang extends LanguageProvider implements LangGenerators {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                addBlock(zBlocks.QUERN, "Quern");

                zItems.zItem.getEntries().forEach(i -> addItem(i, named(i)));

                advKey("quern", "It spin!", "Craft a quern to process resources into dusts");

                add(MODULE_ID + ".jei.quern.title", "Milling Recipes");

                add(MODULE_ID + ".jei.quern.speed", "Min Speed : %s");

                add(MODULE_ID + ".jade.quern.speed", "Current Speed : %s");

                add("config.jade.plugin_" + MODULE_ID + ".quern_speed", "Quern Speed Info");

                add(MODULE_ID + ".configuration.speed_increaser", "Quern Speed Increaser");
                add(MODULE_ID + ".configuration.disable_item_render", "Disable Item Renderer");
                add(MODULE_ID + ".configuration.max_speed", "Max Quern Speed");

        }

}
