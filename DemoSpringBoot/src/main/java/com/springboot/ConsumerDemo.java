package com.springboot;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

//import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
public class ConsumerDemo extends SpringBootServletInitializer implements WebApplicationInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ConsumerDemo.class);
	}

	private static final Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());
	
    public static void main(String[] args) {
    	SpringApplication.run(ConsumerDemo.class, args);

    	logger.info("__ConsumerDemo_Application_Started");       

        Properties prop = new Properties();

        String bootstrapServers = "localhost:9092";
        String groupID = "sample_GroupID_JD12334";
        String topic = "sample_consu_topic";

        // Create the consumer properties
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupID);
        prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        // create consumer

        KafkaConsumer<Long,String> consumer = new KafkaConsumer<Long, String>(prop);


        // subscribe consumer to a topics

        consumer.subscribe(Collections.singleton(topic)); // here we're subscribed to only one topic

        // consumer.subscribe(Arrays.asList("first_topic","second_topic","third_topic")); // here we're subscribe to many topics

        // pool for new data

        while(true){
            ConsumerRecords<Long,String> records = consumer.poll(100); 

            for (ConsumerRecord<Long,String> record : records){

                //logger.info("Key_ConsumerDemo:" +record.key() + ", Value_ConsumerDemo:" + record.value());
                logger.info("Key_debug_ConsumerDemo:" +record.key() + ", Value_debug_ConsumerDemo:" + record.value());
             //   logger.info("partitions :" +record.partition() + ", Offset :" + record.offset());


            }

        }
    }
}
