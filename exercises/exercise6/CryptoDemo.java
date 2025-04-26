import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Base64;

public class CryptoDemo {
    public static void main(String[] args) throws Exception {
        Alice alice = new Alice();
        Bob bob = new Bob();

        // --- AES Symmetric Encryption/Decryption ---
        System.out.println("----- AES Symmetric Encryption -----");
        String aesMessage = "Hello Bob, it's Alice!";
        byte[] aesKey = alice.generateAESKey();
        byte[] encryptedAES = alice.encryptAES(aesMessage, aesKey);
        String decryptedAES = bob.decryptAES(encryptedAES, aesKey);
        System.out.println("Original Message: " + aesMessage);
        System.out.println("Decrypted Message: " + decryptedAES);

        // --- RSA Asymmetric Encryption/Decryption ---
        System.out.println("\n----- RSA Asymmetric Encryption -----");
        String rsaMessage = "Hey Alice, it's Bob!";
        byte[] encryptedRSA = bob.encryptRSA(rsaMessage, alice.getPublicKey());
        String decryptedRSA = alice.decryptRSA(encryptedRSA);
        System.out.println("Original Message: " + rsaMessage);
        System.out.println("Decrypted Message: " + decryptedRSA);

        // --- RSA Signing and Verification ---
        System.out.println("\n----- RSA Signing and Verification -----");
        String signMessage = "Important Message!";
        byte[] signature = alice.signMessage(signMessage);
        boolean isValid = bob.verifySignature(signMessage, signature, alice.getPublicKey());
        System.out.println("Signature valid? " + isValid);
    }
}

class Alice {
    private KeyPair rsaKeyPair;

    public Alice() throws Exception {
        rsaKeyPair = generateRSAKeyPair();
    }

    // AES - Generate random 256-bit key
    public byte[] generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey().getEncoded();
    }

    // AES Encrypt
    public byte[] encryptAES(String plainText, byte[] keyBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        byte[] iv = SecureRandom.getInstanceStrong().generateSeed(12); // 12 bytes IV for GCM
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        byte[] result = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(cipherText, 0, result, iv.length, cipherText.length);
        return result;
    }

    // RSA Public Key
    public PublicKey getPublicKey() {
        return rsaKeyPair.getPublic();
    }

    // RSA Decrypt
    public String decryptRSA(byte[] cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, rsaKeyPair.getPrivate());
        byte[] plainBytes = cipher.doFinal(cipherText);
        return new String(plainBytes);
    }

    // RSA Sign
    public byte[] signMessage(String message) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(rsaKeyPair.getPrivate());
        signature.update(message.getBytes());
        return signature.sign();
    }

    private KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4));
        return generator.generateKeyPair();
    }
}

class Bob {
    private KeyPair rsaKeyPair;

    public Bob() throws Exception {
        rsaKeyPair = generateRSAKeyPair();
    }

    // AES Decrypt
    public String decryptAES(byte[] cipherMessage, byte[] keyBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        byte[] iv = new byte[12];
        System.arraycopy(cipherMessage, 0, iv, 0, 12);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);
        byte[] plainText = cipher.doFinal(cipherMessage, 12, cipherMessage.length - 12);
        return new String(plainText);
    }

    // RSA Encrypt
    public byte[] encryptRSA(String message, PublicKey receiverPublicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, receiverPublicKey);
        return cipher.doFinal(message.getBytes());
    }

    // Verify Signature
    public boolean verifySignature(String message, byte[] signatureBytes, PublicKey senderPublicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(senderPublicKey);
        signature.update(message.getBytes());
        return signature.verify(signatureBytes);
    }

    private KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4));
        return generator.generateKeyPair();
    }
}
