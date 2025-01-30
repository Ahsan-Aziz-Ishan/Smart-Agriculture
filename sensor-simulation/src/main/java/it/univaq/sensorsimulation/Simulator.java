package it.univaq.sensorsimulation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Simulator {

    @Qualifier("mqttConfig.Gateway")
    @Autowired
    private MqttConfig.Gateway gateway;

    @Value("${sensor.humidity.enabled}")
    private boolean humidityEnabled;

    @Value("${sensor.lightintensity.enabled}")
    private boolean lightIntensityEnabled;

    @Value("${sensor.temperature.enabled}")
    private boolean temperatureEnabled;

    @Value("${sensor.soilmoisture.enabled}")
    private boolean soilMoistureEnabled;

    @Value("${sensor.livestock.enabled}")
    private boolean livestockEnabled;

    @Value("${sensor.interval.period}")
    private int intervalPeriod;

    @Value("${number.firms}")
    private int firms;

    @Value("${number.areas}")
    private int areas;

    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void simulate() throws JsonProcessingException, InterruptedException {

        for (int firmId = 1; firmId <= firms; firmId++) {
            for (int areaId = 1; areaId <= areas; areaId++) {
                sendOutdoorSensorReadings(firmId, areaId);
                sendLiveStockReadings(firmId, areaId);
            }
        }

        Thread.sleep(intervalPeriod * 1000);
    }

    private void sendLiveStockReadings(int firmId, int areaId) throws JsonProcessingException {
        if (livestockEnabled) {
            var animals = new ArrayList<String>();
            animals.add("chicken");
            animals.add("cow");
            for (var animal : animals) {
                for (int i = 1; i <= 10; i++) {
                    var sensors = new HashMap<String, Object>();
                    sensors.put("livestock_heartbeat", generateRandomValue(200, 300));
                    var tags = new HashMap<String, Object>();
                    tags.put("firmId", firmId);
                    tags.put("areaId", areaId);
                    tags.put("animal", animal);
                    tags.put("animalId", i);
                    List<Map<String, Object>> sensorData = new ArrayList<>();
                    sensorData.add(sensors);
                    sensorData.add(tags);
                    gateway.sendToMqtt(objectMapper.writeValueAsString(sensorData));
                }
            }
        }
    }

    private void sendOutdoorSensorReadings(int firmId, int areaId) throws JsonProcessingException {
        var sensors = new HashMap<String, Object>();

        if (humidityEnabled) {
            sensors.put("humidity", generateRandomValue(20,100));
        }
        if (lightIntensityEnabled) {
            sensors.put("lightIntensity", generateRandomValue(0,60000));
        }
        if (temperatureEnabled) {
            sensors.put("temperature", generateRandomValue(0,40));
        }
        if (soilMoistureEnabled) {
            sensors.put("soilMoisture", generateRandomValue(15,40));
        }

        var tags = new HashMap<String, Object>();
        tags.put("firmId", firmId);
        tags.put("areaId", areaId);

        List<Map<String, Object>> sensorData = new ArrayList<>();
        sensorData.add(sensors);
        sensorData.add(tags);
        gateway.sendToMqtt(objectMapper.writeValueAsString(sensorData));
    }

    private double generateRandomValue(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }



}
