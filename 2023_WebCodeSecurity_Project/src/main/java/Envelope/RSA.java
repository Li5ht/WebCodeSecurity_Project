package Envelope;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.NoSuchAlgorithmException;

import java.util.Scanner;

public class RSA {
	public static void main(String[] args) throws NoSuchAlgorithmException {
        // KeyPair 생성
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keyPair = kpg.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 공개키 저장
        Scanner scanner = new Scanner(System.in);
        System.out.print("공개키를 저장할 파일 이름: ");
        String publicFileName = scanner.nextLine();
        saveKey(publicKey, publicFileName);

        // 개인키 저장
        System.out.print("개인키를 저장할 파일 이름: ");
        String privateFileName = scanner.nextLine();
        saveKey(privateKey, privateFileName);

        System.out.println("키가 성공적으로 저장되었습니다.");
        
        scanner.close();
    }

    private static void saveKey(Key key, String fileName) {
        try (OutputStream out = new FileOutputStream(fileName)) {
            byte[] encodedKey = key.getEncoded();
            out.write(encodedKey);
        } catch (IOException e) {
            throw new RuntimeException("키를 저장하는 중에 오류가 발생했습니다.", e);
        }
    }

}
