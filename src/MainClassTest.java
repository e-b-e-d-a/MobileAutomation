import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void testGetClassNumber(){
        int expected = 45;
        Assert.assertTrue("getClassNumber is less, than " + expected + "!",
                getClassNumber() >= expected);
    }

}
