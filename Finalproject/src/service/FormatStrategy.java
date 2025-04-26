package service;

import java.io.File;
import java.io.IOException;

public interface FormatStrategy {
    void convertFormat(File inputFile, File outputFile) throws IOException;
}
