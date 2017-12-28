package github.banana;

import java.nio.charset.StandardCharsets;

/**
 * 将响应内容格式化输出
 * 
 * @author zhgxun
 *
 */
public class Response {
    private int code;
    private byte[] data;
    
    public Response(int code, byte[] data) {
        this.code = code;
        this.data = data;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(2014);
        // 响应码
        stringBuilder.append(this.code).append("\n");
        // 把内容转化为字符串
        String string = new String(data, StandardCharsets.UTF_8);
        // 如果长度超过1024则拼接为...展示
        if (string.length() > 1024) {
            stringBuilder.append(string.substring(0,  1024)).append("\n...");
        } else {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
