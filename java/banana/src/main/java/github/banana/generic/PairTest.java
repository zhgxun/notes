package github.banana.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型
 * 编译期实现
 * <p>
 * 静态方法不能引用泛型类型 <T>, 必须定义其他类型 <K> 来实现“泛型”,
 * 此处这里编写 T, 其实已经不再是传入的 T 类型, 而是新的类型, 避免混淆, 一般使用新的泛型
 * 编译器把类型 T 视为 Object
 *
 * @see Pair#create(Object, Object)
 * <p>
 * Java的泛型（Generic）是采用擦拭法（Type Erasure）实现的。
 * <p>
 * 擦拭法的局限:
 * <T>不能是基本类型, 例如int
 * Object字段无法持有基本类型
 * 无法取得带泛型的Class, 例如 Pair<String>.class
 * 无法判断带泛型的Class, 例如 x instance Pair<String>
 * 不能实例化 T 类型, 例如 new T()
 * 泛型方法用防止重复定义重复方法, 例如 public boolean equals(T object) {return true}
 * 子类可以获取父类的泛型 T
 * <p>
 * 使用 <? extends Number> 是方法接收所有泛型类型为 Number 或 Number 的子类
 * 允许调用 get 方法获取 Number 类型的引用, 不允许调用 set 方法传入 Number 类型的引用, 唯一例外可以传入 setX(null)
 * <p>
 * 使用 <? super Integer> 使方法接收所有泛型类型为 Integer 或 Integer 超类的类
 * 可以安全调用 set 方法 设置 Integer 类型的引用, 但是无法获取 get 方法后的引用, 因为获取到的类型是 Object 类型
 * <p>
 * 综合
 * <? extends T>允许调用方法获取T的引用
 * <? super T>允许调用方法传入T的引用
 * <p>
 * 无限定通配符<?>
 * 不允许调用 set 方法, 只能调用 get 方法获取 Object 的引用
 * 可以引入泛型参数 T 来消除 ? 通配符
 * 无限定通配符很少使用, 通常都是知道需要获取的类型的, 比如Class<?> class = ...
 * 部分反射API是泛型:
 * Class<T>
 * Constructor<T>
 * 可以声明带泛型的数组，但不能直接创建带泛型的数组，必须强制转型
 * 可以通过Array.newInstance(Class<T>, int)创建T[]数组，需要强制转型
 */
public class PairTest {

    public static void main(String[] args) {
        Pair<String> pair = new Pair<>();
        pair.setFirst("one");
        pair.setLast("two");
        System.out.println(pair);

        Pair<Long> pair1 = Pair.create(1L, 2L);
        System.out.println(pair1);

        // 泛型无法获取Class类型
        Pair<Integer> pair2 = Pair.create(10, 20);
        Class clazz1 = pair.getClass();
        Class clazz2 = pair2.getClass();
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == Pair.class);

        // 泛型继承
        Class clazz3 = IntPair.class;
        System.out.println(clazz3.getName());
        Type type = clazz3.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            // 得到实际参数类型, 既然是继承自Integer, 类型肯定是Integer
            // java.lang.Integer
            Type[] types = parameterizedType.getActualTypeArguments();
            System.out.println(types[0].getTypeName());
            System.out.println(types[0].getClass());
        }

        // 可传入任何 Number 的子类, 但是无法设置值
        print(new Pair<>(12, 25));
        print(new Pair<>(200L, 300L));
        print(new Pair<>(2.3, 4.5));

        Class<String> clazz4 = String.class;
        // 该种实例化类的方式9以后版本标记为废弃, 需要借助反射来实现类
        // String s = clazz4.newInstance();
        // 后续支持的实例化方式
        try {
            Constructor<String> constructor = clazz4.getConstructor(String.class);
            String s = constructor.newInstance("Hello");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 泛型类型是 Number 的子类或实现类
    private static void print(Pair<? extends Number> p) {
        System.out.println(p.getFirst());
        System.out.println(p.getLast());
    }
}
