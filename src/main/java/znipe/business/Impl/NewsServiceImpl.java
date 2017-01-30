package znipe.business.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import znipe.business.NewsService;
import znipe.model.Column;
import znipe.model.Columns;
import znipe.model.News;
import znipe.repository.NewsRepository;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Everlasting on 2017-01-24.
 */

@Repository
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;


    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @Override
    public News fetchNews() throws FileNotFoundException {
        News news = newsRepository.fetchNewsById();
        if (news == null){
            throw new FileNotFoundException();
        }
        return news;
    }

    @Override
    public Boolean fetchNewsByTitle(String title) throws FileNotFoundException{
       return newsRepository.IsNewsExistsByTitle(title);
    }

    @Override
    public void insert(Column column) throws Exception{

        if(column.getTitle()==null || column.getDescription()==null || column.getHref()==null || column.getPic()==null)
            throw new IllegalArgumentException();

        if(fetchNewsByTitle(column.getTitle())== true)
            throw new FileAlreadyExistsException("the data exists already");

        List<Column> columns = new ArrayList<>();
        columns.add(column);

        News news = fetchNews();
        news.getRows().add(new Columns(columns));
        newsRepository.save(news);
    }

    @Override
    public void delete(String title) throws FileNotFoundException{
        if(title==null)
            throw new IllegalArgumentException();

        News news = fetchNews();

        if(fetchNewsByTitle(title)== true) {
            for (Columns columns : news.getRows()) {
                for (Column c : columns.getColumns()) {
                    if (c.getTitle().equals(title)) {
                        news.getRows().remove(columns);
                        newsRepository.save(news);
                        return;
                    }
                }
            }
        }else{
            throw new FileNotFoundException();
        }
    }

    @Override
    public void modify(Column column, String title) throws FileNotFoundException{
        if(column.getTitle()==null || column.getDescription()==null || column.getHref()==null
                || column.getPic()==null || title==null)
            throw new IllegalArgumentException();

        News news = fetchNews();

        if(fetchNewsByTitle(title)== true) {
            for (Columns columns : news.getRows()) {
                for (Column c : columns.getColumns()) {
                    if (c.getTitle().equals(title)) {
                        c.setTitle(column.getTitle());
                        c.setPic(column.getPic());
                        c.setDescription(column.getDescription());
                        c.setHref(column.getHref());
                        c.setRendering_template(column.getRendering_template());
                        newsRepository.save(news);
                        return;
                    }
                }
            }
        }else{
            throw new FileNotFoundException();
        }
    }

}
