package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Brightness implements IFilter {

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);
        RescaleOp rescaleOp = new RescaleOp(2.0f, 0, null);
        return rescaleOp.filter(bufferedImage, null);
    }

}
