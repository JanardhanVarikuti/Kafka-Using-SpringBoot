# Kafka-Using-SpringBoot
Simple Kafka Consumer demo using Spring Boot deployed on Weblogic12c server

This is a simple kafka consumer class which is listening to a kakfa topic for the data. The application is built using spring boot and deployed the .jar file on the wevlogic 12c server.

Please follow the below steps to built and deploy the application :

    Step1> mvn clean
 
    Step2> mvn clean install
 
    Step3> Copy the .jar from the "Target" folder
 
    Step4> Transfer the .jar to any folder location on the weblogic 12c server
 
    Step5> Execute the following command :
           java -jar <name of your jar>.jar 
  
    Step6> Execute the "kafka console producer" from the kafka installed server
           for e.g., ./bin/kafka-console-producer --broker-list locakhost:9092 --topic sample_consu_topic
    
    Step7> You can verify the data that is being produced from Step6 is being displayed on weblogic server (Step5)
