package com.yusufsezer.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageUtils {

    public static BufferedImage toBufferedImage(Image image) {
        Objects.requireNonNull(image);

        if (image instanceof BufferedImage bufferedImage) {
            return bufferedImage;
        }

        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();

        return bufferedImage;
    }

}
