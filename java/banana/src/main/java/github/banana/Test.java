package github.banana;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 比较两组订单数据的差异
 * 
 * https://poi.apache.org
 * 
 * @author zhgxun
 *
 */
class Test {
    public static void main(String[] args) {
        String a = "12.56";
        String b = "12.560";
        try {
            if (Float.parseFloat(a) == Float.parseFloat(b)) {
                System.out.println("=====");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("开始比较差异订单...");
        DataFormatter formatter = new DataFormatter();
        Workbook book = null;
        try {
            // 订单信息
            Map<String, String> orders = new HashMap<>();

            // 打开.xlsx表格
            System.out.println("读取表格...");
            book = WorkbookFactory.create(new File("201707.xlsx"));
            // 读取第三个活动表格携程订单
            Sheet sheet1 = book.getSheetAt(0);
            System.out.println("处理携程订单...");
            // 遍历一行
            for (Row row : sheet1) {
                // 直接获取前两列
                orders.put(formatter.formatCellValue(row.getCell(0)), formatter.formatCellValue(row.getCell(1)));
            }
            for (String key : orders.keySet()) {
                System.out.println(key + "=>" + orders.get(key));
            }
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}