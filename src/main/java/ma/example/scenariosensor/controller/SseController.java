package ma.example.scenariosensor.controller;

import lombok.RequiredArgsConstructor;
import ma.example.scenariosensor.service.RealtimeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sse")
public class SseController {

    private final RealtimeService sseService;

    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        return sseService.connect();
    }
}
