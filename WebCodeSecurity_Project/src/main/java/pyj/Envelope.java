package pyj;

import java.security.*;
import java.util.Base64;


public class Envelope {
	
	private KeyPair keyPair;
	
	public Envelope() {
		//생성자에서 비대칭키 생성
		generateKeyPair();
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

	public String createEnvelope(String document) {
        try {
            // 전자봉투 생성
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(keyPair.getPrivate());
            signature.update(document.getBytes());
            byte[] signedBytes = signature.sign();

            // Base64로 인코딩하여 반환
            return Base64.getEncoder().encodeToString(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyEnvelope(String document, String envelope) {
        try {
            // 전자봉투 검증
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(keyPair.getPublic());
            signature.update(document.getBytes());
            byte[] envelopeBytes = Base64.getDecoder().decode(envelope);

            return signature.verify(envelopeBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        // 문서 생성
        String document = "This is a sample document.";

        // 전자봉투 생성 및 검증
        Envelope envelope = new Envelope();
        String createdEnvelope = envelope.createEnvelope(document);
        boolean isVerified = envelope.verifyEnvelope(document, createdEnvelope);

        System.out.println("Created Envelope: " + createdEnvelope);
        System.out.println("Is Verified: " + isVerified);
    }

}
