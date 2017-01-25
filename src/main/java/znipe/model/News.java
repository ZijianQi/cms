package znipe.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Everlasting on 2017-01-24.
 */

@Document(collection="News")
public class News {
    @Id
    private  ObjectId id;
    private  String description;
    private  String name;
    private  List<Columns> rows;

    public News(){}

    public News(ObjectId id, String description, String name, List<Columns> rows){
        this.id = id;
        this.description = description;
        this.name = name;
        this.rows = rows;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public List<Columns> getRows() {
        return rows;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRows(List<Columns> rows) {
        this.rows = rows;
    }
}
