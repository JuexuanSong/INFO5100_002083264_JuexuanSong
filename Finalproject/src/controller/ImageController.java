package controller;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.ImageFile;
import service.ImageLoader;
import service.ImageConverter;
import service.FormatStrategy;
import service.JPEGFormatStrategy;
import service.PNGFormatStrategy;
import service.BMPFormatStrategy;

public class ImageController {

    private Stage primaryStage;
    private BorderPane root;
    private FlowPane imagePane;
    private ComboBox<String> formatComboBox;
    private Button convertButton;
    private ImageConverter imageConverter = new ImageConverter();
    private File selectedFile;

    public ImageController(Stage primaryStage, BorderPane root) {
        this.primaryStage = primaryStage;
        this.root = root;
        setupUI();
    }

    private void setupUI() {
        Button uploadButton = new Button("Upload Images");
        uploadButton.setOnAction(e -> uploadImages());

        HBox topPane = new HBox(uploadButton);
        topPane.setAlignment(Pos.CENTER);
        topPane.setSpacing(10);
        root.setTop(topPane);

        imagePane = new FlowPane();
        imagePane.setHgap(20);
        imagePane.setVgap(20);
        root.setCenter(imagePane);

        formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll("JPEG", "PNG", "BMP");
        formatComboBox.setValue("JPEG");

        convertButton = new Button("Convert and Download");
        convertButton.setOnAction(e -> {
            try {
                convertSelectedImage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        VBox bottomPane = new VBox(10, formatComboBox, convertButton);
        bottomPane.setAlignment(Pos.CENTER);
        root.setBottom(bottomPane);
    }

    private void uploadImages() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Images");
        List<File> files = fileChooser.showOpenMultipleDialog(primaryStage);

        if (files != null) {
            imagePane.getChildren().clear();
            for (File file : files) {
                ImageFile imageFile = ImageLoader.loadImage(file);
                if (imageFile != null) {
                    ImageView thumbnail = new ImageView(imageFile.getImage());
                    thumbnail.setFitWidth(100);
                    thumbnail.setFitHeight(100);
                    thumbnail.setPreserveRatio(true);

                    VBox vbox = new VBox(5, thumbnail, new Text(imageFile.getInfo()));
                    vbox.setAlignment(Pos.CENTER);

                    imagePane.getChildren().add(vbox);

                    // Remember the last selected file
                    selectedFile = file;
                }
            }
        }
    }

    private void convertSelectedImage() throws IOException {
        if (selectedFile == null) {
            return;
        }
        String selectedFormat = formatComboBox.getValue();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Converted Image");
        fileChooser.setInitialFileName(selectedFile.getName().split("\\.")[0] + "." + selectedFormat.toLowerCase());
        File outputFile = fileChooser.showSaveDialog(primaryStage);

        if (outputFile != null) {
            switch (selectedFormat) {
                case "JPEG" -> imageConverter.setFormatStrategy(new JPEGFormatStrategy());
                case "PNG" -> imageConverter.setFormatStrategy(new PNGFormatStrategy());
                case "BMP" -> imageConverter.setFormatStrategy(new BMPFormatStrategy());
            }
            imageConverter.convert(selectedFile, outputFile);
        }
    }
}
