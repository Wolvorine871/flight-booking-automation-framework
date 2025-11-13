package utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getLoginData() throws IOException{
		String path=System.getProperty("user.dir")+"\\testData\\FlightLogin_Data.xlsx";
		
		//Taking xl file from testData folder
		ExcelReaderUtility xlutil=new ExcelReaderUtility(path);
		int totalRow=xlutil.getRowCount("Sheet1");
		int totalCols=xlutil.getCellCount("Sheet1", 1);
		
		
		String loginData[][]=new String[totalRow][totalCols];
		
		for (int i = 1; i <= totalRow; i++) {
			for (int j = 0; j < totalCols; j++) {
				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
	
	@DataProvider(name="AirportSearch")
	public String[][] getAirpotData() throws IOException{
		String path=System.getProperty("user.dir")+"\\testData\\FlightLogin_Data.xlsx";
		
		//Taking xl file from testData folder
		ExcelReaderUtility xlutil=new ExcelReaderUtility(path);
		int totalRow=xlutil.getRowCount("Sheet2");
		int totalCols=xlutil.getCellCount("Sheet2", 1);
		
		
		String airports[][]=new String[totalRow][totalCols];
		
		for (int i = 1; i <= totalRow; i++) {
			for (int j = 0; j < totalCols; j++) {
				airports[i-1][j]=xlutil.getCellData("Sheet2", i, j);
			}
		}
		return airports;
	}
}



//Add DataProvider2 later