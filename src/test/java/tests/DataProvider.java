package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "itemsInCart")
    public Object[][] readItemsToAdd() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/productItems.csv"));
            List<String[]> csvData = csvReader.readAll();
            csvReader.close();
            Object[][] csvItemsToAdd = new Object[1][csvData.size()];
            for(int i = 0; i < csvData.size(); i++){
                csvItemsToAdd[0][i] = csvData.get(i)[0];
            }
            return csvItemsToAdd;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        } catch (CsvException e) {
            System.out.println(e);
            return null;
        }
    }
    public List<String> convertCsvDataToStringList(Object[][] csvItemsToAdd){
        List<String> allItemsList = new ArrayList<String>();
        for(int i = 0; i < csvItemsToAdd.length; i++){
            allItemsList.add((String) csvItemsToAdd[i][0]);
        }
        return allItemsList;
    }
}

