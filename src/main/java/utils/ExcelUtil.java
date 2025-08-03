package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelUtil {
    private static final String[] COLUMNS = {"Name", "JobTitle", "Phone", "Address"};

    public static void writeOnlineEmployeesToExcel(List<Map<String, String>> online, String excelPath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("OnlineUSA");

        Row header = sheet.createRow(0);
        for (int i = 0; i < COLUMNS.length; i++)
            header.createCell(i).setCellValue(COLUMNS[i]);

        int rowIdx = 1;
        for (Map<String, String> emp : online) {
            Row row = sheet.createRow(rowIdx++);
            for (int i = 0; i < COLUMNS.length; i++)
                row.createCell(i).setCellValue(emp.get(COLUMNS[i]));
        }
        try (FileOutputStream out = new FileOutputStream(excelPath)) {
            workbook.write(out);
        }
        workbook.close();
    }

    public static List<Map<String, String>> readFromExcel(String excelPath) throws Exception {
        List<Map<String, String>> rows = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.rowIterator();
            List<String> cols = new ArrayList<>();
            if (iterator.hasNext()) {
                Row header = iterator.next();
                header.forEach(cell -> cols.add(cell.getStringCellValue()));
            }
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Map<String, String> data = new HashMap<>();
                for (int i = 0; i < cols.size(); i++)
                    data.put(cols.get(i), row.getCell(i).getStringCellValue());
                rows.add(data);
            }
        }
        return rows;
    }
}