import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void testGetClassString(){
        Assert.assertTrue ("Отсутствует слово \"Hello\" в методе getClassString()",
                getClassString() == "Hello, world"|| getClassString() == "hello, world");
        }
    }
