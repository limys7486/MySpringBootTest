import my.base.spring.support.PasswordEncoderUtil;
import org.junit.Test;

/**
 * Created by ysj on 2018-03-11.
 */
public class PasswordEncodeTest {

    final String pwd = "qwer1234!";

    @Test
    public void getBcrpr(){
        PasswordEncoderUtil pwdu = new PasswordEncoderUtil();
        System.out.println(pwdu.encode(pwd));
    }
}
