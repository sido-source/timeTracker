package com.time_tracker.backendtime_tracker;

import com.time_tracker.backendtime_tracker.Repositories.RoleRepository;
import com.time_tracker.backendtime_tracker.initialDataLoading.InsertRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
//@ComponentScan(basePackages = "Controllers")
//@ComponentScan(basePackages = "Dtos")
//@ComponentScan(basePackages = "Entities")
//@ComponentScan(basePackages = "Repositories")
//@ComponentScan(basePackages = "Services.Company")
public class BackendTimeTrackerApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

//	@Bean
//	private companyRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendTimeTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Insert the data to Company Tabel
//		CompanyDto companyDto1 = new CompanyDto("name1", "industry1", 1900, 123);
//		Company companyDto2 = new Company(1,"name1", "industry1", 1900, 123);
//		companyRepository.save(companyDto2);

		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean);
		}

	}

	@Bean
	public CommandLineRunner run(RoleRepository roleRepository) {
		return new InsertRules().run(roleRepository);
	}
}
