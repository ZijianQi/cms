package znipe.repository.Impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import znipe.model.News;
import znipe.repository.NewsRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by Everlasting on 2017-01-24.
 */

@Component
public class NewsRepositoryImpl implements NewsRepository{

    private final MongoTemplate mongo;

    @Autowired
    public NewsRepositoryImpl(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public News fetchNews(){
        return mongo.findById(new ObjectId("5795dea9cea368e92defba98"), News.class);
    }

    @Override
    public void delete(News news){
        mongo.remove(news);
    }

    @Override
    public void save(News news){
        mongo.save(news);
    }

    @Override
    public void test(){

    }
}


