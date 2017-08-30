package it.dax.klefkey.offline.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


// TODO da verificare le firme e il codice generale
// TODO da decidere firma di encypt e decrypt
// TODO vedere se UTF-8 va bene


public class CryptographyUtils{

    private final Cipher cipher;
    private final KeyFactory keyFactory;
    private byte[] keyBytes;

    public CryptographyUtils() throws NoSuchPaddingException, NoSuchAlgorithmException{
        cipher = Cipher.getInstance(Constants.RSA_ALGORITHM);
        keyFactory = KeyFactory.getInstance(Constants.RSA_ALGORITHM);
    }

    public CryptographyUtils(String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException{
        this.cipher = Cipher.getInstance(algorithm);
        this.keyFactory = KeyFactory.getInstance(algorithm);
    }

    public PrivateKey getPrivateKey(String file) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException{
        keyBytes = Files.readAllBytes(new File(Constants.PRIVATE_KEY_FILE_NAME).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(spec);
    }

    public PublicKey getPublicKey(String file) throws IOException, InvalidKeySpecException{
        keyBytes = Files.readAllBytes(new File(Constants.PUBLIC_KEY_FILE_NAME).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(spec);
    }

    public void generateKeys(int length) throws NoSuchAlgorithmException, IOException{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Constants.RSA_ALGORITHM);
        keyGen.initialize(length);

        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        ReaderWriterUtils.writeObjectFile("", Constants.PRIVATE_KEY_FILE_NAME, privateKey.getEncoded());
        ReaderWriterUtils.writeObjectFile("", Constants.PUBLIC_KEY_FILE_NAME, publicKey.getEncoded());
    }

    public String encrypt(String text, String file) throws IOException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException{
        cipher.init(Cipher.DECRYPT_MODE, getPublicKey(file));
        keyBytes = cipher.doFinal(text.getBytes());
        return new String(keyBytes, "UTF-8");
    }

    // TODO effettuare decrypt













}