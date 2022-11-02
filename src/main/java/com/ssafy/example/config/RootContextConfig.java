package com.ssafy.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.example.model.repo.HomeRepo;
import com.ssafy.example.model.repo.UserRepo;

@Configuration
@MapperScan(basePackageClasses = {HomeRepo.class, UserRepo.class})
public class RootContextConfig {

}
