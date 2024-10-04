package app.aniMonster.business.logic.encrypt;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import org.bouncycastle.bcpg.ArmoredInputStream;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcePBEDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBEKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Base64;

@Component
public class DbEncryptUtil {

    @Value("${encrypt.secretKey}")
    private String secretKey;
    @Value("${encrypt.algorithm}")
    private String algorithm;
    @Value(("${encrypt.iv}"))
    private String iv;
    @Value("${encrypt.activate}")
    private boolean activate;

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    // Bouncy Castle 보안 프로바이더를 등록하는 부분
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    private String encrypt(String value) throws Exception {

        /**
         * java 전용 암호화
         */
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(128);
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);

//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION); // 패딩 및 ECB 모드


        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedValue = cipher.doFinal(value.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedValue);

    }

    private String decrypt(String encryptedValue) throws Exception {

        /**
         * java 전용 암호화
         */
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);

//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue, "UTF-8");


    }

    public String encryptEncode(String value) {
        if (!activate) {
            return value;
        }
        try {
            return encrypt(value);
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.NULL_POINT, "Error while encrypting", e);
        }
    }

    public String encryptDecode(String value) {
        if (!activate) {
            return value;
        }
        try {
            return decrypt(value);
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.NULL_POINT, "Error while decrypting", e);
        }
    }
}
