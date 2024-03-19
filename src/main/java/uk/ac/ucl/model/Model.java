package uk.ac.ucl.model;

import java.io.BufferedReader;

// import java.io.Reader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import org.apache.commons.csv.CSVFormat;
// import org.apache.commons.csv.CSVParser;
// import org.apache.commons.csv.CSVRecord;

// public class Model
// {
//   // The example code in this class should be replaced by your Model class code.
//   // The data should be stored in a suitable data structure.

//   public List<String> getPatientNames()
//   {
//     return readFile("data/patients100.csv");
//   }

//   // This method illustrates how to read csv data from a file.
//   // The data files are stored in the root directory of the project (the directory your project is in),
//   // in the directory named data.
//   public List<String> readFile(String fileName)
//   {
//     List<String> data = new ArrayList<>();

//     try (Reader reader = new FileReader(fileName);
//          CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT))
//     {
//       for (CSVRecord csvRecord : csvParser)
//       {
//         // The first row of the file contains the column headers, so is not actual data.
//         data.add(csvRecord.get(0));
//       }
//     } catch (IOException e)
//     {
//       e.printStackTrace();
//     }
//     return data;
//   }

//   // This also returns dummy data. The real version should use the keyword parameter to search
//   // the data and return a list of matching items.
//   public List<String> searchFor(String keyword)
//   {
//     return List.of("Search keyword is: "+ keyword, "result1", "result2", "result3");
//   }
// }


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Model {
    private DataFrame dataFrame;
    private DataLoader dataLoader;

    public Model() {
        dataFrame = new DataFrame();
        dataLoader = new DataLoader();
    }



    // Load data from CSV file into the DataFrame
    public void loadData(String filePath) {
        try {
            // Directly load the data into the DataFrame using DataLoader
            dataFrame = dataLoader.loadDataFromCSV(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method illustrates how to read csv data from a file and returns it as a List of List of String.
    //public List<List<String>> readFile(String fileName) {
    //     List<List<String>> data = new ArrayList<>();
    //     try (Reader reader = new FileReader(fileName);
    //          CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
    //         for (CSVRecord csvRecord : csvParser) {
    //             List<String> rowData = new ArrayList<>();
    //             csvRecord.forEach(rowData::add);
    //             data.add(rowData);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return data;
    // }
    public DataFrame readFile(String fileName) {
        DataFrame dataFrame = new DataFrame();
        try (Reader reader = new FileReader(fileName);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            List<String> columnNames = new ArrayList<>(csvParser.getHeaderMap().keySet());
            columnNames.forEach(dataFrame::addColumn);
            for (CSVRecord record : csvParser) {
                for (String columnName : columnNames) {
                    String value = record.get(columnName);
                    dataFrame.addValue(columnName, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFrame;
    }

      


    // Assuming we have a method to get patient names as a demonstration
    public List<String> getPatientNames() {
        List<String> patientNames = new ArrayList<>();
        int rowCount = dataFrame.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String firstName = dataFrame.getValue("FIRST", i);
            String lastName = dataFrame.getValue("LAST", i);
            String fullName = firstName + " " + lastName;
            patientNames.add(fullName);
        }
        return patientNames;
    }
    

    // Search functionality to be implemented based on actual DataFrame structure
    public List<String> searchFor(String keyword) {
        List<String> searchResults = new ArrayList<>();
        int rowCount = dataFrame.getRowCount();
        List<String> columnNames = dataFrame.getColumnNames();
    
        for (int row = 0; row < rowCount; row++) {
            boolean found = false;
            for (String columnName : columnNames) {
                String value = dataFrame.getValue(columnName, row);
                if (value != null && value.toLowerCase().contains(keyword.toLowerCase())) {
                    found = true;
                    break; // 找到匹配后即停止检查当前行的其他列
                }
            }
            if (found) {
                // 如果找到匹配，获取患者姓名并添加到结果列表中
                String firstName = dataFrame.getValue("FIRST", row);
                String lastName = dataFrame.getValue("LAST", row);
                String fullName = firstName + " " + lastName;
                searchResults.add(fullName);
            }
        }
        return searchResults;
    }
}
