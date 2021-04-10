import reflection.Showable;

/**
 * @program: maven01
 * @description:
 * @author: hgdd
 * @create: 2021-03-29 20:08
 */
public class Person implements Showable {
    private String name;
    private int age;
    public Person(){
        System.out.println("无参构造方法");
    }
    public Person(String name){
        this.name=name;
        System.out.println("有参构造方法");

    }
    @Override
    public void show() {
        System.out.println("show()");
    }

    @Override
    public String toString() {
        return "Person{"+
                "name'"+name+'\''+",age="+age+'}';
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }

}
