#!/bin/bash
YH_HOME=$(cd $(dirname $0)/../; pwd)
if [ ! -z "$JAVA_HOME" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  echo "could not find java, please set JAVA_HOME"
  exit 1
fi
if [ ! -d "$YH_HOME/logs" ]; then
  mkdir -p $YH_HOME/logs
fi
nohup java -jar -server \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=$YH_HOME/logs \
-XX:+PrintGCDetails \
-Xloggc:$YH_HOME/logs/gc.log \
-XX:InitiatingHeapOccupancyPercent=45 ../ruoyi-4.5.1.jar