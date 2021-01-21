package eus.healthit.bchef.core.api;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageCoder {
	public static final String PROBA1 = "proba1.jfif";
	public static final String PROBA2 = "proba2.png";
	public static final String PROBA3 = "proba3.jfif";


	public  static String encodeImage(String pathString) {
		try {
			File file = new File(pathString);
			String string = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image decodeImage(String codedImage) {
		try {
			byte [] bytes = Base64.getDecoder().decode(codedImage);
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			BufferedImage bImage2 = ImageIO.read(bis);
			return bImage2;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void saveImage(BufferedImage image, String path) throws IOException {
			ImageIO.write(image, "jpg", new File(path) );
	}
	
}
