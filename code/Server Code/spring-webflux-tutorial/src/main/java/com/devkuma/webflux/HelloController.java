package com.devkuma.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class HelloController {

    @GetMapping("/gps")
    Flux<Map<String, String>> stream() throws IOException {

        // run Python Stream Data
        Process p = Runtime.getRuntime().exec("python /Users/jamang/server.py");

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        System.out.println("sdfdsaf");
        return Flux.fromStream(stream).zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(tuple -> {
                    try {
                        System.out.println("1");
                        return Collections.singletonMap("value", stdInput.readLine());
                    } catch (IOException e) {
                        System.out.println("2");
                        return Collections.singletonMap("value", "0");
                    }
                });

    }


}
