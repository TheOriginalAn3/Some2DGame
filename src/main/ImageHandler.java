package main;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageHandler {
    public static BufferedImage readImage(String pathToImg) throws IOException {
        BufferedImage img = null;
        File file = new File(pathToImg);
        if (file.exists()) {
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                img = ImageIO.read(inputStream);
            }
        }
        return img;
    }
    // Alternative:
    // public static BufferedImage readImage(String pathToImg) throws IOException {
    //     BufferedImage img = null;
    //     File file = new File(pathToImg);
    //     if (file.exists()) {
    //         img = ImageIO.read(file);
    //     }
    //     return img;
    // }
}
