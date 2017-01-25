
package znipe.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


/**
 * Created by Everlasting on 2017-01-24.
 */
@Document
public class Columns {
    private  List<Column> columns;

    public Columns(){}

    public Columns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
