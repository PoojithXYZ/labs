import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AES {

  public static SecretKey generateKey() throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128); 
    return keyGen.generateKey();
  }

  public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
    return Base64.getEncoder().encodeToString(encryptedBytes); 
  }

  public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
    return new String(decryptedBytes);
  }

  public static void main(String[] args) {
    try {
      String inputText = "ThisIs16ByteText"; 

      SecretKey secretKey = generateKey();

      String encrypted = encrypt(inputText, secretKey);
      System.out.println("Original Text : " + inputText);
      System.out.println("Encrypted Text: " + encrypted);

      String decrypted = decrypt(encrypted, secretKey);
      System.out.println("Decrypted Text: " + decrypted);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
