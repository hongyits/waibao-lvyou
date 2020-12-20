@echo off
title  统一运维平台
:: 如果是在路径有空格的情况下，记录要像下面一样加引号，否则路径会出错 (只要设置JAVA_HOME就行，其他不用弄)
:: set JAVA_HOME="C:\Program Files"\Java\jdk1.8.0_231
set JAVA_HOME=
set YH_HOME=%~dp0..
if not exist logs (
  md logs
)
if defined JAVA_HOME (
  echo use JAVA_HOME = %JAVA_HOME%
  set JAVA=%JAVA_HOME%\bin\java
)
%JAVA% -Djava.awt.headless=false -jar -server -Xmx1G -XX:MetaspaceSize=64m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs -XX:+PrintGCDetails -XX:HeapDumpPath=logs -Xloggc:logs\gc.log -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:InitiatingHeapOccupancyPercent=45 %YH_HOME%\ehc-data-1.0.jar
pause