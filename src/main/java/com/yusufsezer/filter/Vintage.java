package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Vintage implements IFilter {

    private final int value;

    public Vintage() {
        this(150);
    }

    public Vintage(int value) {
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

                int newRed = green;
                int newGreen = red;
                int newBlue = value;

                Color newColor = new Color(newRed, newGreen, newBlue);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return new Contrast(50f).filter(bufferedImage);
    }

}
