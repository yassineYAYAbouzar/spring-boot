package com.springboot.demo;

import com.springboot.demo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//@Bean
	//public BCryptPasswordEncoder bCryptPasswordEncoder(){
		//return new BCryptPasswordEncoder();
	//}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public UserDto userDto() {
		return new UserDto();
	}

}
