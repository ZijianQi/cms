package znipe.repository.Impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import znipe.model.News;
import znipe.repository.NewsRepository;
import org.springframework.data.mongodb.core.MongoTemplate;


import static org.springframework.data.mongodb.core.query.Criteria.where;


/**
 * Created by Everlasting on 2017-01-24.
 */

@Repository
public class NewsRepositoryImpl implements NewsRepository{

    private final MongoTemplate mongo;

    @Autowired
    public NewsRepositoryImpl(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public News fetchNewsById(){
        return mongo.findById(new ObjectId("5795dea9cea368e92defba98"), News.class);
    }

    @Override
    public Boolean IsNewsExistsByTitle(String title){
        Query query = new Query(where("rows.columns.title").is(title));
        return mongo.exists(query, News.class);
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


