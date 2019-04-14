package loggertester;

import com.aimetpgm.util.logger.CustomLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class);
    }

    @Configuration
    @ComponentScan(basePackages = "com.aimetpgm.util.logger")
    public class AnnotationConfig {
    }

    @RequestMapping("/")
    @RestController
    public class MyController{

        @CustomLogger
        @GetMapping
        public String HelloWorld(
                @RequestParam(required = false) String greetingWord,
                @RequestParam(required = false) String name
        ) {
            if (null == name && null == greetingWord) return "Hello, World";
            if (null == greetingWord) greetingWord = "Hello".concat(", ");
            return null == name ? greetingWord.concat(", Annonymous") :greetingWord.concat(name) ;
        }
    }
}
