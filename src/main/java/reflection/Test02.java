package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: maven01
 * @description:
 * @author: hgdd
 * @create: 2021-04-01 16:10
 */
public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("请输入类路径");
            String path=sc.nextLine();
            System.out.println("待加载的类为："+path);
            Class c=Class.forName(path);
            String name =c.getName();
            System.out.println(name);

            Field[]fs=c.getDeclaredFields();
            if(fs!=null&&fs.length>0){
                for(Field f:fs){
                    String modifier="";
                    switch (f.getModifiers()){
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
            if(ms!=null&&ms.length>0){
                for(Method m:ms){
                    System.out.println(m.getModifiers()+"\t"+m.getReturnType()+"\t"+m.getName());
                }
            }
            Constructor[]cs=c.getConstructors();
            if(cs!=null&&cs.length>0){
                for(Constructor m:cs){
                    System.out.println(m.getModifiers()+"\t"+m.getName());
                }
            }
            Object o=c.newInstance();
            if(o instanceof Showable){
                Showable p=(Showable) o;
                p.show();
            }
            System.out.println("+++++++");
            if(ms!=null&&ms.length>0){
                for(Method m:ms){
                    if(m.getName().startsWith("sh")){
                        m.invoke(o);
                    }
                }
            }
            Map<String,String> pmap=new HashMap<String, String>();
            pmap.put("name","zhangsan");
            pmap.put("age",30+"");
            Object oo=setValues(pmap,c);
            System.out.println(oo.toString());
        }
    }

        private static Object setValues(Map<String, String> pmap, Class cls) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object o=null;
        o=cls.newInstance();
        Method[]ms=cls.getDeclaredMethods();
        if(ms!=null&&ms.length>0){
            for(Method m:ms){
                if(m.getName().startsWith("set")){
                    String mName=m.getName();
                    String fName=mName.substring(3).toLowerCase();
                    String value= pmap.get(fName);
                    if("Integer".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())||"Int".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())){
                        m.invoke(o,Integer.parseInt(value));
                    }else{
                        m.invoke(o,value);
                    }
                }
            }

        }
            return o;
    }

}
