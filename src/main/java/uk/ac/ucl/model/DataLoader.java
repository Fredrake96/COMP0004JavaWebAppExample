package uk.ac.ucl.model;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
public class DataLoader {
    public DataFrame loadDataFromCSV(String filePath) throws Exception {
        DataFrame dataFrame = new DataFrame();
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            List<CSVRecord> records = csvParser.getRecords();

            for (String columnName : csvParser.getHeaderMap().keySet()) {
                dataFrame.addColumn(columnName);
            }

            for (CSVRecord record : records) {
                for (String columnName : csvParser.getHeaderMap().keySet()) {
                    dataFrame.addValue(columnName, record.get(columnName));
                }
            }
        }
        return dataFrame;
    }
    
}
