package data;

import lombok.Data;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Properties;

/**
 * Class which helps with encrypting decrypting values
 */
@Data
public class EncryptorDecryptor {

    private IvParameterSpec ivSpec;
    private SecretKeyFactory factory;
    private Cipher cipher;

    public EncryptorDecryptor() {
        byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.ivSpec = new IvParameterSpec(IV);
        try {
            this.factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypts the given value
     *
     * @param valueToEncrypt value to encrypt
     * @return returns an encrypted value
     */
    public String encrypt(String valueToEncrypt) {
        try {
            KeySpec spec = new PBEKeySpec("abcjs1345123".toCharArray(), "bdsfdsasd235423".getBytes(), 65536, 256);
            SecretKey tmp = this.factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            this.cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(valueToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Decrypts the giving value
     *
     * @param valueToDecrypt value to decrypt
     * @return the decrypted value
     */
    public String decrypt(String valueToDecrypt) {
        try {
            KeySpec spec = new PBEKeySpec("abcjs1345123".toCharArray(), "bdsfdsasd235423".getBytes(), 65536, 256);
            SecretKey tmp = this.factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            this.cipher.init(Cipher.DECRYPT_MODE, secretKey, this.ivSpec);
            return new String(this.cipher.doFinal(Base64.getDecoder().decode(valueToDecrypt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
