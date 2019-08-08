package club.sdll.ptc.springcloud.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月17日 09:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
