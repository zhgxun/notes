package github.banana.demo;

/**
 * 1. 首先加载SinTestV2类, 因此会执行SinTestV2类中的static块
 * 2. 接着执行new MyClass()，而MyClass类还没有被加载，因此需要加载MyClass类
 * 3. 在加载MyClass类的时候，发现MyClass类继承自SinTestV2类，但是由于SinTestV2类已经被加载了，所以只需要加载MyClass类，那么就会执行MyClass类的中的static块
 * 4. 在加载完之后，就通过构造器来生成对象
 * 5. 在生成对象的时候，必须先初始化父类的成员变量，因此会执行SinTestV2中的Person person = new Person()
 * 6. Person类还没有被加载过，因此会先加载Person类并执行Person类中的static块, 并初始化构造器
 * 7. 然后初始化SinTestV2构造器
 * 8. 初始化子类MyClass构造器之前，需要先初始化属性构造器
 */
public class SinTestV2 {

    PersonV2 person = new PersonV2("SinTestV2 -- 4");

    static {
        System.out.println("test static -- 1");
    }

    public SinTestV2() {
        System.out.println("test constructor -- 5");
    }

    public static void main(String[] args) {
        new MyClass();
    }
}

class PersonV2 {
    static {
        System.out.println("person static -- 3");
    }

    public PersonV2(String str) {
        System.out.println("person " + str);
    }
}


class MyClass extends SinTestV2 {
    PersonV2 person = new PersonV2("MyClass -- 6");

    static {
        System.out.println("myclass static -- 2");
    }

    public MyClass() {
        System.out.println("myclass constructor -- 7");
    }
}
