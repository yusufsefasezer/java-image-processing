package com.yusufsezer.filter;

import com.yusufsezer.contract.IFilter;
import com.yusufsezer.util.ImageUtils;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class BlackAndWhite implements IFilter {

    @Override
    public Image filter(Image image) {
        BufferedImage bufferedImage = ImageUtils.toBufferedImage(image);

//        int width = bufferedImage.getWidth();
//        int height = bufferedImage.getHeight();
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                int rgb = bufferedImage.getRGB(x, y);
//                Color color = new Color(rgb);
//                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
//                Color newColor = new Color(gray, gray, gray);
//                bufferedImage.setRGB(x, y, newColor.getRGB());
//            }
//        }
//        return bufferedImage;
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        return op.filter(bufferedImage, null);
    }

}
