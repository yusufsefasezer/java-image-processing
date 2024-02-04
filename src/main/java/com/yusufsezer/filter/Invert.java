package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Invert implements IFilter {

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);
        RescaleOp rescaleOp = new RescaleOp(-1.0f, 255.0f, null);
        return rescaleOp.filter(bufferedImage, null);
    }

}
