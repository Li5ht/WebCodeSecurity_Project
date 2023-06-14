package Envelope;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

public class Verifier {
	public boolean verifyRSA(String publicKeyFilePath, String signature, PublicKey publicKey) {
		try {
            byte[] signatureBytes = Base64.getDecoder().decode(signature);

            Signature verifier = Signature.getInstance("SHA256withRSA");
            verifier.initVerify(publicKey);

            Path publicKeyFile = Paths.get(publicKeyFilePath);
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile);

            verifier.update(publicKeyBytes);

            return verifier.verify(signatureBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | java.io.IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}