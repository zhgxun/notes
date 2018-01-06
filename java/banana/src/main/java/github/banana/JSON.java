package github.banana;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * 处理json
 * 
 * 静态语言的解析都需要知道明确的json数据类型和接口, 一般不会盲目解析
 * 
 * @see http://json.org/json-zh.html 网站可以看到各语言支持的json解析库
 * 
 * @author zhgxun
 *
 */
public class JSON {

    public static void main(String[] args) {
        read();
    }

    /**
     * 写入一个json对象文件
     */
    private static void write() {
        // 创建一个json对象
        JsonObject object = new JsonObject();
        // 为json对象添加属性
        // {"cat":"it"}
        object.addProperty("cat", "it");

        // 创建json数组
        JsonArray languages = new JsonArray();
        JsonObject language = new JsonObject();
        // 添加一个数组
        language.addProperty("id", 1);
        language.addProperty("ide", "Eclipse");
        language.addProperty("name", "java");
        // 将创建的数组添加到json对象languages中
        languages.add(language);

        language = new JsonObject();
        language.addProperty("id", 2);
        language.addProperty("ide", "XCode");
        language.addProperty("name", "Swift");
        languages.add(language);

        language = new JsonObject();
        language.addProperty("id", 3);
        language.addProperty("ide", "Visual Studio");
        language.addProperty("name", "C#");
        languages.add(language);

        // 将json数组添加到json对象中
        object.add("languages", languages);
        object.addProperty("pop", true);

        // 将json对账格式化为字符串
        String jsonStr = object.toString();
        System.out.println(jsonStr);
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("data.json")));
            pw.print(jsonStr);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读json对象文件
     */
    private static void read() {
        // 创建json解析器
        JsonParser parser = new JsonParser();
        // 使用解析器解析json数据，返回值是JsonElement，强制转化为其子类JsonObject类型
        JsonObject object;
        try {
            object = (JsonObject) parser.parse(new FileReader("data.json"));
            // 使用JsonObject的get(String
            // memeberName)方法返回JsonElement，再使用JsonElement的getAsXXX方法得到真实类型
            System.out.println("cat = " + object.get("cat").getAsString());

            // 遍历JSON数组
            JsonArray languages = object.getAsJsonArray("languages");
            for (JsonElement jsonElement : languages) {
                JsonObject language = jsonElement.getAsJsonObject();
                System.out.println("id = " + language.get("id").getAsInt() + ",ide = "
                        + language.get("ide").getAsString() + ",name = " + language.get("name").getAsString());
            }

            System.out.println("pop = " + object.get("pop").getAsString());
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
