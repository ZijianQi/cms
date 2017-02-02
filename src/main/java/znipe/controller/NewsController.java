package znipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import znipe.model.Column;
import znipe.model.Columns;
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

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String homePage(){
        return "Welcome!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/news")
    public News fetchNews() throws FileNotFoundException{
        return newsService.fetchNews();
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public void insertNews(@RequestBody Columns columns) throws Exception{
        newsService.insert(columns);
    }

    @RequestMapping(value = "/news/{title}", method = RequestMethod.DELETE)
    public void deleteNews(@PathVariable("title") String title) throws FileNotFoundException{
        newsService.delete(title);
    }

    @RequestMapping(value = "/news/{title}", method = RequestMethod.POST)
    public void modifyNews(@RequestBody Column column, @PathVariable("title") String title) throws FileNotFoundException{
        newsService.modify(column, title);
    }


}
