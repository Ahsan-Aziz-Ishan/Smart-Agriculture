package it.univaq.sensorsimulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@IntegrationComponentScan
@EnableScheduling
public class SensorSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorSimulationApplication.class, args);
    }

}
