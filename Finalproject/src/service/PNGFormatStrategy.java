package service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PNGFormatStrategy implements FormatStrategy {
    @Override
    public void convertFormat(File inputFile, File outputFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputFile);
        ImageIO.write(bufferedImage, "png", outputFile);
    }
}
