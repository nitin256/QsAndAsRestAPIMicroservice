package com.invesco.qsandas.qsandasrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuestionsAndAnswersRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionsAndAnswersRestApplication.class, args);
	}

}
