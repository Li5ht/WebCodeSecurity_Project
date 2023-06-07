package Envelope;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AES {
	public static void main(String[] args) throws NoSuchAlgorithmException {
        // Key 생성
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // 대칭키 저장
        Scanner scanner = new Scanner(System.in);
        System.out.print("대칭키(비밀키)를 저장할 파일 이름: ");
        String fileName = scanner.nextLine();
        saveKey(secretKey, fileName);

        System.out.println("키가 성공적으로 저장되었습니다.");
        
        scanner.close();
    }

    private static void saveKey(SecretKey key, String fileName) {
        try (OutputStream out = new FileOutputStream(fileName)) {
            byte[] encodedKey = key.getEncoded();
            out.write(encodedKey);
        } catch (IOException e) {
            throw new RuntimeException("키를 저장하는 중에 오류가 발생했습니다.", e);
        }
    }

}
