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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

        // 7.
    }

    /**
     * 加载配置文件
     *
     * @param location 配置文件路径
     */
    private void loadConfig(String location) {
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
                classNames.add(packName + "." + file.getName().replace("\\.class", "").trim());
            }
        }
    }

    /**
     * 实例化对象
     */
    private void instance() {
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
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    // 看是否有 @Service 注解
                    Service service = clazz.getAnnotation(Service.class);
                    // 看接口的实现类是否自定义名称
                    String beanName = service.value();
                    if (!beanName.equals("")) {
                        ioc.put(beanName, clazz.newInstance());
                        continue;
                    }

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        if (ioc.containsKey(i.getName())) {
                            throw new RuntimeException("接口: " + i.getName() + " 的实现类不止一个");
                        }
                        ioc.put(i.getName(), i.newInstance());
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
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
                String url = "/" + baseUrl + "/" + requestMapping.value().replaceAll("\\/+", "/");
                // 将方法与method关联
                handlerMapping.put(url, method);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Error");
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (handlerMapping.isEmpty()) {
            return;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("\\/+", "/");
        if (!handlerMapping.containsKey(url)) {
            resp.getWriter().write("Error: 404 Not Found");
            return;
        }

        Method method = handlerMapping.get(url);
        // 获取方法的参数列表
        Class<?>[] paramTypes = method.getParameterTypes();
        // 获取请求参数
        Map<String, String[]> params = req.getParameterMap();
        // 保存请求参数
        Object[] values = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramType = paramTypes[i];
            if (paramType == HttpServletRequest.class || paramType == HttpServletResponse.class) {
                values[i] = req;
            } else if (paramType == String.class) {
                // 遍历请求参数列表
                for (Map.Entry<String, String[]> entry : params.entrySet()) {
                    String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                    values[i] = value;
                }
            } else {
                System.out.println("未处理的参数类型");
            }
        }

        // 获取当前方法的实例名称
        String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
        try {
            // 利用反射执行该方法
            method.invoke(ioc.get(beanName), values);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
