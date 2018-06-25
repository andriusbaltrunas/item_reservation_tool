package com.item.reservation.tool.utils;

import lombok.extern.log4j.Log4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Log4j
public class ImageUtils {

    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 400;

    public static byte[] resizeImage(byte[] bytes) {
        ByteArrayInputStream ba = new ByteArrayInputStream(bytes);
        byte[] byteResponse = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(ba);
            Image image = bufferedImage.getScaledInstance(MAX_WIDTH, MAX_HEIGHT, Image.SCALE_SMOOTH);

            BufferedImage responseBufferedImage = new BufferedImage(MAX_WIDTH, MAX_HEIGHT, BufferedImage.TYPE_INT_RGB);
            responseBufferedImage.getGraphics().drawImage(image, 0, 0, new Color(0, 0, 0), null);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(responseBufferedImage, "jpg", output);

            byteResponse = output.toByteArray();
        } catch (IOException e) {
            log.error("cant resize image ", e);
        }
        return byteResponse;
    }
}
