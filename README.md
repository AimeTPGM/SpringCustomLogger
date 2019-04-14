# CustomLogger

add this project as a module then set annotation configuration as follow

```
@Configuration
@ComponentScan(basePackages = "com.aimetpgm.util.logger")
public class AnnotationConfig {
}
```

put annotation `@CustomLogger` on the method, for example

```
@CustomLogger
@GetMapping
public String HelloWorld() {
    return "Hello, world"
}
```

in application.yml, please add 

```
logging:
    level:
        root: INFO
```

or if you use application.properties, please add

```
logging.level.root=INFO
```

the logger will print out all log message as follow. 

(See logs from `c.a.util.logger.CustomLoggerAspect`)

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.4.RELEASE)

2019-04-14 21:00:31.033  INFO 3408 --- [           main] loggertester.App                         : Starting App on pankamols-MacBook-Pro.local with PID 3408 (/Users/pankamols/playground/logger/target/classes started by pankamols in /Users/pankamols/playground/logger)
2019-04-14 21:00:31.043  INFO 3408 --- [           main] loggertester.App                         : No active profile set, falling back to default profiles: default
2019-04-14 21:00:32.795  INFO 3408 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8081 (http)
2019-04-14 21:00:32.822  INFO 3408 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-04-14 21:00:32.823  INFO 3408 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.17]
2019-04-14 21:00:32.925  INFO 3408 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2019-04-14 21:00:32.925  INFO 3408 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1814 ms
2019-04-14 21:00:33.320  INFO 3408 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-04-14 21:00:33.622  INFO 3408 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
2019-04-14 21:00:33.627  INFO 3408 --- [           main] loggertester.App                         : Started App in 3.28 seconds (JVM running for 3.721)
2019-04-14 21:00:37.292  INFO 3408 --- [nio-8081-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2019-04-14 21:00:37.292  INFO 3408 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2019-04-14 21:00:37.303  INFO 3408 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 10 ms
2019-04-14 21:00:37.347  INFO 3408 --- [nio-8081-exec-1] c.a.util.logger.CustomLoggerAspect       : executing HelloWorld() with args: [greetingWord: null, name: null]
2019-04-14 21:00:37.354  INFO 3408 --- [nio-8081-exec-1] c.a.util.logger.CustomLoggerAspect       : HelloWorld() was executed at 2019-04-14 09:00:341 using 13 ms
2019-04-14 21:00:37.354  INFO 3408 --- [nio-8081-exec-1] c.a.util.logger.CustomLoggerAspect       : HelloWorld() Finish
```

# @Before

will notify the execution of the method with arguments passed into the method

```
2019-04-14 21:00:37.347  INFO 3408 --- [nio-8081-exec-1] c.a.util.logger.CustomLoggerAspect       : executing HelloWorld() with args: [greetingWord: null, name: null]
```

# @Around

will be fired and logging the date time and ms used for executing a particular method

```
2019-04-14 21:00:37.354  INFO 3408 --- [nio-8081-exec-1] c.a.util.logger.CustomLoggerAspect       : HelloWorld() was executed at 2019-04-14 09:00:341 using 13 ms
```

# @After

will ensure if the method is finished executing

# Others Behaviors

`@AfterReturning` will be implemented later

`@AfterThrowing` will be implemented later

if you have any idea or want to contribute, feel free to do so

I'm super happy for that