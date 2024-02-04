package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ClampUtil;
import com.yusufsezer.util.ImageUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sepia implements IFilter {

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

                int newRed = (int) ((red * 0.393) + (green * 0.769) + (blue * 0.189));
                int newGreen = (int) ((red * 0.349) + (green * 0.686) + (blue * 0.168));
                int newBlue = (int) ((red * 0.272) + (green * 0.534) + (blue * 0.131));

                newRed = ClampUtil.clampToByte(newRed);
                newGreen = ClampUtil.clampToByte(newGreen);
                newBlue = ClampUtil.clampToByte(newBlue);

                Color newColor = new Color(newRed, newGreen, newBlue);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return bufferedImage;
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
