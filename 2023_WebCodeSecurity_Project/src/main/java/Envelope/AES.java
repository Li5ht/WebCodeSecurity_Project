package Envelope;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AES {
	public static void main(String[] args) throws NoSuchAlgorithmException {
        // Key 생성
		Scanner scanner = new Scanner(System.in);
		
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        // 대칭키 저장
        System.out.print("대칭키(비밀키)를 저장할 파일 이름: ");
        String fileName = scanner.nextLine();
        saveKey(secretKey, fileName);
        
        //대칭키 출력
        System.out.println("\n생성된 대칭키 정보: ");
        byte[] secretKeyBytes = secretKey.getEncoded();
        System.out.println("키의 길이 (bytes): " + secretKeyBytes.length);

        for (byte bytes : secretKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        
        System.out.println("\n키가 성공적으로 저장되었습니다.");
        
        scanner.close();
    }
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b) + "\t");
        }
        return sb.toString();
    }

    private static void saveKey(SecretKey key, String fileName) {
        try (OutputStream output = new FileOutputStream(fileName,false);
   	         OutputStreamWriter osw = new OutputStreamWriter(output, "UTF-8")) {
			byte[] encodedKey = key.getEncoded();
			osw.write(bytesToHex(encodedKey));
        } catch (IOException e) {
            throw new RuntimeException("키를 저장하는 중에 오류가 발생했습니다.", e);
        }
    }

}
