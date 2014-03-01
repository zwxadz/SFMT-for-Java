import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JumpTest {
    
    static final String SAMPLE_PARAMS = "params.607.properties";
    static Properties params;
    
    public static void main(String[] args) {
        loadParams();
        JumpPolynomial jumpPoly = new JumpPolynomial(params.getProperty("2^24"));
        SFMT607j r0 = new SFMT607j(1234);
        SFMT607j r1 = r0.copyAndJump(jumpPoly);
        for (int i = 0; i < 20; i++) {
            System.out.printf("%10d ", (long)r1.nextBits() & 0xffffffffL);
            if (i % 5 == 4) {
                System.out.println();
            }
        }
        System.out.println();
        for (int i = 0; i < 0x1000000; i++) {
            r0.nextBits();
        }
        for (int i = 0; i < 20; i++) {
            System.out.printf("%10d ", (long)r0.nextBits() & 0xffffffffL);
            if (i % 5 == 4) {
                System.out.println();
            }
        }
    }
    
    static void loadParams() {
        InputStream in = JumpTest.class.getClassLoader().getResourceAsStream(SAMPLE_PARAMS);
        try {
            params = new Properties();
            params.load(in);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
}
