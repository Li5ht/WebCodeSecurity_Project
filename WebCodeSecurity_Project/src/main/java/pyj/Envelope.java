package pyj;

import java.io.*;
import java.security.*;
import java.util.Scanner;


public class Envelope {
	
	/* chat gpt code 주석처리
	 * private KeyPair keyPair;
	 * 
	 * public Envelope() { //생성자에서 비대칭키 생성 generateKeyPair(); }
	 * 
	 * public void generateKeyPair() { try { //비대칭키 생성 KeyPairGenerator
	 * keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	 * keyPairGenerator.initialize(2048); keyPair =
	 * keyPairGenerator.generateKeyPair(); } catch (NoSuchAlgorithmException e) {
	 * e.printStackTrace(); } }
	 * 
	 * public String createEnvelope(String document) { try { // 전자봉투 생성 Signature
	 * signature = Signature.getInstance("SHA256withRSA");
	 * signature.initSign(keyPair.getPrivate());
	 * signature.update(document.getBytes()); byte[] signedBytes = signature.sign();
	 * 
	 * // Base64로 인코딩하여 반환 return Base64.getEncoder().encodeToString(signedBytes); }
	 * catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e)
	 * { e.printStackTrace(); } return null; }
	 * 
	 * public boolean verifyEnvelope(String document, String envelope) { try { //
	 * 전자봉투 검증 Signature signature = Signature.getInstance("SHA256withRSA");
	 * signature.initVerify(keyPair.getPublic());
	 * signature.update(document.getBytes()); byte[] envelopeBytes =
	 * Base64.getDecoder().decode(envelope);
	 * 
	 * return signature.verify(envelopeBytes); } catch (NoSuchAlgorithmException |
	 * InvalidKeyException | SignatureException e) { e.printStackTrace(); } return
	 * false; }
	 */
    public static void main(String[] args) {
    	
    	// KeyPair 생성
    	KeyPairGenerator gen  = KeyPairGenerator.getInstance("RSA");
    	gen.initialize(1024);
    	KeyPair pair = gen.generateKeyPair();
    	
    	PublicKey pub = pair.getPublic();
    	PrivateKey pri = pair.getPrivate();
    	
    	
    	//2. byte[]로 가져와서 같이 확인 후 몇 개 찍어보기
    	byte[] pubByte = pub.getEncoded();
    	byte[] priByte = pri.getEncoded();
    	System.out.println("생성된 공개키 정보: ");
    	System.out.println("키의 길이(byte): "+ pubByte.length);
    	for (byte bytes : pubByte) {
    		System.out.print(String.format("%02x", bytes)+"\t");
    	}
    	
    	System.out.println("\n생성된 개인키 정보 : ");
    	System.out.println("키의 길이(byte): "+ priByte.length);
    	for (byte bytes : priByte) {
    		System.out.print(String.format("%02x", bytes)+"\t");
    	}
    	
    	
    	//3. KeyPair 파이에 저장
    	Scanner sc = new Scanner(System.in);
    	System.out.print("공개키를 저장할 파일 이름 : ");
    	String pubFile = sc.nextLine();
    	
    	
    	try(FileOutputStream out = new FileOutputStream(pubFile)){
    		try (ObjectOutputStream outStream = new ObjectOutputStream(out)){
    			outStream.writeObject(pub);
    		}
    	}catch (FileNotFoundException e) {
    		throw new RuntimeException(e);
    	}catch (IOException e) {
    		throw new RuntimeException(e);
    	}
    	
    	System.out.print("개인키를 저장할 파일 이름 : ");
    	String priFile = sc.nextLine();
    	
    	try(FileOutputStream out = new FileOutputStream(pubFile)){
    		try (ObjectOutputStream outStream = new ObjectOutputStream(out)){
    			outStream.writeObject(pri);
    		}
    	}catch (FileNotFoundException e) {
    	}catch (IOException e) {
    		throw new RuntimeException(e);
    	}
    	
    	
    	
		/*chat gpt code 주석처리
		 * // 문서 생성 String document = "This is a sample document.";
		 * 
		 * // 전자봉투 생성 및 검증 Envelope envelope = new Envelope(); String createdEnvelope =
		 * envelope.createEnvelope(document); boolean isVerified =
		 * envelope.verifyEnvelope(document, createdEnvelope);
		 * 
		 * System.out.println("Created Envelope: " + createdEnvelope);
		 * System.out.println("Is Verified: " + isVerified);
		 */
    }

}
