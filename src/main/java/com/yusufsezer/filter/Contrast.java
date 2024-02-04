package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Contrast implements IFilter {

    private final float value;

    public Contrast() {
        this(1.5f);
    }

    public Contrast(float value) {
        this.value = value;
    }

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);
        RescaleOp rescaleOp = new RescaleOp(1.5f, 0, null);
        return rescaleOp.filter(bufferedImage, null);
    }

}
