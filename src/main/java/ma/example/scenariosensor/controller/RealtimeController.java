package ma.example.scenariosensor.controller;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.service.RealtimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class RealtimeController {

    private final RealtimeService realtimeService;

    @GetMapping("/stream")
    public SseEmitter stream() {
        return realtimeService.connect();
    }
}
