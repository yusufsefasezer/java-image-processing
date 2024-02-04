package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Prewitt implements IFilter {

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);

        float[] prewittKernel = {
            -1, 0, 1,
            -1, 0, 1,
            -1, 0, 1
        };

        Kernel kernel = new Kernel(3, 3, prewittKernel);
        ConvolveOp convolveOp = new ConvolveOp(kernel);

        return convolveOp.filter(bufferedImage, null);
    }

}
