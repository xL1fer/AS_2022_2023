::===============================::
:: UC2 stop cluster script file  ::
::===============================::

:: stop brokers
start C:/kafka_2.13-3.1.0/bin/windows/kafka-server-stop.bat

:: stop zookeeper
start C:/kafka_2.13-3.1.0/bin/windows/zookeeper-server-stop.bat