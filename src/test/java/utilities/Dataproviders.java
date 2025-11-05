package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {

    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\testData\\Opencart_loginData.xlsx"; // taking xl file from testData
        ExcellUtility xlutil = new ExcellUtility(path); // creating an object for XLUtility
        
        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);
        
        // Create array with totalrows-1 to exclude header row
        String logindata[][] = new String[totalrows-1][totalcols];
        
        for(int i = 1; i < totalrows; i++) { // start from 1 to skip header
            for(int j = 0; j < totalcols; j++) {
                logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        return logindata;
    }
    
    // You can add more DataProviders here as mentioned in your comments
    // @DataProvider(name="YourDataProvider2")
    // public String[][] getData2() throws IOException { ... }
}
