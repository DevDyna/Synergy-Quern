package com.synergy.quern.utils;

import static com.synergy.quern.Main.ID;

import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

/**
 * Converted to ONLY support JEI render (atm)
 */
public class Image {

    private int x;
    private int y;
    private String rl;
    private String modid = ID;
    private int xo = 0;
    private int yo = 0;
    // private int tx = x;
    // private int ty = y;

    private int u = 0;
    private int v = 0;
    // private int index = 0;

    public Image() {

    }

    public static Image of() {
        return new Image();
    }

    public Image size(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Deprecated
    public Image sizeTexture(int textureWidth, int textureHeight) {
        // this.tx = textureWidth;
        // this.ty = textureHeight;
        return this;
    }

    public Image uv(int u, int v) {
        this.u = u;
        this.v = v;
        return this;
    }

    public Image offset(int xo, int yo) {
        this.xo = xo;
        this.yo = yo;
        return this;
    }

    @Deprecated
    public Image index(int i) {
        // this.index = i;
        return this;
    }

    public Image rl(String image) {
        this.rl = image;
        return this;
    }

    public Image rl(String modid, String image) {
        this.modid = modid;
        this.rl = image;
        return this;
    }

    public Image rl(Identifier rl) {
        this.modid = rl.getNamespace();
        this.rl = rl.getPath();
        return this;
    }

    /**
     * JEI usage
     */
    public void render(IGuiHelper h, GuiGraphicsExtractor g) {
        h.drawableBuilder(com.synergy.quern.utils.x.rl(modid, rl), u, v, x, y).setTextureSize(x, y).build()
                .draw(g, xo, yo);
    }

}