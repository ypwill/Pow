package PowTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author WYP
 * @date 2020-10-13 20:01
 */
public class Encryption {
    public static String getSha256(final String strText) {
        return encryption(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     */
    public static String getSha512(final String strText) {
        return encryption(strText, "SHA-512");
    }

    /**
     * 传入文本内容，返回 MD5串
     */
    public static String getMd5(String data){
        return encryption(data,"MD5");
    }

    /**
     * 字符串 加密
     */
    private static String encryption(final String strText, final String strType) {

        String result = null;
        if (strText != null && strText.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(strText.getBytes());
                byte[] byteBuffer = messageDigest.digest();
                StringBuilder strHexString = new StringBuilder();
                for (byte aByteBuffer : byteBuffer) {
                    String hex = Integer.toHexString(0xff & aByteBuffer);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                result = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
