package znipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import znipe.model.Column;
import znipe.model.News;
import znipe.business.NewsService;

import java.io.FileNotFoundException;

/**
 * Created by Everlasting on 2017-01-24.
 */

@RestController
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) throws FileNotFoundException {
        this.newsService = newsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/news")
    public News fetchNews() throws FileNotFoundException{
        return newsService.fetchNews();
    }

    // add
    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public void insertNews(@RequestBody Column column) throws Exception{
        newsService.insert(column);
    }

    // delete
    @RequestMapping(value = "/news/{title}", method = RequestMethod.DELETE)
    public void deleteNews(@PathVariable("title") String title) throws FileNotFoundException{
        newsService.delete(title);
    }

    //modify
    @RequestMapping(value = "/news/{title}", method = RequestMethod.POST)
    public void modifyNews(@RequestBody Column column, @PathVariable("title") String title) throws FileNotFoundException{
        newsService.modify(column, title);
    }


}
