package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.GridPage;
import utils.ExcelUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

public class GridTest extends BaseTest {
    GridPage grid = new GridPage();
    BasePage bp = new BasePage();

    private String gridPath = "/kendo-angular-ui/demos/grid/filter-all-columns?theme=default-main";

    @Test(description = "Export all online USA employees to Excel and verify export is correct")
    public void shouldExportAndVerifyOnlineUsaEmployeesToExcel() throws Exception {
        bp.navigateTo(gridPath);
        List<Map<String, String>> usa = grid.getUsaEmployeesAllPages();

        List<Map<String, String>> onlyOnline = grid.getOnlineUsaEmployees(usa);

        String excelPath = "employees_online_usa.xlsx";
        ExcelUtil.writeOnlineEmployeesToExcel(onlyOnline, excelPath);

        List<Map<String, String>> readBack = ExcelUtil.readFromExcel(excelPath);
        System.out.println("INFO: Number of employees read from Excel: " + readBack.size());

        Assert.assertEquals(readBack.size(), onlyOnline.size(), "Mismatch in exported employees count.");

        for (int i = 0; i < onlyOnline.size(); i++) {
            Map<String, String> orig = onlyOnline.get(i);
            Map<String, String> fromExcel = readBack.get(i);
            Assert.assertEquals(fromExcel.get("Name"), orig.get("Name"), "Name mismatch in row " + i);
            Assert.assertEquals(fromExcel.get("JobTitle"), orig.get("JobTitle"), "JobTitle mismatch in row " + i);
            Assert.assertEquals(fromExcel.get("Phone"), orig.get("Phone"), "Phone mismatch in row " + i);
            Assert.assertEquals(fromExcel.get("Address"), orig.get("Address"), "Address mismatch in row " + i);
        }
        // Clean up
        new File(excelPath).delete();
    }
}

