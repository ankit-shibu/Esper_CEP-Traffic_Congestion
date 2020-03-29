package com.espertech.esper.project;

import java.util.Properties;
import com.espertech.esper.project.ReadDataStatement;
import com.espertech.esper.project.ReadDataListener;
import com.espertech.esper.project.Threshold1Statement;
import com.espertech.esper.project.Threshold1Listener;
import com.espertech.esper.project.KafkaInputProcessorDefault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.espertech.esperio.kafka.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.*;
import com.espertech.esper.common.client.EPCompiled;

public class EventHandler implements Runnable {

	private static final Log log = LogFactory.getLog(EventHandler.class);

	private EPRuntime runtime=null;
	private String engineURI;
	public static int[] a;
	public static int flag;
	public static int flag_kafka;
	public EventHandler(String engineURI) {
		log.info("New EventsHandler created");
		this.engineURI = engineURI;
	}
	public EventHandler() {
		
	}
	public static void main(String args[])
	{
		 EventHandler EventHandlerMain = new EventHandler("EventHandler");
		 a =new int[24];
		 a[0] = 4598; a[1] =72; a[2] = 67;
		 a[3] = 4687; a[4] = 77; a[5] = 61;
		 a[6] = 4536; a[7] = 59; a[8] = 66;
		 a[9] = 3465; a[10] = 70; a[11] = 55;
		 a[12] = 4161; a[13] = 59; a[14] = 72;
		 a[15] = 3607; a[16] = 72; a[17] = 51;
		 a[18] = 2854; a[19] = 65; a[20] = 41;
		 a[21] = 2474; a[22] = 83; a[23] = 79;
	        try {
	        	flag_kafka = 1;
	            EventHandlerMain.run();
	        } catch (Throwable t) {
	            log.error("Failed to run example: " + t.getMessage(), t);
	        }
	        while(true);
	}
	public EPRuntime getRuntime() {
		return runtime;
	}
	
	public static void setKafka(Properties props)
	{
		// Kafka Consumer Properties
				props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
				props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class.getName());
				props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class.getName());
				props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");

				// EsperIO Kafka Input Adapter Properties
				props.put(EsperIOKafkaConfig.INPUT_SUBSCRIBER_CONFIG, EsperIOKafkaInputSubscriberByTopicList.class.getName());
				props.put(EsperIOKafkaConfig.TOPICS_CONFIG, "trafficdata");
				props.put(EsperIOKafkaConfig.INPUT_PROCESSOR_CONFIG, KafkaInputProcessorDefault.class.getName());
				props.put(EsperIOKafkaConfig.INPUT_TIMESTAMPEXTRACTOR_CONFIG, EsperIOKafkaInputTimestampExtractorConsumerRecord.class.getName());
	}
	public void run() {
		Properties props = new Properties();
		flag = 1;
		System.out.println("Run again");
		
		if(flag_kafka == 1)
		setKafka(props);
		
		Configuration configuration = new Configuration();
		configuration.getCommon().addEventType("CombinedEvent",CombinedEvent.class.getName());
		configuration.getCommon().addEventType("FeaturesEvent",FeaturesEvent.class.getName());
		
		// Adding the plugin for Kafka
		configuration.getRuntime().addPluginLoader("KafkaInput", EsperIOKafkaInputAdapterPlugin.class.getName(), props, null);
		log.info("Setting up EPL");
		runtime = EPRuntimeProvider.getRuntime(engineURI,configuration);		
		
		EPCompiled compiled1 = null;
		ReadDataStatement simpleStm3 = new ReadDataStatement(compiled1,configuration,runtime);
		simpleStm3.addListener(new ReadDataListener(runtime));
		
		EPCompiled compiled2 = null;
		Threshold1Statement simpleStm4 = new Threshold1Statement(compiled2,configuration,runtime,a[0],a[1],a[2]);
		simpleStm4.addListener(new Threshold1Listener(runtime));
		
		EPCompiled compiled3 = null;
		Threshold2Statement simpleStm5 = new Threshold2Statement(compiled3,configuration,runtime,a[3],a[4],a[5]);
		simpleStm5.addListener(new Threshold2Listener(runtime));
		
		EPCompiled compiled4 = null;
		Threshold3Statement simpleStm6 = new Threshold3Statement(compiled4,configuration,runtime,a[6],a[7],a[8]);
		simpleStm6.addListener(new Threshold3Listener(runtime));
		
		EPCompiled compiled5 = null;
		Threshold4Statement simpleStm7 = new Threshold4Statement(compiled5,configuration,runtime,a[9],a[10],a[11]);
		simpleStm7.addListener(new Threshold4Listener(runtime));
		
		EPCompiled compiled6 = null;
		Threshold5Statement simpleStm8 = new Threshold5Statement(compiled6,configuration,runtime,a[12],a[13],a[14]);
		simpleStm8.addListener(new Threshold5Listener(runtime));
		
		EPCompiled compiled7 = null;
		Threshold6Statement simpleStm9= new Threshold6Statement(compiled7,configuration,runtime,a[15],a[16],a[17]);
		simpleStm9.addListener(new Threshold6Listener(runtime));
		
		EPCompiled compiled8 = null;
		Threshold7Statement simpleStm10 = new Threshold7Statement(compiled8,configuration,runtime,a[18],a[19],a[20]);
		simpleStm10.addListener(new Threshold7Listener(runtime));
		
		EPCompiled compiled9 = null;
		Threshold8Statement simpleStm11 = new Threshold8Statement(compiled9,configuration,runtime,a[21],a[22],a[23]);
		simpleStm11.addListener(new Threshold8Listener(runtime));
		
		//runtime.getEventService().sendEventBean(new FeaturesEvent(0,10000,10000,0,"14/09/2019 16:50",2,0),"FeaturesEvent");
		//runtime.getEventService().sendEventBean(new FeaturesEvent(0,10000,10000,0,"14/09/2019 16:500",2,0),"FeaturesEvent");
		//runtime.getEventService().sendEventBean(new FeaturesEvent(0,10000,10000,0,"14/09/2019 16:50",2,0),"FeaturesEvent");

		log.info("Done setting up engine");
		while(flag == 1)
		{
			try { 
                Thread.sleep(1); 
            } 
            catch (InterruptedException e) { 
                System.out.println("Caught:" + e); 
            } 
		}
		configuration = null;
		System.out.println("Thresholds Changing");
	}

}
