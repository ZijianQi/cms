package znipe.business.Impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import znipe.business.NewsBusiness;
import znipe.model.Column;
import znipe.model.Columns;
import znipe.model.News;
import znipe.repository.NewsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Everlasting on 2017-01-24.
 */

@Repository
public class NewsBusinessImpl implements NewsBusiness {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsBusinessImpl(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @Override
    public News fetchNews(){
        return newsRepository.fetchNews();
    }

    @Override
    public void insert(Column column){

        List<Column> columns = new ArrayList<>();
        columns.add(column);

        News news = fetchNews();
        news.getRows().add(new Columns(columns));
        newsRepository.save(news);
    }

    @Override
    public void delete(String title){
        News news = fetchNews();

        for(Columns columns: news.getRows()){
            for(Column c: columns.getColumns()){
                if(c.getTitle().equals(title)) {
                    news.getRows().remove(columns);
                    newsRepository.save(news);
                    return;
                }
            }
        }
    }

    @Override
    public void modify(Column column, String title){
        News news = fetchNews();

        for(Columns columns: news.getRows()){
            for(Column c: columns.getColumns()){
                if(c.getTitle().equals(title)) {
                    c.setPic(column.getPic());
                    c.setDescription(column.getDescription());
                    c.setHref(column.getHref());
                    c.setRendering_template(column.getRendering_template());
                    newsRepository.save(news);
                    return;
                }
            }
        }
    }

}
