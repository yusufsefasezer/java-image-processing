package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ClampUtil;
import com.yusufsezer.util.ImageUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Noise implements IFilter {

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));

                int red = (color.getRed() + random(0, 255)) / 2;
                int green = (color.getGreen() + random(0, 255)) / 2;
                int blue = (color.getBlue() + random(0, 255)) / 2;

                red = ClampUtil.clampToByte(red);
                green = ClampUtil.clampToByte(green);
                blue = ClampUtil.clampToByte(blue);

                Color newColor = new Color(red, green, blue);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return bufferedImage;
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
