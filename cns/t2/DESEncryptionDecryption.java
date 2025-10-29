import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DESEncryptionDecryption {

  public static SecretKey generateKey() throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance("DES");
    keyGen.init(56);
    return keyGen.generateKey();
  }

  public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    return new String(decryptedBytes);
  }

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Enter a message to encrypt: ");
      String message = scanner.nextLine();

      SecretKey secretKey = generateKey();
      String encrypted = encrypt(message, secretKey);
      System.out.println("Encrypted Text: " + encrypted);
      String decrypted = decrypt(encrypted, secretKey);
      System.out.println("Decrypted Text: " + decrypted);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
