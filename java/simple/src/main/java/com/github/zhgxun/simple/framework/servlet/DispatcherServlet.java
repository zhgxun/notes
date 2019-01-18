package com.github.zhgxun.simple.framework.servlet;

import com.github.zhgxun.simple.framework.annotation.Autowired;
import com.github.zhgxun.simple.framework.annotation.Controller;
import com.github.zhgxun.simple.framework.annotation.RequestMapping;
import com.github.zhgxun.simple.framework.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 简单的能处理一个参数的Spring MVC 框架
 * <p>
 * 仅供学习用, 毕竟现在的Java程序都是打包成Java应用, 即是可执行jar包来运行, 新的应用很少在使用war包的方式来运行了
 * 尤其是SpringBoot开始, 内嵌了容器
 */
public class DispatcherServlet extends HttpServlet {

    private static final String LOCATION = "contextConfigLocation";
    private Properties properties = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private Map<String, Method> handlerMapping = new HashMap<>();

    public DispatcherServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("初始化开始");
        // 1. 加载配置文件
        loadConfig(config.getInitParameter(LOCATION));

        // 2. 扫描所有相关的类
        scanner("com.github.zhgxun.simple.hello");

        // 3. 初始化所有相关类的实例, 并保存到ioc容器中
        instance();

        // 4. 依赖注入DI
        autowired();

        // 5. 构造handlerMapping
        initHandleMapping();

        // 6. 等待请求doGet, doPost 方法
    }

    /**
     * 加载配置文件
     *
     * @param location 配置文件路径
     */
    private void loadConfig(String location) {
        // application.properties
        System.out.println("加载配置文件: " + location);
        InputStream stream = null;
        try {
            stream = this.getClass().getClassLoader().getResourceAsStream(location);
            properties.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描包下面的所有文件
     *
     * @param packName 包名 com.github.zhgxun.simple
     */
    private void scanner(String packName) {
        // com.github.zhgxun.simple.hello
        // com.github.zhgxun.simple.hello.controller
        System.out.println("扫描包: " + packName);
        URL url = this.getClass().getClassLoader().getResource("/" + packName.replaceAll("\\.", "/"));
        if (url == null) {
            throw new RuntimeException("包名称: " + packName + "不能为空");
        }
        File dir = new File(url.getFile());
        File[] files = dir.listFiles();
        if (files == null) {
            throw new RuntimeException("包: " + packName + " 所在的目录不存在类文件");
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanner(packName + "." + file.getName());
            } else {
                // class类文件名称不保存扩展名, 但保存绝对路径
                // com.github.zhgxun.simple.hello.controller.HelloController
                // com.github.zhgxun.simple.hello.service.impl.HelloServiceImpl
                String className = packName + "." + file.getName().replace(".class", "").trim();
                classNames.add(className);
                System.out.println("扫描到的className: " + className);
            }
        }
    }

    /**
     * 实例化对象
     */
    private void instance() {
        System.out.println("开始实例化对象");
        if (classNames.size() == 0) {
            return;
        }

        try {
            for (String className : classNames) {
                // 通过类名获取类的实例
                Class<?> clazz = Class.forName(className);
                // 看是否有 @Controller 注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, clazz.newInstance());
                    System.out.println("实例化@Controller beanName: " + beanName);
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    // 看是否有 @Service 注解
                    Service service = clazz.getAnnotation(Service.class);
                    // 看接口的实现类是否自定义名称
                    // helloController
                    String beanName = service.value();
                    if (!beanName.equals("")) {
                        ioc.put(beanName, clazz.newInstance());
                        System.out.println("实例化@Service beanName: " + beanName);
                        continue;
                    }

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        if (ioc.containsKey(i.getName())) {
                            throw new RuntimeException("接口: " + i.getName() + " 的实现类不止一个");
                        }
                        ioc.put(i.getName(), clazz.newInstance());
                        // com.github.zhgxun.simple.hello.service.HelloService
                        System.out.println("实例化@Service interface beanName: " + i.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将类名的首字母转化为小写
     *
     * @param className 类名
     * @return 首字母小写的类名
     */
    private String lowerFirstCase(String className) {
        char[] chars = className.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 对属性进行依赖注入对应的实例
     */
    private void autowired() {
        System.out.println("自动注入类的@Autowired注解实例...");
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 获取对象实例中的所有属性, 包括私有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                // 只注入 @Autowired 注解的属性
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                Autowired autowired = field.getAnnotation(Autowired.class);
                String beanName = autowired.value().trim();
                // 无默认值则获取一个类型对应的名称
                if (beanName.equals("")) {
                    beanName = field.getType().getName();
                }
                // 可能为私有属性, 需要控制反射权限
                field.setAccessible(true);
                try {
                    // 从ioc容易中获取对应的对象实例赋值到对象的属性中, 即是依赖注入响应的实例
                    field.set(entry.getValue(), ioc.get(beanName));
                    // com.github.zhgxun.simple.hello.service.HelloService
                    System.out.println("注入属性 beanName: " + beanName);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("实例: " + entry.getValue().getClass().getName() + " 的属性: " + beanName + " 无可注入的实现类");
                }
            }
        }
    }

    /**
     * 初始化所有方法的映射
     */
    private void initHandleMapping() {
        System.out.println("初始化映射关系...");
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            // handlerMapping 只存在你 @Controller 注解中
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseUrl = "";
            // 是否存在类上的公共路径
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }
            // 获取类上的所有方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                // 只处理方法上存在 @RequestMapping 注解的方法
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                // 将方法与method关联
                // /hello/test
                handlerMapping.put(url, method);
                System.out.println("handlerMapping: " + url);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("开始GET请求");
        try {
            dispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Error");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("开始POST请求");
        try {
            dispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Error");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (handlerMapping.isEmpty()) {
            return;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!handlerMapping.containsKey(url)) {
            resp.getWriter().write("Error: 404 Not Found");
            return;
        }
        // /hello/test
        System.out.println("请求URL: " + url);

        Method method = handlerMapping.get(url);
        // 获取请求参数
        Map<String, String[]> params = req.getParameterMap();
        // 获取当前方法的实例名称
        String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
        // helloController
        System.out.println("获取当前方法的实例名称: " + beanName);
        try {
            // 利用反射执行该方法, 简化操作只传输一个固定变量
            String name = params.getOrDefault("name", new String[]{"param 'name' is need"})[0];
            method.invoke(ioc.get(beanName), req, resp, new String(name.getBytes(StandardCharsets.UTF_8)));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
