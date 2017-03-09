package almsaeedstudio;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationPidFileWriter;

/**
 * Created by oleg on 3/9/17.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .listeners(new ApplicationPidFileWriter("almsaeedstudio.pid"))
                .run(args);
    }

}
