package app.oceanc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author bryan
 * @version 1.0
 * @description: short url application
 * @date 2022/5/26 8:25 PM
 */
//@MapperScan("app.oceanc.mapper")
//@MapperScans(value = {@MapperScan("app.oceanc.mapper")})
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ShortUrlApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
        System.out.println("ShortUrlApplication start...");
    }
}
