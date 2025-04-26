package model;

import javafx.scene.image.Image;

public class ImageFile {
    private Image image;
    private String info;

    public ImageFile(Image image, String info) {
        this.image = image;
        this.info = info;
    }

    public Image getImage() {
        return image;
    }

    public String getInfo() {
        return info;
    }
}
