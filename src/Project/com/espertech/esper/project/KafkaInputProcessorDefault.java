package com.espertech.esper.project;
import java.text.*;
import com.espertech.esper.project.CombinedEvent;
import com.espertech.esper.project.FeaturesEvent;
import com.espertech.esper.project.EventHandler;
import com.espertech.esperio.kafka.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import com.espertech.esper.common.internal.util.JavaClassHelper;
import com.espertech.esper.project.EventHandler;
import com.espertech.esper.runtime.client.*;
import java.util.*;

public class KafkaInputProcessorDefault implements EsperIOKafkaInputProcessor {

	  private EPRuntime runtime;
	  //private KafkaConsumer<Object,Object> consumer;
	  //private ConsumerRecords<Object,Object> records;
	  private EsperIOKafkaInputTimestampExtractor timestampExtractor;

	  public void init(EsperIOKafkaInputProcessorContext context) {
		 this.runtime = context.getRuntime();
	    System.out.println("init");
	    String timestampExtractorClassName = context.getProperties().getProperty(EsperIOKafkaConfig.INPUT_TIMESTAMPEXTRACTOR_CONFIG);
        if (timestampExtractorClassName != null) {
            timestampExtractor = (EsperIOKafkaInputTimestampExtractor) JavaClassHelper.instantiate(EsperIOKafkaInputTimestampExtractor.class, timestampExtractorClassName, context.getRuntime().getServicesContext().getClasspathImportServiceRuntime().getClassForNameProvider());
        }
	  }

	  public void process(ConsumerRecords<Object, Object> records) {
		  System.out.println("Nothing here!!!!!!!!!!!!!!!!!!!!!!");
		  System.out.println(records.count());
		  int i = 0;
	    for (ConsumerRecord<Object,Object> record : records) {
	    	if (timestampExtractor != null) {
                long timestamp = timestampExtractor.extract(record);
                runtime.getEventService().advanceTimeSpan(timestamp);
            }
	      if (record.value() != null) {
	        String event = (String)record.value();
	    	int type = getType(event);	    	
	    	if(type == 1)
	    		{
	    		traffic(event);
	    		}
	    	else
	    		thresholds(event);	   	
	      }
	    }
	  }
	  void thresholds(String event) 
	  {
		  EventHandler.flag = 0;
		  EventHandler.flag_kafka = 0;
		  String[] obj = new String[8];
		  for(int i=0;i<8;i++)
		  {
			  obj[i] = getThresholds(event,i);
		  }
		  int[] a = new int[24];
		  for(int i=0;i<8;i++)
		  {
			  a[3*i] = getFirst(obj[i]);
			  a[3*i+1] = getSecond(obj[i]);
			  a[3*i+2] = getThird(obj[i]);
			  EventHandler.a[3*i] = getFirst(obj[i]);
			  EventHandler.a[3*i+1] = getSecond(obj[i]);
			  EventHandler.a[3*i+2] = getThird(obj[i]);
		  }
		    EventHandler EventHandlerMain = new EventHandler("EventHandler1");
		  try {
	            EventHandlerMain.run();
	        } catch (Throwable t) {
	            System.out.println("Error");
	        }
	  }
	  
	  void traffic (String event)
	  {
		  	int velocity = getVel(event);
	        int intensity =getInt(event);
	        int tweet_count = getTweet(event); 
	        String time =gettime(event);
	        String weather_status = getWeather(event);
	        
	        int weather,timeofday,typeofday;
	        //System.out.println("Combined Event generated for "+time+" "+velocity+" "+intensity+" "+tweet_count+" ");
	        if(weather_status.equals("partly-cloudy-day")||weather_status.equals("partly-cloudy-night")||weather_status.equals("clear-night"))
	    		weather = 0;
	    	else if(weather_status.equals("fog")||weather_status.equals("cloudy"))
	    		weather = 1;
	    	else
	    		weather = 2;
	        
	        //System.out.println("Combined Event generated for "+time+" "+velocity+" "+intensity+" "+tweet_count);
	        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy HH:mm");
			  Date dt1 = null ;
			  try{
			  dt1=format1.parse(time);
			  }catch(Exception e)
			  {
			  System.out.println("Exception");
			  }
			  DateFormat format2=new SimpleDateFormat("HH"); 
			  int hour= Integer.parseInt(format2.format(dt1));
			  
	    	if(hour>=8&&hour<=12)
	    		timeofday=1;
	    	else if(hour>12&&hour<=16)
	    		timeofday=2;
	    	else if(hour>16&&hour<=20)
	    		timeofday=3;
	    	else
	    		timeofday=4;
	        
	    	DateFormat format3=new SimpleDateFormat("EEEE");
	    	String finalDay=format3.format(dt1);
	    	
	    	if(finalDay.equals("Saturday") || finalDay.equals("Sunday"))
	    		typeofday = 0;
	    	else
	    		typeofday = 1;
	    	System.out.println("Combined Event generated for "+time+" "+velocity+" "+intensity+" "+tweet_count+" "+weather+" "+timeofday+" "+typeofday);
	    	runtime.getEventService().sendEventBean(new FeaturesEvent(velocity,intensity,tweet_count,weather,time,timeofday,typeofday),"FeaturesEvent"); 
	  }
	  
	  String getThresholds(String t,int typ)
	  {
		  int i = t.indexOf("\""+typ+"\"");
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(t.charAt(j)=='['&&f==-1)
				{
				i=j;
				f=1;
				}
				if(t.charAt(j)==']')
				{
					k=j;
					break;
				}
			}
			return (t.substring(i,k+1));
	  }
	  int getVel(String t)
	  {
		  int i = t.indexOf("\"velocity\"");
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(Character.isDigit(t.charAt(j))&&f==-1)
				{
				i=j;
				f=1;
				}
				if(t.charAt(j)==',')
				{
					k=j;
					break;
				}
			}
			return (int)(Integer.parseInt(t.substring(i,k)));
	  }
	  int getFirst(String t)
	  {
		  int i = 0;
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(Character.isDigit(t.charAt(j))&&f==-1)
				{
				i=j;
				f=1;
				}
				if(t.charAt(j)==','&&f==1)
				{
					k=j;
					break;
				}
			}
			return (int)(Integer.parseInt(t.substring(i,k)));
	  }
	  
	  int getSecond(String t)
	  {
		  	int i = 0;
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(t.charAt(j)==','&&f==1)
				{
					k=j;
					break;
				}
				if(t.charAt(j)==','&&f==-1)
				{
				i=j;
				f=1;
				}

			}
			return (int)(Integer.parseInt(t.substring(i+1,k)));
	  }
	  
	  int getThird(String t)
	  {
		  int i = 0;
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(t.charAt(j)==','&&f<=0)
				{
				i=j;
				++f;
				}
				if(t.charAt(j)==']')
				{
					k=j;
					break;
				}
			}
			return (int)(Integer.parseInt(t.substring(i+1,k)));
	  }
	  
	  int getType(String t)
	  {
		  int i = t.indexOf("\"type\"");
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(Character.isDigit(t.charAt(j))&&f==-1)
				{
				i=j;
				f=1;
				}
				if(t.charAt(j)==',')
				{
					k=j;
					break;
				}
			}
			return (int)(Integer.parseInt(t.substring(i,k)));
	  }
	  
	  int getInt(String t)
	  {
		  int i = t.indexOf("\"intensity\"");
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(Character.isDigit(t.charAt(j))&&f==-1)
				{
				i=j;
				f=1;
				}
				if(t.charAt(j)==',')
				{
					k=j;
					break;
				}	
			}
			return ((int)Integer.parseInt(t.substring(i,k)));
	  }
	  
	  int getTweet(String t)
	  {
		  int i = t.indexOf("\"tweet_count\"");
			int j,k=i,f=-1;
			for(j=i;j<t.length();j++)
			{
				if(Character.isDigit(t.charAt(j))&&f==-1)
				{
				i=j;
				f=1;
				}
				if(t.charAt(j)==',')
				{
					k=j;
					break;
				}	
			}
			return (int)(Integer.parseInt(t.substring(i,k)));
	  }
	  
	  String getWeather(String t)
	  {
		  int i = t.indexOf("\"weather_status\"");
			int j,k=i,f=-1;
			i+=16;
			for(j=i;j<t.length();j++)
			{
				if(t.charAt(j)=='\"'&&f==-1)
				{
				i=j+1;
				f=1;
				}
				else if(t.charAt(j)=='\"'&&f==1)
				{
				k=j;
				break;
				}
			}
			return t.substring(i,k);
	  }
	  
	  String gettime(String t)
	  {
		  int i = t.indexOf("\"time\"");
			i+=8;
			return t.substring(i,i+16);
	  }
	  
	  public void close() {}
	}