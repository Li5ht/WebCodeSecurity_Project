package Envelope;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Scanner;

import java.nio.charset.StandardCharsets;

public class AES {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // Key 생성
        Scanner scanner = new Scanner(System.in);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // 256비트 키 크기로 변경
        SecretKey secretKey = keyGenerator.generateKey();
        
        // 대칭키 저장
        System.out.print("대칭키(비밀키)를 저장할 파일 이름: ");
        String fileName = scanner.nextLine();
        
        // 비밀키를 해시 함수를 사용하여 저장
        String hashedKey = hashKey(secretKey);
        saveKey(hashedKey, fileName);

        // 대칭키 출력
        System.out.println("\n생성된 대칭키 정보: ");
        byte[] secretKeyBytes = secretKey.getEncoded();
        System.out.println("키의 길이 (bytes): " + secretKeyBytes.length);

        for (byte bytes : secretKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }

        System.out.println("\n키가 성공적으로 저장되었습니다.");

        scanner.close();
    }

    private static String hashKey(SecretKey key) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(key.getEncoded());
        return bytesToHex(hashedBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static void saveKey(String key, String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException("파일 이름이 유효하지 않습니다.");
        }

        try (OutputStream output = new FileOutputStream(fileName)) {
            output.write(key.getBytes(StandardCharsets.UTF_8));
        }
    }
}
