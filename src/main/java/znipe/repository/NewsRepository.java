package znipe.repository;

import znipe.model.Column;
import znipe.model.News;

/**
 * Created by Everlasting on 2017-01-24.
 */
public interface NewsRepository{
    News fetchNews();

    void delete(News news);

    void save(News news);
    void test();
}
