package Envelope;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class RSA {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
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

        // 공개키 출력해보기
        System.out.println("\n생성된 공개키 정보: ");
        byte[] publicKeyBytes = publicKey.getEncoded();
        System.out.println("키의 길이 (bytes): " + publicKeyBytes.length);
        System.out.println(bytesToHex(publicKeyBytes));

        // 공개키 문서 검증
        Verifier verifier = new Verifier();
        boolean isValid = verifier.verifyRSA(publicFileName, Base64.getEncoder().encodeToString(publicKeyBytes), publicKey);
        // 검증 결과 출력
        System.out.println("공개키 전자봉투 검증 결과: " + (isValid ? "유효함" : "유효하지 않음") + "\n");

        // 개인키 저장
        System.out.print("개인키를 저장할 파일 이름: ");
        String privateFileName = scanner.nextLine();
        saveKey(privateKey, privateFileName);

        // 개인키 출력해보기
        System.out.println("\n생성된 개인키 정보: ");
        byte[] privateKeyBytes = privateKey.getEncoded();
        System.out.println("키의 길이 (bytes): " + privateKeyBytes.length);
        System.out.println(bytesToHex(privateKeyBytes));

        System.out.println("키가 성공적으로 저장되었습니다.");

        scanner.close();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static void saveKey(Key key, String fileName) throws IOException {
        byte[] encodedKey = key.getEncoded();
        try (OutputStream output = new FileOutputStream(fileName)) {
            output.write(encodedKey);
        }
    }
}

