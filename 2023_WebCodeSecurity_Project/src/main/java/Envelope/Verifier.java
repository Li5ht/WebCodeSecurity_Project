package Envelope;

import java.security.*;
import java.util.Base64;

public class Verifier {
	private String document;
	private String envelope;
	private PublicKey publicKey;

	
    public String getDocument() {return document;}
	public void setDocument(String document) {this.document = document;}

	public String getEnvelope() {return envelope;}
	public void setEnvelope(String envelope) {this.envelope = envelope;}

	public PublicKey getPublicKey() {return publicKey;}
	public void setPublicKey(PublicKey publicKey) {this.publicKey = publicKey;}
	
	
	public boolean verifyRSA(String document, String envelope, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
	    // 전자봉투 검증
	    Signature signature = Signature.getInstance("SHA256withRSA");
	    signature.initVerify(publicKey);
	    signature.update(document.getBytes());
	    byte[] envelopeBytes = Base64.getDecoder().decode(envelope);
	    return signature.verify(envelopeBytes);
	}
}