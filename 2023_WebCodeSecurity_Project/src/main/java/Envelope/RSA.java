package Envelope;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.PrivateKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import java.util.Scanner;
//import Envelope.Verifier;

public class RSA {
	public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        // KeyPair 생성
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair keyPair = kpg.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();
        
        try (OutputStream output = new FileOutputStream(fileName, false);
                OutputStreamWriter osw = new OutputStreamWriter(output, "UTF-8")) {
               byte[] encodedKey = key.getEncoded();
               String encodedKeyBase64 = Base64.getEncoder().encodeToString(encodedKey);
               osw.write(encodedKeyBase64);
           } catch (IOException e) {
               throw new RuntimeException("키를 저장하는 중에 오류가 발생했습니다.", e);
           }
        
        //공개키 저장
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("공개키를 저장할 파일 이름: ");
        String publicFileName = scanner.nextLine();
        saveKey(publicKey, publicFileName);
        
        //공개키 출력해보기
        System.out.println("\n생성된 공개키 정보: ");
        System.out.println("키의 길이 (bytes): " + publicKeyBytes.length);
        for (byte bytes : publicKeyBytes) {
            System.out.print(String.format("%02x", bytes) + "\t");
        }
        System.out.println();
        
        //공개키 문서 검증
        Verifier verify = new Verifier();
        boolean isValid = verify.verifyRSA(publicFileName, Base64.getEncoder().encodeToString(publicKeyBytes), publicKey);

         //검증 결과 출력
        System.out.println("공개키 전자봉투 검증 결과: " + (isValid ? "유효함" : "유효하지 않음")+"\n");
        
        // 개인키 저장
        System.out.print("\n개인키를 저장할 파일 이름: ");
        String privateFileName = scanner.nextLine();
        saveKey(privateKey, privateFileName);
        
        //개인키 출력해보기
        System.out.println("\n생성된 개인키 정보: ");
        System.out.println("키의 길이 (bytes): " + privateKeyBytes.length);
        for (byte bytes : privateKeyBytes) {
            System.out.print(String.format("%02x", bytes));
        }

        System.out.println("\n키가 성공적으로 저장되었습니다.");
        
        scanner.close();
    }
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static void saveKey(Key key, String fileName) {
        try (OutputStream output = new FileOutputStream(fileName,false);
	         OutputStreamWriter osw = new OutputStreamWriter(output, "UTF-8")) {
	        	byte[] encodedKey = key.getEncoded();
	            String encodedKeyBase64 = Base64.getEncoder().encodeToString(encodedKey);
	            //osw.write(encodedKeyBase64);
	            osw.write(bytesToHex(encodedKey));
        } catch (IOException e) {
            throw new RuntimeException("키를 저장하는 중에 오류가 발생했습니다.", e);
        }
    }

}
