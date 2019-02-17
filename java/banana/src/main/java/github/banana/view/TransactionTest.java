package github.banana.view;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring 事务的传播行为
 *
 * <pre><code>
 * |--------------------------------------------------------------------------------------------------------------------
 * | 事务传播行为类型                    | 说明
 * |-----------------------------------|--------------------------------------------------------------------------------
 * | {@link Propagation#REQUIRED}      | 当前没有事务就新建一个事务, 当前存在事务则加入到这个事务中, 默认设置
 * |-----------------------------------|--------------------------------------------------------------------------------
 * | {@link Propagation#SUPPORTS}      | 支持当前事务, 如果当前没有事务则以非事务方式执行
 * |-----------------------------------|--------------------------------------------------------------------------------
 * | {@link Propagation#MANDATORY}     | 使用当前事务, 如果当前没有事务则抛出异常
 * |-----------------------------------|--------------------------------------------------------------------------------
 * | {@link Propagation#REQUIRES_NEW}  | 新建事务, 如果当前存在事务则挂起当前事务, 内存回滚互相间无依赖
 * |-----------------------------------|--------------------------------------------------------------------------------
 * | {@link Propagation#NOT_SUPPORTED} | 以非事务方式执行, 如果当前存在事务就把当前事务挂起
 * |-----------------------------------|--------------------------------------------------------------------------------
 * | {@link Propagation#NEVER}         | 以非事务方式执行, 当前存在事务则抛异常
 * |-----------------------------------|--------------------------------------------------------------------------------
 * | {@link Propagation#NESTED}        | 当前存在事务则在嵌套事务内执行; 当前没有事务执行则与{@link Propagation#REQUIRED} 类似
 * |--------------------------------------------------------------------------------------------------------------------
 * <code/><pre/>
 *
 * 有一个经典的问题, 比如下方, 注意下方代码是不可执行的, 只是示例
 * <pre><code>
 * interface FooService {
 *     void demo();
 *
 *     void hello();
 * }
 *
 * class FooServiceImpl implements FooService {
 *
 *     @Override
 *     @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
 *     public void demo() {
 *
 *     }
 *
 *     @Override
 *     public void hello() {
 *         demo();
 *     }
 * }
 * <code/><pre/>
 * 问题是hello中的demo能否使用事务?
 * 答案是不能使用事务, 上次一个同事问我, 我还解释错误, Spring不能这么编程
 * 是因为这个同事非要把声明事务写在 @Service 层中, 类似 hello 这样的调用, 我当时也好奇, 觉得是可以的呀
 * 后来看了一些Spring相关的东西, 原来Spring的事务是用AOP代理的, 事务只存在于代理类中, 而不是this类中, 这点不可混淆, 否则很多编程错误
 * 都是这样发生的
 *
 * 解决方法很简单, 至少有4中
 * 1. 通过aop获取当前代理类来执行, 这个比较简单
 * <pre><code>
 * FooServiceImpl impl = (FooServiceImpl) AopContext.currentProxy();
 * impl.demo();
 * <code/><pre/>
 *
 * 2. 获取ioc容器中的当前Bean
 * 这个比较麻烦, 要重修ApplicationContext
 * 示例如
 * <pre><code>
 * public class ApplicationContextUtil implements ApplicationContextAware {
 *
 *     private static ApplicationContext context;
 *
 *     @Override
 *     public void setApplicationContext(ApplicationContext ctx) throws BeansException {
 *         context = ctx;
 *     }
 *
 *     public static <T> T getBean(Class<T> clazz) {
 *         return context.getBean(clazz);
 *     }
 * }
 *
 * FooServiceImpl impl = ApplicationContextUtil.getBean(FooServiceImpl.class);
 * impl.demo();
 * <code/><pre/>
 *
 * 3. 自动注入当前类的实例到容器中, 推荐
 * <pre><code>
 * class FooServiceImpl implements FooService {
 *
 *     @Autowired
 *     private FooServiceImpl fooService;
 *
 *     @Override
 *     @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
 *     public void demo() {
 *
 *     }
 *
 *     @Override
 *     public void hello() {
 *         fooService.demo();
 *     }
 * }
 * <code/><pre/>
 *
 * 4. 显示使用声明式事务
 * <pre><code>
 * @Override
 * @Transactional
 * public void hello() {
 *     demo();
 * }
 * <code/><pre/>
 *
 * 总体上看, 不管使用哪种形式, 一定是使用被 Spring 代理后的类, 而不是原始类
 * 如果必须使用, 那么可以自动注入自身类来解决(第三种), 否则就应该使用声明事务(第四种)
 * 第一种和第二种比较麻烦, 特殊情况也是可以使用的, 相对也不是很复杂, 使用哪种形式其实没有太大的区别, 重要的是理解框架对事务的处理方式
 *
 * 事务的基本要素
 * ACID
 * 原子性
 * 一致性
 * 隔离性
 * 持久性
 *
 * 事务的隔离级别
 *
 * <pre><code>
 * |---------------------------------------------------------
 * | 隔离级别                            | 脏读 | 重复读 | 幻读
 * |---------------------------------------------------------
 * | {@link Isolation#READ_UNCOMMITTED} | ×    | ×     | ×
 * |---------------------------------------------------------
 * | {@link Isolation#READ_COMMITTED}   | √    | ×     | ×
 * |---------------------------------------------------------
 * | {@link Isolation#REPEATABLE_READ}  | √    | √     | ×
 * |---------------------------------------------------------
 * | {@link Isolation#SERIALIZABLE}     | √    | √     | √
 * ---------------------------------------------------------
 * <code/><pre/>
 *
 * 我们常用的 MySQL 使用的是第三种默认隔离级别, 可重复读, 但依然未解决幻读的问题, 幻读就是多个事务交叉运行时
 * 事务期间保证同一个事务内读取到的数据是一致的, 但是事务更新成功后, 重新读取到的数据可能不一致
 *
 * 数据库解决并发的方式是 MVCC 多版本并发控制器
 *
 * 不可重复读和幻读容易混淆
 * 不可重复读侧重于修改, 幻读侧重于新增和删除
 * 解决不可重复读锁住满足条件的行即可
 * 解决幻读需要锁表, 不允许其访问, 也就是串行化的方案
 */
public class TransactionTest {

    public static void main(String[] args) {

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void test() {

    }
}

interface FooService {
    void demo();

    void hello();
}

class FooServiceImpl implements FooService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void demo() {

    }

    @Override
    @Transactional
    public void hello() {
        demo();
    }
}
