import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import znipe.business.Impl.NewsServiceImpl;
import znipe.business.NewsService;
import znipe.model.Column;
import znipe.model.Columns;
import znipe.model.News;
import znipe.repository.NewsRepository;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Everlasting on 2017-01-29.
 */
public class NewsServiceTests {

        private  NewsRepository newsRepository;

    @Autowired
        public NewsServiceTests(){

        }

        @Test(expected = FileNotFoundException.class)
        public void fetchNews_should_throw_if_null() throws FileNotFoundException{
            final NewsRepository newsRepository = mock(NewsRepository.class);
            final NewsService newsService = new NewsServiceImpl(newsRepository);

            when(newsRepository.fetchNewsById()).thenReturn(null);

            newsService.fetchNews();
        }

        @Test
        public void fetchNews_should_call_on_fetchNewsById() throws FileNotFoundException {
            final NewsRepository newsRepository = mock(NewsRepository.class);
            final NewsService newsService = new NewsServiceImpl(newsRepository);

            when(newsRepository.fetchNewsById()).thenReturn(new News());
            newsService.fetchNews();
            verify(newsRepository, times(1)).fetchNewsById();
        }

    @Test
    public void isNewsExistingByTitle_should_call_on_IsNewsExistsByTitle() throws FileNotFoundException {
        final NewsRepository newsRepository = mock(NewsRepository.class);
        final NewsService newsService = new NewsServiceImpl(newsRepository);

        String title = "test";
        newsService.isNewsExistingByTitle(title);
        verify(newsRepository, times(1)).IsNewsExistsByTitle(title);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insert_should_throw_if_null() throws Exception{
        final NewsRepository newsRepository = mock(NewsRepository.class);
        final NewsService newsService = new NewsServiceImpl(newsRepository);
        String test = "test";

        when(newsRepository.fetchNewsById()).thenReturn(null);
        Column column = new Column(test,null, test, test, test);
        List<Column> columnsList = new ArrayList<>();
        columnsList.add(column);
        Columns columns = new Columns(columnsList);

        newsService.insert(columns);
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void insert_should_throw_if_exists() throws Exception{
        final NewsRepository newsRepository = mock(NewsRepository.class);
        final NewsService newsService = new NewsServiceImpl(newsRepository);
        String test = "test";

        when(newsService.isNewsExistingByTitle(test)).thenReturn(true);

        Column column = new Column(test,test, test, test, test);
        List<Column> columnsList = new ArrayList<>();
        columnsList.add(column);
        Columns columns = new Columns(columnsList);

        newsService.insert(columns);
    }
}
