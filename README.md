#BUG DEMO PROJECT

##The problem

I have camunda 7.18 BPM application wrapped into Spring Boot.
The application BPM flow has the single service task. The implementation of this task is "Delegate expression".
The Java Delegate class has the instance varialbe whose value is autowired by Spring.

When I run the application and create new process instance, the value for the instance variable mentioned above is successfully injected by Spring.

When I test the flow using JUnit5 and Camunda's `ProcessEngineExtension`, the the value for the instance variable mentioned above is NOT injected and stays `null`.

## How to reproduce

### Clone the repo

Clone the repo by any mean convenient for you
The following steps assume that your current directory is where you've cloned the project

### Run the BPM flow inside Spring Boot

1. Build the application skipping the tests `mvn package -DskipTests`
2. Run it `java -jar ./target/junit5-1.0.0-SNAPSHOT.jar`
3. After Spring Boot application with Camunda inside is initialized, create new process instance for the process definition key `junit5-process`. You can use any mean that is convenient for you. If you have `curl` in your `PATH`, you can use `./utils/start.sh`
4. The results:
    * If you used `./utils/start.sh`, the JSON with the process instance ID will be printed on the console
    * The Spring Boot application log has  
    ```
    ... c.optum.ibrcp.services.ServiceDelegate   : >> execute()  
    ... c.optum.ibrcp.services.ServiceDelegate   : theBean=[com.optum.ibrcp.spring.SomeSpringBean@40f08583]`  
    ... com.optum.ibrcp.spring.SomeSpringBean    : >> method()  
    ... com.optum.ibrcp.spring.SomeSpringBean    : << method()  
    ... c.optum.ibrcp.services.ServiceDelegate   : << execute()
   ```
    It means that (a) the bean of the class `SomeSpringBeat` has been instantiated and injected into the service delgate object and (b) the bean's method `method()` has been called  
    **Works as designed**
    
### Run the BPM flow inside JUnit5 test

1. Just run `mvn test`
2. The results:
    * The console has  
    ```
    Nov 27, 2023 11:01:38 AM com.optum.ibrcp.services.ServiceDelegate <init>  
    INFO: >> <init()>
    Nov 27, 2023 11:01:38 AM com.optum.ibrcp.services.ServiceDelegate <init>
    INFO: << <init()>
    Nov 27, 2023 11:01:38 AM com.optum.ibrcp.services.ServiceDelegate execute
    INFO: >> execute()
    Nov 27, 2023 11:01:38 AM com.optum.ibrcp.services.ServiceDelegate execute
    INFO: theBean=[null]
    11:01:38.875 [main] DEBUG org.camunda.bpm.engine.context - ENGINE-16006 BPMN Stack Trace:
	    service_task (activity-execute, ProcessInstance[4])
	    service_task, name=Do something
	      ^
	      |
	      StartEvent_1

    11:01:38.876 [main] DEBUG org.camunda.bpm.engine.cmd - ENGINE-13011 closing existing command context
    11:01:38.877 [main] ERROR org.camunda.bpm.engine.context - ENGINE-16004 Exception while closing command context: Cannot invoke "com.optum.ibrcp.spring.SomeSpringBean.method()" because "this.theBean" is null
    java.lang.NullPointerException: Cannot invoke "com.optum.ibrcp.spring.SomeSpringBean.method()" because "this.theBean" is null
	    at com.optum.ibrcp.services.ServiceDelegate.execute(ServiceDelegate.java:31)
        ...
    ```  
    It means that the Spring autowiring did not work and the delagate's instance variable `theBean` stays `null`
     