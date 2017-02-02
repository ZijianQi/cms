package znipe.business.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import znipe.business.NewsService;
import znipe.model.Column;
import znipe.model.Columns;
import znipe.model.News;
import znipe.repository.NewsRepository;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Everlasting on 2017-01-24.
 */

@Component
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
    public Boolean isNewsExistingByTitle(String title) throws FileNotFoundException{
       return newsRepository.IsNewsExistsByTitle(title);
    }

    @Override
    public void insert(Columns columns) throws Exception{

        for(Column column: columns.getColumns()) {
            if (column.getTitle() == null || column.getDescription() == null || column.getHref() == null || column.getPic() == null) {
                throw new IllegalArgumentException();
            }

            if (isNewsExistingByTitle(column.getTitle()) == true) {
                throw new FileAlreadyExistsException("the data exists already");
            }
        }

            News news = fetchNews();
            news.getRows().add(columns);
            newsRepository.save(news);
    }

    @Override
    public void delete(String title) throws FileNotFoundException{
        if(title==null) {
            throw new IllegalArgumentException();
        }

        News news = fetchNews();

        if(isNewsExistingByTitle(title)== true) {

            for (Columns columns : news.getRows()) {
                for (Column c : columns.getColumns()) {

                    if (c.getTitle().equals(title)) {
                        columns.getColumns().remove(c);

                        if(columns.getColumns().size() == 0)
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

        if(isNewsExistingByTitle(title)== true) {
            news.getRows().forEach(columns -> {
                columns.getColumns().forEach(c -> {
                    if (c.getTitle().equals(title)) {
                        c.setTitle(column.getTitle());
                        c.setPic(column.getPic());
                        c.setDescription(column.getDescription());
                        c.setHref(column.getHref());
                        c.setRendering_template(column.getRendering_template());
                        newsRepository.save(news);
                        return;
                    }
                });
            });
        }else{
            throw new FileNotFoundException();
        }
    }
}
