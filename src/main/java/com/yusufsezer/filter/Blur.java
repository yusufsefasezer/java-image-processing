package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Blur implements IFilter {

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);

        float[] blurMatrix = {
            1 / 9f, 1 / 9f, 1 / 9f,
            1 / 9f, 1 / 9f, 1 / 9f,
            1 / 9f, 1 / 9f, 1 / 9f
        };

        Kernel kernel = new Kernel(3, 3, blurMatrix);
        ConvolveOp convolveOp = new ConvolveOp(kernel);

        return convolveOp.filter(bufferedImage, null);
    }

}
