package KeyManager;

import java.security.*;
import java.util.Base64;

public class KeyManager {
    private KeyPair keyPair;
    private SecretKey secretKey;
    
    public KeyManager() {
        // 비대칭키와 대칭키를 생성/복구하기 위한 KeyPair와 SecretKey 초기화
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
            
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    public KeyPair getKeyPair() {
        return keyPair;
    }
    
    public SecretKey getSecretKey() {
        return secretKey;
    }
    
    public String encodePublicKey(PublicKey publicKey) {
        // 공개키를 Base64로 인코딩하여 문자열로 반환
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    
    public PublicKey decodePublicKey(String encodedPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 문자열로 인코딩된 공개키를 디코딩하여 PublicKey 객체로 반환
        byte[] publicKeyBytes = Base64.getDecoder().decode(encodedPublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        return keyFactory.generatePublic(keySpec);
    }
    
    public String encodePrivateKey(PrivateKey privateKey) {
        // 개인키를 Base64로 인코딩하여 문자열로 반환
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
    
    public PrivateKey decodePrivateKey(String encodedPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 문자열로 인코딩된 개인키를 디코딩하여 PrivateKey 객체로 반환
        byte[] privateKeyBytes = Base64.getDecoder().decode(encodedPrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(keySpec);
    }
    
    public String encodeSecretKey(SecretKey secretKey) {
        // 대칭키를 Base64로 인코딩하여 문자열로 반환
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
    
    public SecretKey decodeSecretKey(String encodedSecretKey) {
        // 문자열로 인코딩된 대칭키를 디코딩하여 SecretKey 객체로 반환
        byte[] secretKeyBytes = Base64.getDecoder().decode(encodedSecretKey);
        return new SecretKeySpec(secretKeyBytes, "AES");
    }
}

