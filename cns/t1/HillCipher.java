/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

class Basic {
  String allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  int indexOfChar(char c) {
    for (int i = 0; i < allChar.length(); i++) {
      if (allChar.charAt(i) == c) return i;
    }
    return -1;
  }

  char charAtIndex(int pos) {
    return allChar.charAt(pos % 26); // edit by me % 26
  }
}

class Hill {
  Basic b1 = new Basic();
  int block;
  int key[][];

  Hill(int block) {
    this.block = block;
    this.key = new int[block][block];
  }

  void keyInsert() throws Exception {
    Scanner scn = new Scanner(System.in);
    System.out.println("Enter key Matrix");
    for (int i = 0; i < block; i++) {
      for (int j = 0; j < block; j++) {
        key[i][j] = scn.nextInt();
      }
    }
  }

  void KeyInverseInsert() throws Exception {
    Scanner scn = new Scanner(System.in);
    System.out.println("Enter key Inverse Matrix:");
    for (int i = 0; i < block; i++) {
      for (int j = 0; j < block; j++) {
        key[i][j] = scn.nextInt();
      }
    }
  }

  String encryptBlock(String plain) throws Exception {
    plain = plain.toUpperCase();
    int a[][] = new int[block][1], sum = 0;
    int cipherMatrix[][] = new int[block][1];
    String cipher = "";

    for (int i = 0; i < block; i++) {
      a[i][0] = b1.indexOfChar(plain.charAt(i));
    }

    for (int i = 0; i < block; i++) {
      for (int j = 0; j < 1; j++) {
        for (int k = 0; k < block; k++) {
          sum = sum + key[i][k] * a[k][j];
        }
        cipherMatrix[i][j] = sum % 26;
        sum = 0;
      }
    }

    for (int i = 0; i < block; i++) {
      cipher += b1.charAtIndex(cipherMatrix[i][0]);
    }

    return cipher;
  }

  String encrypt(String plainText) throws Exception {
    String cipherText = "";
    keyInsert();
    plainText = plainText.toUpperCase();
    int len = plainText.length();

    while (len % block != 0) {
      plainText += "X";
      len = plainText.length();
    }

    for (int i = 0; i < len; i = i + block) {
      cipherText += encryptBlock(plainText.substring(i, i + block));
      cipherText += " ";
    }

    return cipherText;
  }

  String decryptBl(String cipher) throws Exception {
    cipher = cipher.toUpperCase();
    int a[][] = new int[block][1], sum = 0;
    int plainMatrix[][] = new int[block][1];
    String plain = "";

    for (int i = 0; i < block; i++) {
      a[i][0] = b1.indexOfChar(cipher.charAt(i));
    }

    for (int i = 0; i < block; i++) {
      for (int j = 0; j < 1; j++) {
        for (int k = 0; k < block; k++) {
          sum = sum + key[i][k] * a[k][j];
        }
        while (sum < 0) {
          sum += 26;
        }
        plainMatrix[i][j] = sum;
        sum = 0;
      }
    }

    for (int i = 0; i < block; i++) {
      plain += b1.charAtIndex(plainMatrix[i][0]);
    }

    return plain;
  }

  String Decrypt(String cipherText) throws Exception {
    String plainText = "";
    KeyInverseInsert();
    cipherText = cipherText.replaceAll(" ", "");
    cipherText = cipherText.toUpperCase();
    int len = cipherText.length();

    for (int i = 0; i < len; i = i + block) {
      plainText += decryptBl(cipherText.substring(i, i + block));
      plainText += " ";
    }

    return plainText;
  }
}

class HillCipher {
  public static void main(String args[]) throws Exception {
    String plainText, cipherText;
    int block;
    Scanner scn = new Scanner(System.in);
    System.out.println("Enter plain-text:");
    plainText = scn.nextLine();
    System.out.println("Enter block size of matrix:");
    block = scn.nextInt();
    Hill hill = new Hill(block);
    plainText = plainText.replaceAll(" ", "");
    cipherText = hill.encrypt(plainText);
    System.out.println("Encrypted Text is:\n" + cipherText);
    String decryptedText = hill.Decrypt(cipherText);
    System.out.println("Decrypted Text is:\n" + decryptedText);
  }
}
