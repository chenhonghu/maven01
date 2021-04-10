import org.junit.*;

/**
 * @program: maven01
 * @description:
 * @author: hgdd
 * @create: 2021-03-31 19:58
 */
public class CalculatorTest {
    private Calculator cal;
    @BeforeClass
    public static void bs(){
        System.out.println("beforeclass");
    }
    @Before
    public void setUp(){
        System.out.println("before");
        cal=new Calculator();
    }
    @After
    public void tearDown(){
        System.out.println("after");
    }
    @AfterClass
    public static void ac(){
        System.out.println("afterclass");
    }
    @Test
    public void add(){
        System.out.println("add测试");
        Assert.assertEquals(3,cal.add(1,2));
    }
    @Test
    public void sub(){
        System.out.println("sub测试");
        Assert.assertEquals(1,cal.sub(2,1));
    }
}
