package info.thecodinglive.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {Jsr310JpaConverters.class}, basePackages = {"info.thecodinglive.member.model"})
@MapperScan("info.thecodinglive.member.mapper")
@SpringBootApplication
public class MemberApp
{
	public static void main(String ar[]){
		SpringApplication.run(MemberApp.class, ar);
	}


}
