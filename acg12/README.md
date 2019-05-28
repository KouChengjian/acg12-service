#Tomcat

######启动tomcat服务
方式一：直接启动 ./startup.sh<br>
方式二：作为服务启动 nohup ./startup.sh &<br>
方式三：控制台动态输出方式启动 ./catalina.sh run 动态地显示tomcat后台的控制台输出信息,Ctrl+C后退出并关闭服务<br>
通过方式一、方式三启动的tomcat有个弊端，当客户端连接断开的时候，tomcat服务也会立即停止，通过方式二可以作为linux服务一直运行<br>
通过方式一、方式二方式启动的tomcat，其日志会写到相应的日志文件中，而不能动态地查看tomcat控制台的输出信息与错误情况，通过方式三可以以控制台模式启动tomcat服务，<br>
直接看到程序运行时后台的控制台输出信息，不必每次都要很麻烦的打开catalina.out日志文件进行查看，这样便于跟踪查阅后台输出信息。tomcat控制台信息包括log4j和System.out.println()等输出的信息。<br>

######关闭tomcat服务
./shutdown.sh

######查看Tomcat是否以关闭
ps -ef|grep java

######实时查看tomcat的日志
tail -f catalina.out



#redis

######检查后台进程是否正在运行
ps -ef |grep redis

######检测6379端口是否在监听
netstat -lntp | grep 6379

######使用`redis-cli`客户端检测连接是否正常
./redis-cli

######启动
./redis-server &  <br>
./redis-server ../etc/redis.conf

######停止
使用客户端<br>
redis-cli shutdown<br>
因为Redis可以妥善处理SIGTERM信号，所以直接kill-9也是可以的<br>
kill -9 PID

######使用密码连接
./redis-cli -h 127.0.0.1 -p 6379 -a Passw0rd

######选择数据库
select 1

#MySql
######停止服务
service mysql stop

service mysql status

