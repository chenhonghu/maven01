package myjunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: maven01
 * @description:
 * @author: hgdd
 * @create: 2021-04-01 17:34
 */
public class MyJunitRunner {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class cls=Class.forName("MyCalculatorTest");
        Method []ms=cls.getDeclaredMethods();
        List<Method> testMethods=new ArrayList<Method>();
        Method beforeMethod=null;
        Method afterMethod=null;
        Method afterClassMethod=null;
        Method beforeClassMethod=null;
        for(Method m:ms){
            if(m.isAnnotationPresent(MyTest.class)){
                testMethods.add(m);
            }
            if(m.isAnnotationPresent(MyBefore.class)){
                beforeMethod=m;
            }
            if(m.isAnnotationPresent(MyAfter.class)){
                afterMethod=m;
            }
            if(m.isAnnotationPresent(MyAfterClass.class)){
                afterClassMethod=m;
            }
            if(m.isAnnotationPresent(MyBeforeClass.class)){
                beforeClassMethod=m;
            }
        }
        if(testMethods==null||testMethods.size()<=0){
            throw  new RuntimeException("没有测试的方法");
        }
        Object o=cls.newInstance();
        beforeClassMethod.invoke(o);
        for(Method m:testMethods){
            if(beforeMethod!=null){
                beforeMethod.invoke(o);
            }
            m.invoke(o);
            if(afterMethod!=null){
                afterMethod.invoke(o);
            }

        }
        afterClassMethod.invoke(o);
    }
}
