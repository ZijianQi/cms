import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import znipe.business.Impl.NewsServiceImpl;
import znipe.business.NewsService;
import znipe.model.Column;
import znipe.model.News;
import znipe.repository.NewsRepository;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

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
    public void fetchNewsByTitle_should_call_on_IsNewsExistsByTitle() throws FileNotFoundException {
        final NewsRepository newsRepository = mock(NewsRepository.class);
        final NewsService newsService = new NewsServiceImpl(newsRepository);

        String title = "test";
        newsService.fetchNewsByTitle(title);
        verify(newsRepository, times(1)).IsNewsExistsByTitle(title);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insert_should_throw_if_null() throws Exception{
        final NewsRepository newsRepository = mock(NewsRepository.class);
        final NewsService newsService = new NewsServiceImpl(newsRepository);
        String test = "test";

        when(newsRepository.fetchNewsById()).thenReturn(null);
        Column column = new Column();
        //when title is null
        column.setDescription(test);
        column.setPic(test);
        column.setHref(test);
        newsService.insert(column);
        column.setTitle(test);
        newsService.insert(column);

    }

    @Test(expected = FileAlreadyExistsException.class)
    public void insert_should_throw_if_exists() throws Exception{
        final NewsRepository newsRepository = mock(NewsRepository.class);
        final NewsService newsService = new NewsServiceImpl(newsRepository);
        String test = "test";

        when(newsService.fetchNewsByTitle(test)).thenReturn(true);
        Column column = new Column();
        column.setDescription(test);
        column.setPic(test);
        column.setHref(test);
        column.setTitle(test);
        newsService.insert(column);

    }
}
