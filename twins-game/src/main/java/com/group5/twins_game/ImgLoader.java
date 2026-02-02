package com.group5.twins_game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class contains the function that
 * can read image from the path given
 *
 * @author Jin Yang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Cell
 * @see Diamond
 * @see DoorCell
 * @see Entity
 * @see Key
 * @see Player
 * @see Trap
 */
public class ImgLoader {
    /**
     * This readImg function returns the BufferedImage
     * if there is a corresponding file in the given path.
     * The path, image name, image type all need to be correct
     * in order to successfully read the image
     *
     * @param path ignore the resources root, start from the
     *             directory under the resources root, up to
     *             and include the file_name.file_type
     * @return Return a BufferedImage object when path is
     *              correct and the image is successfully read.
     * @throws IOException Shows Error that no file can be
     *              found in the given path
     *
     * @author Jin Yang
     * @author Winnie Chan
     */
    public BufferedImage readImg(String path) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            if (inputStream != null) {
                return ImageIO.read(inputStream);
            } else {
                throw new IOException("no image name this in the path: " + path);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
