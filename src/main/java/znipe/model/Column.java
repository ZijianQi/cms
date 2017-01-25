package znipe.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * Created by Everlasting on 2017-01-24.
 */
@Document
public class Column {
    private String pic;
    private String title;
    private String description;
    private String href;
    private String rendering_template;

    public Column(){}

    public Column (String pic, String title, String description, String rendering_template, String href) {
        this.pic = pic;
        this.title = title;
        this.description = description;
        this.rendering_template = rendering_template;
        this.href = href;
    }

    public String getRendering_template() {
        return rendering_template;
    }

    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHref() {
        return href;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setRendering_template(String rendering_template) {
        this.rendering_template = rendering_template;
    }
}
