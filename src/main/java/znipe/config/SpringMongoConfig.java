package znipe.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Created by Everlasting on 2017-01-24.
 */

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class SpringMongoConfig extends AbstractMongoConfiguration {

    private final Environment environment;

    @Autowired
    public SpringMongoConfig(Environment env){
        this.environment = env ;
    }


    @Override
    public String getDatabaseName() {
        return environment.getProperty("databaseName");
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoClientURI uri  = new MongoClientURI(environment.getProperty("mongoClientUri"));
        return new MongoClient(uri);
    }
}

