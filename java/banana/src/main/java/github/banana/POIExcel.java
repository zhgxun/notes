package github.banana;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
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
class POIExcel {
    public static void main(String[] args) {
        // 源文件名
        String sourceFileName = "201707.xlsx";
        
        // 目标文件名
        String targetFileName = "201707_差异订单.xls";
        
        System.out.println("start...");
        DataFormatter formatter = new DataFormatter();
        Workbook book = null;
        try {
            // 保存携程订单数据
            Map<String, String> ctripOrders = new HashMap<>();
            // 保存百度订单数据
            Map<String, String> baiduOrders = new HashMap<>();

            // 打开.xlsx表格
            book = WorkbookFactory.create(new File(sourceFileName));
            // 读取第三个活动表格携程订单
            Sheet sheetTrip = book.getSheetAt(0);
            // 遍历一行
            for (Row row : sheetTrip) {
                // 读取前两行
                ctripOrders.put(formatter.formatCellValue(row.getCell(0)), formatter.formatCellValue(row.getCell(1)));
            }

            // 读取第四个活动表格百度订单
            Sheet sheetBaidu = book.getSheetAt(1);
            for (Row row : sheetBaidu) {
                // 读取前两行内容
                baiduOrders.put(formatter.formatCellValue(row.getCell(0)), formatter.formatCellValue(row.getCell(1)));
            }
            
            // 将剩余的订单写入到一个新的excel表格中
            book = new HSSFWorkbook();
            Sheet sheetDiff = book.createSheet("差异订单");
            
            // 控制行记录
            int i = 0;
            
            // 标题栏
            Row rowTitle = sheetDiff.createRow(i);
            Cell cellTitleOrderId = rowTitle.createCell(0);
            cellTitleOrderId.setCellValue("供应商订单号");
            Cell cellTitleAmount = rowTitle.createCell(1);
            cellTitleAmount.setCellValue("供应商佣金");
            Cell cellBaiduAmount = rowTitle.createCell(2);
            cellBaiduAmount.setCellValue("百度佣金");
            
            // 用百度订单号来比较两种佣金是否相等
            for (String orderId : baiduOrders.keySet()) {
                // 如果携程订单不存在, 则新增一条携程订单
                String ctripCommission = ctripOrders.get(orderId);
                String baiduCommission = baiduOrders.get(orderId);
                if (ctripCommission == null) {
                    // 新增一行数据记录
                    Row rowEmpty = sheetDiff.createRow(++i);
                    Cell cellEmptyOrderId = rowEmpty.createCell(0);
                    cellEmptyOrderId.setCellValue(orderId);
                    Cell cellEmptyCtripAmount = rowEmpty.createCell(1);
                    cellEmptyCtripAmount.setCellValue("-");
                    Cell cellEmptyBaiduAmount = rowEmpty.createCell(2);
                    cellEmptyBaiduAmount.setCellValue(baiduCommission);
                    continue;
                }

                // 比较佣金是否相等
                if (Float.parseFloat(ctripCommission) != Float.parseFloat(baiduCommission)) {
                    Row rowLine = sheetDiff.createRow(++i);
                    Cell cellLineOrderId = rowLine.createCell(0);
                    cellLineOrderId.setCellValue(orderId);
                    Cell cellLineCtripAmount = rowLine.createCell(1);
                    cellLineCtripAmount.setCellValue(ctripCommission);
                    Cell cellLineBaiduAmount = rowLine.createCell(2);
                    cellLineBaiduAmount.setCellValue(baiduCommission);
                }
            }

            // 将差异数据写入到excel文件中
            FileOutputStream output = new FileOutputStream(targetFileName);
            book.write(output);
            output.close();
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }
}