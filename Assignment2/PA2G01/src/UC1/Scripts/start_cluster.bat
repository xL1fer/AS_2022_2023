::===============================::
:: UC1 start cluster script file ::
::===============================::

:: start zookeeper
start C:/kafka_2.13-3.1.0/bin/windows/zookeeper-server-start.bat zookeeper.properties

:: start brokers
start C:/kafka_2.13-3.1.0/bin/windows/kafka-server-start.bat server_0.properties
start C:/kafka_2.13-3.1.0/bin/windows/kafka-server-start.bat server_1.properties
start C:/kafka_2.13-3.1.0/bin/windows/kafka-server-start.bat server_2.properties

:: start topic
start C:/kafka_2.13-3.1.0/bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --replication-factor 1 --partitions 3 --topic Sensor