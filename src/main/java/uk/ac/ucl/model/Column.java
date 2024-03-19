package uk.ac.ucl.model;
import java.util.ArrayList;

public class Column {
    private String name;
    private ArrayList<String> rows;

    public Column (String name) {
        this.name = name;
        this.rows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return rows.size();
    }

    public String getRowValue(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < rows.size()) {
            return rows.get(rowIndex);
        } else {
            // Handle invalid index, could throw an exception or return null
            return null;
        }
    }

    public void setRowValue(int rowIndex, String value) {
        // Check if the index is valid and add a new row or update an existing one
        if (rowIndex >= 0 && rowIndex < rows.size()) {
            rows.set(rowIndex, value);
        } else if (rowIndex == rows.size()) {
            rows.add(value);
        } else {
            // Handle invalid index, could throw an exception
        }
    }

    public void addRowValue(String value) {
        rows.add(value);
    }
}
