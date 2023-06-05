package pyj;

import java.security.*;
import java.util.Base64;


public class Envelope {
	
	private KeyPair keyPair;
	
	public Envelope() {
		//생성자에서 비댕치키 생성
		generateKeyPair();
	}
	
	public void generateKeyPair() {
		try {
			//비대칭키 생성
			KeyPairGenerator keyPairGenerator = keyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
