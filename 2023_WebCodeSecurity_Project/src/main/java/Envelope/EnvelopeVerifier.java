package Envelope;

import java.security.*;
import java.util.Base64;

public class EnvelopeVerifier {
    public boolean verifyEnvelope(String document, String envelope, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 전자봉투의 유효성 검증
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(document.getBytes());
        byte[] envelopeData = Base64.getDecoder().decode(envelope);
        return signature.verify(envelopeData);
    }
}

