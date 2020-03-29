# Esper_CEP-Traffic_Congestion

## Esper CEP
Esper is an open-source Java-based software product for Complex event processing (CEP) and Event stream processing (ESP), that analyzes series of events for deriving conclusions from them.

## Requirements
- java
- ESPER jar file
- Apache Kafka Lenses

## Getting Started
```
- Esper_CEP-Traffic_Congestion/src/Project/com/espertech/esper/project/EventHandler.java
- csvjson.json the sample input to be ingested by the kafka lenses
```
## Working

- First the kafka lenses account needs to be created and started on the topic - "trafficdata".
  * Configuration of the kafka lenses can be changed in the kafka adapter in EventhHandler.java
- Then run the EventHandler.java. Feed in the live stream into the kafka topic. Alerts are generated onto the console on every congestion event.

