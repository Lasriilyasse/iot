package ma.example.scenariosensor.service;

import ma.example.scenariosensor.entity.Measurement;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class RealtimeService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter connect() {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

    public void push(Measurement measurement) {
        emitters.forEach(emitter -> {
            try {
                emitter.send(measurement);
            } catch (Exception e) {
                emitters.remove(emitter);
            }
        });
    }
}
