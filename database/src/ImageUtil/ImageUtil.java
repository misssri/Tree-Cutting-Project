package ImageUtil;

import java.util.Base64;

public class ImageUtil {
    public static String encodeImage(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}