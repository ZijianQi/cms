package znipe.repository;

import znipe.model.News;

/**
 * Created by Everlasting on 2017-01-24.
 */
public interface NewsRepository{
    News fetchNewsById();
    Boolean IsNewsExistsByTitle(String title);
    void delete(News news);
    void save(News news);
    void test();
}
