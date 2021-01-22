package eus.healthit.bchef.core.api;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;

public class ImageRepository {

	public static String saveImage(String imageString) throws SQLException {
		if (imageString.equals("default")) {
			return "default";
		}
		BufferedImage img = (BufferedImage) decodeImage(imageString);
		File file = null;
		try {
			file = new File("main/resources/" + UUID.randomUUID().toString() + ".jpg");
			file.createNewFile();
			ImageIO.write(img, "jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file.getPath();
	}

	public static Image decodeImage(String codedImage) {
		try {
			if (codedImage.equals("default")) {
				return ImageIO.read(new File("resources/recipeIcons/defaultRecipeStepIcon.jpg"));
			}
			byte[] bytes = Base64.getDecoder().decode(codedImage);
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			BufferedImage bImage2 = ImageIO.read(bis);
			return bImage2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encodeImage(String pathString) {
		try {
			if (!pathString.equals("nochange")) {
				File file = new File(pathString);
				String string = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
				return string;
			}
			return "nochange";
		} catch (Exception e) {
			return "default";
		}
	}

}
