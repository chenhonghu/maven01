package reflection;

import reflection.Showable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: maven01
 * @description: sd
 * @author: hgdd
 * @create: 2021-03-25 18:54
 */
//在程序运行中，有人给了一个类，请动态的了解这个类，且创建这个类的对象
public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("请输入类路径：");
            String path=sc.nextLine();
            System.out.println("待加载的类为："+path);
            Class c=Class.forName(path);
            String name =c.getName();
            System.out.println(name);

            Field[]fs=c.getDeclaredFields();//declared 自己声明的
            if(fs!=null&&fs.length>0){
                for(Field f:fs){
                    String modifier="";
                    switch (f.getModifiers()){//getModifier 返回int类型的值表示该字段的修饰符
                        case 1:
                            modifier="public";
                            break;
                        case 2:
                            modifier="private";
                            break;

                    }
                    System.out.println(modifier+"\t"+f.getName());
                }
            }
            Method []ms=c.getDeclaredMethods();
            //Method []ms=c.getMethods();
            if(ms!=null&&ms.length>0){
                for(Method m:ms){
                    System.out.println(m.getModifiers()+"\t"+m.getReturnType().toString()+"\t"+m.getName());
                }//getReturnType() 该方法的返回类型 例如 void strin
            }
            Constructor[]cs=c.getConstructors();//getConstructors 获取该类中的所有构造方法 实用于只有有参构造方法时
            if(cs!=null&&cs.length>0){
                for(Constructor m:cs){
                    System.out.println(m.getModifiers()+"\t"+m.getName());
                }
            }
            //利用反射完成实例化操作
            Object o=c.newInstance();//无构造方法
            //这个是已知接口。。。instanceof
            if(o instanceof Showable){
                Showable p=(Showable) o;
                p.show();
            }
            //利用反射调用某个方法 适合j2ee中规范化方法调用场景 ：setXXX getXXX（）
            System.out.println("+++++++++++++");
            if(ms!=null&&ms.length>0){
                for(Method m:ms){
                    if(m.getName().startsWith("sh")){//startWith boolean类型
                        //调用此方法 show（） 它有两个参数：第一个是实列，第二个实参数组

                        m.invoke(o);

                    }
                }
            }
            Map<String,String> pmap=new HashMap<String,String>();
            pmap.put("name","zhangsan");
            pmap.put("age",30+"");
            Object oo=setValues(pmap,c);
            System.out.println(oo.toString());



        }
    }
    //反射功能模块：将Map中保存的 属性值存到 cls对应的对像中去。注意已知cls满足j2ee的javabean规范（setXXX getXXX）
    public static Object setValues(Map<String,String> map,Class cls) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object o=null;//o:"Person{name='张',age='0'}"
        o=cls.newInstance();
        Method[]ms=cls.getDeclaredMethods();
        if(ms!=null&&ms.length>0){
            for(Method m:ms){
                //只有setXXX才激活
                if(m.getName().startsWith("set")){//stName()-->name
                    String mName=m.getName();//mName:"setAge"
                    String fName=mName.substring(3).toLowerCase();
                    String value=map.get(fName);
                    if("Integer".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())||"Int".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())){
                        m.invoke(o,Integer.parseInt(value));//调用set方法，设置值
                    }else{
                        m.invoke(o,value);//调用set方法，设置值
                    }
                }
            }
        }
        return o;
    }

}
