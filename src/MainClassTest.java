import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void testGetLocalNumber(){
        int expected = 16;
        Assert.assertTrue("getLocalNumber is not equal " + expected + "!",
                getLocalNumber() == expected);
    }

}
