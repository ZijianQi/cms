package znipe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import znipe.business.NewsBusiness;
import znipe.model.Column;
import znipe.model.News;
import znipe.service.NewsServices;

/**
 * Created by Everlasting on 2017-01-24.
 */

@Service
public class NewsServicesImpl implements NewsServices {

    private final NewsBusiness newsBusiness;

    @Autowired
    public NewsServicesImpl(NewsBusiness newsBusiness)
    {
        this.newsBusiness = newsBusiness;
    }

    @Override
    public News fetchNews(){
        return newsBusiness.fetchNews();
    }

    @Override
    public void insert(Column column){
        newsBusiness.insert(column);
    }

    @Override
    public void delete(String title){
        newsBusiness.delete(title);
    }

    @Override
    public void modify(Column column, String title){
        newsBusiness.modify(column, title);
    }
}
