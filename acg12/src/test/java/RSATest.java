import com.framework.loippi.utils.des.RSAUtils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/2 18:35
 * Description:
 */
public class RSATest {

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAUtils.main(args);
//        test2();
    }
}
