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

        // Test
        CryptographyUtils cu = null;

        try {
            cu = new CryptographyUtils();

            boolean patt = cu.generateKeys(512);

            //PrivateKey privateKey = cu.getPrivateKey("keys/pri_gen");
            //PublicKey publicKey = cu.getPublicKey("keys/pub_gen");

            String text = "Domenico Angri";

            byte[] encodedText = cu.encrypt(text, "keys/public");

            byte[] decodedText = cu.decrypt(encodedText, "keys/private");

            if (decodedText.equals(text.getBytes())) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}