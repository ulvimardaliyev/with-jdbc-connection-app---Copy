package db.password.encoding;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encode {

    public static String encodePassword(String password) throws NoSuchAlgorithmException {
        String returnMe;
        byte[] keepWordInBytes = password.getBytes();


        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        //messageDigest.update(salt);
        byte[] make = messageDigest.digest(keepWordInBytes);


        BigInteger bigInteger = new BigInteger(1, make);
        returnMe = bigInteger.toString(16);

        return returnMe;
    }

}
