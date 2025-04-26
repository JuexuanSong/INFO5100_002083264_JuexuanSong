package service;

import javafx.scene.image.Image;
import model.ImageFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageLoader {

    public static ImageFile loadImage(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            Image image = new Image(fis);
            String info = String.format("Width: %.0f, Height: %.0f", image.getWidth(), image.getHeight());
            return new ImageFile(image, info);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
