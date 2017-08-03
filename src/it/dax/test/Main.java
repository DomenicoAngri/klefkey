package it.dax.test;

import it.dax.utils.CryptographyUtils;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

public class Main{

    public static void main(String[] args){
        CryptographyUtils cu = null;

        try {
            cu = new CryptographyUtils();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            boolean patt = cu.generateKeys(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrivateKey privateKey = cu.getPrivateKey("keys/pri_gen");
            PublicKey publicKey = cu.getPublicKey("keys/pub_gen");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        String text = "Domenico Angri";

    }

}