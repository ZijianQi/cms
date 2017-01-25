package znipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import znipe.model.Column;
import znipe.model.News;
import znipe.repository.NewsRepository;
import znipe.service.NewsServices;

/**
 * Created by Everlasting on 2017-01-24.
 */

@RestController
public class NewsController {

    private final NewsServices newsServices;
    private final NewsRepository newsRepository;

    @Autowired
    public NewsController(NewsServices newsServices,  NewsRepository newsRepository){
        this.newsServices = newsServices;
        this.newsRepository = newsRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/news")
    public News fetchNews(){
        return newsServices.fetchNews();
    }

    // add
    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public void insertNews(@RequestBody Column column){
        newsServices.insert(column);
    }

    // delete
    @RequestMapping(value = "/news/{title}", method = RequestMethod.DELETE)
    public void deleteNews(@PathVariable("title") String title){
        newsServices.delete(title);
    }

    //modify
    @RequestMapping(value = "/news/{title}", method = RequestMethod.POST)
    public void modifyNews(@RequestBody Column column, @PathVariable("title") String title){
        newsServices.modify(column, title);
    }


}
