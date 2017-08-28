package it.dax.klefkey.test.control;

import it.dax.klefkey.test.utils.CryptographyUtils;

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