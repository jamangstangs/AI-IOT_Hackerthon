package com.devkuma.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class StreamingController {

    @Autowired
    HelloService helloService;

    @GetMapping(produces = TEXT_EVENT_STREAM_VALUE)
    Flux<Data> sse(@RequestParam(value = "fail", required = false, defaultValue = "false") boolean fail) {
        return source(fail);
    }

    @GetMapping(produces = APPLICATION_NDJSON_VALUE)
    Flux<Data> ndjson(@RequestParam(value = "fail", required = false, defaultValue = "false") boolean fail) throws IOException {
        return source(fail);
    }

    @GetMapping
    Flux<Data> array(@RequestParam(value = "fail", required = false, defaultValue = "false") boolean fail) {
        return source(fail);
    }

    Flux<Data> source(boolean fail) {
        return fail ? failing() : successful();
    }

    Flux<Data> failing() {
        return successful()
                .concatWith(Mono.error(new RuntimeException("Opps!")));
    }

    private Flux<Data> successful() {
        try {
            Runtime.getRuntime().exec("python3 /home/ubuntu/server.py");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> new Data(helloService.getLat(), helloService.getLon()));
    }
}


class Data {
    private final String lat;
    private final String lon;

    Data(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}