package app.aniMonster.business.logic.encrypt;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class DbEncryptUtil {
    @Getter
    @Value("${encrypt.secretKey}")
    private String secretKey;
    @Value("${encrypt.algorithm}")
    private String algorithm;

    private String encrypt(String value) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(128);
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedValue = cipher.doFinal(value.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    private String decrypt(String encryptedValue) throws Exception {
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue, "UTF-8");
    }

    public String encryptEncode(String value) {
        try {
            return encrypt(value);
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.NULL_POINT, "Error while encrypting", e);
        }
    }

    public String encryptDecode(String value) {
        try {
            return decrypt(value);
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.NULL_POINT, "Error while decrypting", e);
        }
    }
}
