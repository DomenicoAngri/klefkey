package it.dax.klefkey.test.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class CryptographyUtils{

    private Cipher cipher;

    public CryptographyUtils() throws NoSuchPaddingException, NoSuchAlgorithmException{
        cipher = Cipher.getInstance("RSA");
    }

    public PrivateKey getPrivateKey(String file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
        byte[] keyBytes = Files.readAllBytes(new File(file).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    public PublicKey getPublicKey(String file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
        byte[] keyBytes = Files.readAllBytes(new File(file).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    public boolean generateKeys(int length) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(length);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        File pri = new File("keys/private");
        FileOutputStream fosPri = new FileOutputStream(pri);
        fosPri.write(privateKey.getEncoded());
        fosPri.flush();
        fosPri.close();

        File pub = new File("keys/public");
        FileOutputStream fosPub = new FileOutputStream(pub);
        fosPub.write(publicKey.getEncoded());
        fosPub.flush();
        fosPub.close();

        return true;
    }

    public byte[] encrypt(String text, String file) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        PublicKey publicKey = getPublicKey(file);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(text.getBytes());

    }

    public byte[] decrypt(byte[] text, String file) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        PrivateKey privateKey = getPrivateKey(file);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(text);

    }











}
