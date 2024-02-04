# Java Image Processing

The project utilizes Java's robust features to implement various image processing techniques.

The Java Image Processing project is an image processing application developed in Java, utilizing the Swing library for the graphical user interface (GUI). The project employs various classes from the AWT (Abstract Window Toolkit) package to perform image processing operations. Notable classes include Image, BufferedImage, Kernel, ConvolveOp, ColorSpace, and ColorConvertOp, all of which are integral components of Java's standard library.

The construction of the project is facilitated by the Maven build tool. The development environment relies on Java version 21 to ensure compatibility and take advantage of the latest features.

## Image Filters:

The project encompasses a diverse set of image filters to enhance and manipulate images. These filters include:

- BlackAndWhite
- Blur
- Brightness
- Contrast
- Invert
- Noise
- Prewitt
- Sepia
- Sharpen
- Sobel
- Threshold
- Vintage

## Helper Classes:

Several auxiliary classes have been implemented to support the functionality of the project.

**ClampUtils:** A utility class providing methods for clamping values within a specified range.
**ClassUtils:** A utility class offering miscellaneous methods for class-related operations.
**ImageUtils:** A utility class containing toBufferedImage method to assist in image processing tasks.

The combination of Swing for the user interface, AWT for image processing operations, Maven for project management, and Java 21 for language features results in a robust and versatile image processing application. The project's array of filters and supporting utility classes make it a comprehensive tool for manipulating and transforming images in a Java environment.

## [Download](https://github.com/yusufsefasezer/java-image-processing/archive/master.zip)

## How to run

Maven must be installed to run this application.

If you have maven execute the below command to run.

```
mvn package
java -jar target\java-image-processing.jar
```

# License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details

Created by [Yusuf Sezer](https://www.yusufsezer.com)
