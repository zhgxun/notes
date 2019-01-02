package com.github.zhgxun.agent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trans implements ClassFileTransformer {

    // 在指定方法执行前后添加时间记录器, 需要以字符串的形式注入代码行
    private final static String prefix = "\nlong startTime = System.currentTimeMillis();\n";
    private final static String postfix = "\nlong endTime = System.currentTimeMillis();\n";

    // 待处理白名单
    // 键为类名, 值为方法名
    private final static Map<String, List<String>> methodMap = new HashMap<>();

    public Trans() {
        add("com.github.zhgxun.mock.CostTest.one");
        add("com.github.zhgxun.mock.CostTest.two");
    }

    private void add(String name) {
        String className = name.substring(0, name.lastIndexOf("."));
        String methodName = name.substring(name.lastIndexOf(".") + 1);
        List<String> list = methodMap.getOrDefault(className, new ArrayList<>());
        if (list.size() == 0) {
            methodMap.put(className, list);
        }
        list.add(methodName);
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 替换className名称
        className = className.replace("/", ".");
        // 需要被监控的白名单列表, 非白名单不处理, 返回null即可, 即是不破坏原本的正常类加载
        if (!methodMap.containsKey(className)) {
            return null;
        }

        // 使用javassist技术修改字节码
        CtClass ctClass = null;
        try {
            // 使用全称, 用于取得字节码类<使用javassist>
            ctClass = ClassPool.getDefault().get(className);
            for (String methodName : methodMap.get(className)) {
                // 打印方法消耗的时间
                String outputStr = "\nSystem.out.println(\"this method " + methodName + " cost:\" +(endTime - startTime) +\"ms.\");";
                // 得到类的方法实例
                CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
                // 新定义一个方法, 比如 one$old, 避免重复
                String newMethodName = methodName + "$old";
                // 将原来的方法名字修改为新定义的方法名
                ctMethod.setName(newMethodName);
                // 创建新的方法, 复制原来的方法, 名字为原来的名字
                CtMethod newCtMethod = CtNewMethod.copy(ctMethod, methodName, ctClass, null);
                // 构建新的方法体
                StringBuilder body = new StringBuilder();
                body.append("{");
                body.append(prefix);
                // 调用原有代码, 类似于method();($$)表示所有的参数
                body.append(newMethodName);
                body.append("($$);\n");
                body.append(postfix);
                body.append(outputStr);
                body.append("}");

                // 替换新方法
                newCtMethod.setBody(body.toString());

                // 增加新方法
                ctClass.addMethod(newCtMethod);
            }
            return ctClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
