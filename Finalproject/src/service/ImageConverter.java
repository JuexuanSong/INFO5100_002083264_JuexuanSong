package service;

import java.io.File;
import java.io.IOException;

public class ImageConverter {
    private FormatStrategy formatStrategy;

    public void setFormatStrategy(FormatStrategy formatStrategy) {
        this.formatStrategy = formatStrategy;
    }

    public void convert(File inputFile, File outputFile) throws IOException {
        if (formatStrategy == null) {
            throw new IllegalStateException("Format strategy not set!");
        }
        formatStrategy.convertFormat(inputFile, outputFile);
    }
}
