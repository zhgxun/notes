package github.banana.rpc;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    public static void main(String[] args) {

        ObjectInputStream input = null;
        ObjectOutputStream output = null;

        try {
            // 启动服务端监听端口
            ServerSocket socket = new ServerSocket(8889);
            while (true) {
                // 接收到一个客户端连接请求
                Socket client = socket.accept();
                // 将客户端的连接反序列化为对象
                input = new ObjectInputStream(client.getInputStream());
                // 顺序读入方法名
                String methodName = input.readUTF();
                Class<?>[] paramTypes = (Class<?>[]) input.readObject();
                Object[] params = (Object[]) input.readObject();
                // 找到方法
                Method method = UserServiceImpl.class.getMethod(methodName, paramTypes);
                Object result = method.invoke(UserServiceImpl.class.newInstance(), params);
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);
                output.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
