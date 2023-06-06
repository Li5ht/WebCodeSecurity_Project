package Envelope;

import java.security.*;
import java.util.Base64;

//import javax.servlet.http.HttpServlet;

public class EnvelopeGenerator {
	private KeyPairGenerator keyPairGenerator;
    
    public EnvelopeGenerator() throws NoSuchAlgorithmException {
        // 비대칭키(공개키/개인키)를 생성하기 위한 KeyPairGenerator 초기화
        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
    }
    
    public KeyPair generateKeyPair() {
        // 비대칭키(공개키/개인키) 생성
        return keyPairGenerator.generateKeyPair();
    }
    
    public String generateEnvelope(String document, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 문서의 전자봉투 생성
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(document.getBytes());
        byte[] signedData = signature.sign();
        return Base64.getEncoder().encodeToString(signedData);
    }
}
