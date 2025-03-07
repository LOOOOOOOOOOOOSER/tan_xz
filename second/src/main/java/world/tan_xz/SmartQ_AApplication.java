package world.tan_xz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("world.tan_xz.dao")
public class SmartQ_AApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartQ_AApplication.class, args);
    }

}