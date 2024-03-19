package uk.ac.ucl.model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class DataFrame {
    private Map<String, Column> columns;

    public DataFrame() {
        this.columns = new LinkedHashMap<>();
    }

    public void addColumn(String name) {
        columns.put(name, new Column(name));
    }

    public void setColumnNames(List<String> columnNames) {
            for (String columnName : columnNames) {
                this.addColumn(columnName);
            }
        }
    
        
    public void addRowData(List<String> rowData) {
    int columnIndex = 0;
            for (String columnName : this.getColumnNames()) {
                if (columnIndex < rowData.size()) {
                    Column column = columns.get(columnName);
                    column.addRowValue(rowData.get(columnIndex));
                } else {
                    // 如果rowData中的值少于列名数量，可以选择添加空字符串或其他占位符
                    Column column = columns.get(columnName);
                    column.addRowValue("");
                }
                columnIndex++;
            }
    }

    public List<String> getColumnNames() {
        return new ArrayList<>(columns.keySet());
    }

    public int getRowCount() {
        if (!columns.isEmpty()) {
            return columns.values().iterator().next().getSize();
        }
        return 0;
    }

    public String getValue(String columnName, int row) {
        Column column = columns.get(columnName);
        if (column != null) {
            return column.getRowValue(row);
        }
        return null; // or throw an exception
    }

    public void putValue(String columnName, int row, String value) {
        Column column = columns.get(columnName);
        if (column != null) {
            column.setRowValue(row, value);
        } else {
            // Handle the case where the column does not exist
        }
    }

    public void addValue(String columnName, String value) {
        Column column = columns.get(columnName);
        if (column != null) {
            column.addRowValue(value);
        } else {
            // Handle the case where the column does not exist
        }
    }
}
