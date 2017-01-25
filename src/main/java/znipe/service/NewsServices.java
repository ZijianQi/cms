package znipe.service;

import znipe.model.Column;
import znipe.model.News;

/**
 * Created by Everlasting on 2017-01-24.
 */

public interface NewsServices {
    News fetchNews();
    void insert(Column column);
    void delete(String title);
    void modify(Column column, String title);
}
