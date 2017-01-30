package znipe.business;

import znipe.model.Column;
import znipe.model.News;

import java.io.FileNotFoundException;

/**
 * Created by Everlasting on 2017-01-24.
 */

public interface NewsService {
    News fetchNews() throws FileNotFoundException;
    Boolean fetchNewsByTitle(String title) throws FileNotFoundException;
    void insert(Column column) throws Exception;
    void delete(String title) throws FileNotFoundException;
    void modify(Column column, String title) throws FileNotFoundException;
}
