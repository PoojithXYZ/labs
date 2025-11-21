import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class BlowfishEncryption {

  public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("Blowfish");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF8"));
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("Blowfish");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    return new String(decrypted, "UTF8");
  }

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Enter text to encrypt: ");
      String plainText = scanner.nextLine();

      KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
      keyGen.init(128);
      SecretKey secretKey = keyGen.generateKey();

      String encryptedText = encrypt(plainText, secretKey);
      String decryptedText = decrypt(encryptedText, secretKey);

      System.out.println("Encrypted Text: " + encryptedText);
      System.out.println("Decrypted Text: " + decryptedText);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
