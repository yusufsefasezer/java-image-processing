package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Threshold implements IFilter {

    private final int value;

    public Threshold() {
        this(128);
    }

    public Threshold(int value) {
        this.value = value;
    }

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int intensity = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
                int newRGB = (intensity >= value) ? 255 : 0;

                Color newColor = new Color(newRGB, newRGB, newRGB);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return bufferedImage;
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
