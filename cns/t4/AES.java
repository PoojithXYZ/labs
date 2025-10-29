import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AES {

  // Method to generate a 128-bit AES Secret Key
  public static SecretKey generateKey() throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128); // AES key size in bits
    return keyGen.generateKey();
  }

  // Method to encrypt plain text
  public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // ECB mode, padding used
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
    return Base64.getEncoder().encodeToString(encryptedBytes); // Encode to Base64 for readability
  }

  // Method to decrypt cipher text
  public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    return new String(decryptedBytes);
  }

  // Main method
  public static void main(String[] args) {
    try {
      String inputText = "ThisIs16ByteText"; // 128 bits = 16 bytes (1 byte = 8 bits)

      // Generate AES key
      SecretKey secretKey = generateKey();

      // Encrypt
      String encrypted = encrypt(inputText, secretKey);
      System.out.println("Original Text : " + inputText);
      System.out.println("Encrypted Text: " + encrypted);

      // Decrypt
      String decrypted = decrypt(encrypted, secretKey);
      System.out.println("Decrypted Text: " + decrypted);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
