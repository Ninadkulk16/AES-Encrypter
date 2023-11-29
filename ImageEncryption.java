package new1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ImageEncryption {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String SECRET_KEY = "my_secret_key_16";

    public static void main(String[] args) {
        String inputFile = "/Users/kulka/Downloads/trial.png"; 
        String outputFile = "/Users/kulka/Downloads/res.png";

        try {
            encryptFile(inputFile, outputFile);
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private static void encryptFile(String inputFile, String outputFile) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        // Read the input media file
        InputStream inputStream = new FileInputStream(inputFile);

        // Create the output encrypted file
        OutputStream outputStream = new FileOutputStream(outputFile);

        // Create the cipher and initialize it for encryption
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // Encrypt the media file
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // Close streams
        cipherInputStream.close();
        inputStream.close();
        outputStream.close();

        System.out.println("Encryption completed successfully.");
    }
}

