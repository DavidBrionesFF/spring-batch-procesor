package com.bytecode.springbatch.configuration;

import com.bytecode.springbatch.model.Persona;
import com.mongodb.MongoClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private Environment environment;

    private static Log log = LogFactory.getLog(BatchConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemWriter<Persona> writer() {
        MongoItemWriter<Persona> writer = new MongoItemWriter<Persona>();
        try {
            writer.setTemplate(mongoTemplate());
        } catch (Exception e) {
            log.error(e.toString());
        }
        writer.setCollection("Persona");
        return writer;
    }

    @Value("classpath:file.csv")
    private Resource resource;

    @Bean
    public ItemReader<Persona> reader(){
        FlatFileItemReader<Persona> reader = new FlatFileItemReader<Persona>();

        reader.setResource(resource);

        log.info("EXISTS ==> " + resource.exists());

        reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "nombre", "apellido", "edad" });
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Persona>() {
                    {
                        setTargetType(Persona.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Persona, Persona>chunk(5)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), environment.getProperty("spring.data.mongodb.database"));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
