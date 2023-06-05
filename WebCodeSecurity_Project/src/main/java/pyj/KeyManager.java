package pyj;

import java.security.*;
import java.util.Base64;

public class KeyManager {
	private KeyPair keyPair;

    public KeyManager() {
        // 생성자에서 비밀키 생성
        generateSecretKey();
    }
    
    public void generateKeyPair() {
		try {
			//비대칭키 생성
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

    public void generateSecretKey() {
        try {
            // 비밀키 생성
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();

            // Base64로 인코딩하여 출력
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("Generated Secret Key: " + encodedKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
