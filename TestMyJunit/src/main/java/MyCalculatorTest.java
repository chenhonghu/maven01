
import myjunit.*;

/**
 * @program: maven01
 * @description:
 * @author: hgdd
 * @create: 2021-03-31 19:58
 */
public class MyCalculatorTest {
   // private Calculator cal;
    @MyBeforeClass
    public static void bs(){
        System.out.println("beforeclass");
    }
    @MyBefore
    public void setUp(){
        System.out.println("before");
       // cal=new Calculator();
    }
    @MyAfter
    public void tearDown(){
        System.out.println("after");
    }
    @MyAfterClass
    public static void ac(){
        System.out.println("afterclass");
    }
    @MyTest
    public void add(){
        System.out.println("add测试");

    }
    @MyTest
    public void sub(){
        System.out.println("sub测试");

    }
}
