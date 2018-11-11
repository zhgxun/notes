package github.banana.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http编程
 * 
 * @author zhgxun
 *
 */
public class HttpClient {

    public static void main(String[] args) {
        // get
        System.out.println("start get request...");
        Response response = get("https://www.douban.com");
        System.out.println(response);

        // post
        System.out.println("start post send...");
        Map<String, String> map = new HashMap<>();
        map.put("form_email", "zhgxun");
        map.put("form_password", "cqzytsy%");

        // 初始化一个列表
        List<String> list = new ArrayList<>(map.size());
        for (String string : map.keySet()) {
            try {
                list.add(string + "=" + URLEncoder.encode(map.get(string), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // 将参数拼接成地址格式
        String query = String.join("&", list);

        try {
            Response resp = post("https://www.douban.com/accounts/login", "application/x-www-form-urlencoded", query);
            System.out.println(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get方式获取数据
     * 
     * @param theUrl
     * @return
     */
    private static Response get(String theUrl) {
        HttpURLConnection httpURLConnection = null;
        try {
            // 将请求地址转化为一个URL对象
            URL url = new URL(theUrl);
            // 将URL对象强制转化为HttpURLConnection连接
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 初始化一个输出流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 从输入流中读取内容
            try (InputStream inputStream = httpURLConnection.getInputStream()) {
                // 以byte方式一次读取1024字节
                byte[] bytes = new byte[1024];
                int n;
                while ((n = inputStream.read(bytes)) >= 0) {
                    // 将读取到的内容写入到输出存储中
                    byteArrayOutputStream.write(bytes, 0, n);
                }
                // 将读取到的内容响应输出
                return new Response(httpURLConnection.getResponseCode(), byteArrayOutputStream.toByteArray());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }
        return null;
    }

    /**
     * post方式发送数据
     * 
     * @param theUrl
     * @param type
     * @param data
     * @return
     * @throws IOException
     */
    private static Response post(String theUrl, String type, String data) throws IOException {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(theUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            // 设置请求方式为POST
            httpURLConnection.setRequestMethod("POST");
            // 设置开始输入请求内容
            httpURLConnection.setDoOutput(true);
            // 将请求体转化为字节数组
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            // 设置内容类型
            httpURLConnection.setRequestProperty("Content-Type", type);
            // 设置内容长度为字符串
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bytes.length));

            // 获取连接内容输出对象
            OutputStream outputStream = httpURLConnection.getOutputStream();
            // 将内容写入到输出对象中
            outputStream.write(bytes);

            // 保存响应存储对象
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 从请求对象中获取输入对象
            InputStream inputStream = httpURLConnection.getInputStream();
            // 一次性读取1024个字节
            byte[] b = new byte[1024];
            int n;
            while ((n = inputStream.read(b)) >= 0) {
                // 将内容存储到输出对象中
                byteArrayOutputStream.write(b, 0, n);
            }

            // 返回响应的内容
            return new Response(httpURLConnection.getResponseCode(), byteArrayOutputStream.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }
        return null;
    }
}
