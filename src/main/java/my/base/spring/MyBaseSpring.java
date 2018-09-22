package my.base.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {Jsr310JpaConverters.class}, basePackages = {"my.base.spring.model"})
@MapperScan("my.base.spring.mapper")
@SpringBootApplication
public class MyBaseSpring
{
	public static void main(String ar[]){
		SpringApplication.run(MyBaseSpring.class, ar);
	}


}
