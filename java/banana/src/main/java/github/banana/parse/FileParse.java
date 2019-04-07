package github.banana.parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class FileParse {

    public static void main(String[] args) throws Exception {
        File w = new File("express-ext.txt");
        FileWriter fw = new FileWriter(w.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File r = new File("express.txt");
        BufferedReader reader = new BufferedReader(new FileReader(r));
        String tempString;
        Map<String, String> map = new HashMap<>();
        while ((tempString = reader.readLine()) != null) {
            if (tempString.length() == 0) {
                continue;
            }
            if (tempString.startsWith("kuaidaoyun-name")) {
                String[] names = tempString.split(":");
                map.put(names[0], names[1].trim());
            }
            if (tempString.startsWith("kuaidaoyun-code")) {
                String[] codes = tempString.split(":");
                bw.write(codes[1].trim().toLowerCase() + ":");
                bw.newLine();
                bw.write("  id: -1");
                bw.newLine();
                bw.write("  biz-code: ");
                bw.newLine();
                bw.write("  biz-name: ");
                bw.newLine();
                bw.write("  biz-simple-name: ");
                bw.newLine();
                bw.write("  biz-order-name: ");
                bw.newLine();
                bw.write("  logic-id: 0");
                bw.newLine();
                bw.write("  order: 0");
                bw.newLine();
                bw.write("  map-code: ");
                bw.newLine();
                bw.write("  kuaidaoyun-name: " + map.get("kuaidaoyun-name"));
                bw.newLine();
                bw.write("  kuaidaoyun-code: " + codes[1].trim());
                bw.newLine();
                bw.newLine();

                map = new HashMap<>();
            }

        }
        reader.close();
        bw.close();
    }
}
