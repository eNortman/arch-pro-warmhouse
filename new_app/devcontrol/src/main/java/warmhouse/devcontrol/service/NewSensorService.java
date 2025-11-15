package warmhouse.devcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import warmhouse.devcontrol.MqClient;
import warmhouse.dataholders.SensorValueMessage;

import java.util.Date;
import java.util.Random;

@Service
class NewSensorService implements SensorApiService{

    private final SensorsService sensorsService;
    private final Random random = new Random();

    @Autowired
    public MqClient mqClient;

    @Scheduled(fixedDelay = 2000)
    public void poll(){

        System.out.println("Polling for new temperature Sensor values");

        var observableSensors = sensorsService.findAllSensorsByType(this.getSensorType());

        for (var sensor : observableSensors) {

            if (random.nextInt(10) < 3) {
                sensor.setValue(random.nextInt(15) + 10);
                sensor.setLastUpdated(new Date());

                sensorsService.save(sensor);

                var msg = new SensorValueMessage();
                msg.setSensorId(sensor.getId());
                msg.setValue(sensor.getValue());
                msg.setStatus(true);
                msg.setSensorName(sensor.getName());

                mqClient.sendMessage(msg);
            }

        }

    }

    @Override
    public String getSensorType() {
        return "NEWAPISENSOR";
    }

    NewSensorService(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

}
