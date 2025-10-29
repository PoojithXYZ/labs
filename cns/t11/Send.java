import java.io.*;
import java.security.*;

public class Send {
  public static void main(String args[]) {
    String data;
    data =
        "This have I thought good to deliver thee, "
            + "that thou mightst not lose the dues of rejoicing "
            + "by being ignorant of what greatness is promised thee.";
    try {
      FileOutputStream fos = new FileOutputStream("test");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
      ks.load(
          new FileInputStream(System.getProperty("user.home") + File.separator + ".keystore"),
          null);
      char c[] = new char[args[1].length()];
      args[1].getChars(0, c.length, c, 0);
      PrivateKey pk = (PrivateKey) ks.getKey(args[0], c);
      Signature s = Signature.getInstance("DSA");
      s.initSign(pk);
      byte buf[] = data.getBytes();
      s.update(buf);
      oos.writeObject(data);
      oos.writeObject(s.sign());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
