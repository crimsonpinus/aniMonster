package app.aniMonster.business.logic.encrypt;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class DbEncryptUtil {

    @Value("${encrypt.secretKey}")
    private String secretKey;
    @Value("${encrypt.algorithm}")
    private String algorithm;
    @Value(("${encrypt.iv}"))
    private String iv;
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private String encrypt(String value) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(128);
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);

//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION); // 패딩 및 ECB 모드


        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedValue = cipher.doFinal(value.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedValue);


//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
//
//        // IV 생성
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        byte[] iv = new byte[cipher.getBlockSize()];
//        SecureRandom random = new SecureRandom();
//        random.nextBytes(iv); // 난수로 IV 생성
//        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
//        byte[] encryptedValue = cipher.doFinal(value.getBytes("UTF-8"));
//
//        // IV를 암호문 앞에 붙여 저장 (Base64로 인코딩)
//        byte[] encryptedWithIv = new byte[iv.length + encryptedValue.length];
//        System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
//        System.arraycopy(encryptedValue, 0, encryptedWithIv, iv.length, encryptedValue.length);
//
//        return Base64.getEncoder().encodeToString(encryptedWithIv); // Base64 인코딩
    }

    private String decrypt(String encryptedValue) throws Exception {
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);

//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue, "UTF-8");


//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
//
//        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
//
//        // IV와 암호문 분리
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        byte[] iv = new byte[cipher.getBlockSize()];
//        System.arraycopy(decodedValue, 0, iv, 0, iv.length);
//        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//
//        byte[] encryptedBytes = new byte[decodedValue.length - iv.length];
//        System.arraycopy(decodedValue, iv.length, encryptedBytes, 0, encryptedBytes.length);
//
//        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
//        byte[] decryptedValue = cipher.doFinal(encryptedBytes);
//
//        return new String(decryptedValue, "UTF-8");
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
