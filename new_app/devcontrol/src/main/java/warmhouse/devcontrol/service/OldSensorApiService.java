package warmhouse.devcontrol.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import warmhouse.devcontrol.MqClient;
import warmhouse.dataholders.OldApiResponse;
import warmhouse.dataholders.SensorValueMessage;
import warmhouse.devcontrol.dbo.Sensor;

import java.util.Date;
import java.util.Optional;

@Service
class OldSensorApiService implements SensorApiService {

    @Value("${TEMPERATURE_API_URL}")
    private String temperatureApiUrl;

    private RestClient restClient;
    private final RestClient.Builder builder;
    private final SensorsService sensorsService;

    @Autowired
    public MqClient mqClient;

    public Optional<SensorValueMessage> updateSensorValue(Sensor sensor) {

        //URI uri = new URI(temperatureApiUrl);
        //"/temperature/{id}", sensor.getSensorSerialId()

        var resp = restClient.get()
                .uri("/temperature/{id}", sensor.getSensorSerialId())
                .retrieve()
                .body(OldApiResponse.class);

        if (resp != null) {

            sensor.setValue(resp.getValue());
            sensor.setLastUpdated(new Date());
            sensor.setLocation(resp.getLocation());

            var msg = new SensorValueMessage();
            msg.setSensorId(sensor.getId());
            msg.setValue(sensor.getValue());
            msg.setStatus(true);
            msg.setSensorName(sensor.getName());
            msg.setComment(resp.description);

            sensorsService.save(sensor);

            return Optional.of(msg);

        }

        return Optional.empty();
    }

    @Scheduled(fixedDelay = 5000)
    public void poll(){

        System.out.println("Polling for old temperature Sensor values");

        var observableSensors = sensorsService.findAllSensorsByType(this.getSensorType());

        for (var sensor : observableSensors){
            var omsg = this.updateSensorValue(sensor);
            omsg.ifPresent(msg -> mqClient.sendMessage(msg) );
        }

    }

    @Override
    public String getSensorType() {
        return "OLDAPISENSOR";
    }

    @PostConstruct
    public void init() {

        if (!temperatureApiUrl.startsWith("http")) {
            throw new IllegalStateException("Missing scheme in TEMPERATURE_API_URL environment variable: "
                    + temperatureApiUrl);
        }

        this.restClient = builder
                .baseUrl(temperatureApiUrl)
                .defaultHeader("Accept", "application/json")
                .build();

    }

    public OldSensorApiService(RestClient.Builder builder, SensorsService sensorsService) {
        this.builder = builder;
        this.sensorsService = sensorsService;
    }
}
