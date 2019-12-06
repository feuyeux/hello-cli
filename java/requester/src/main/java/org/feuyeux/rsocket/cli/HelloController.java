package org.feuyeux.rsocket.cli;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.feuyeux.rsocket.cli.pojo.HelloRequest;
import org.feuyeux.rsocket.cli.pojo.HelloResponse;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.stream.Collectors.toList;

/**
 * @author feuyeux@gmail.com
 */
@Slf4j
@RestController
public class HelloController {
    private final HelloRSocketAdapter helloRSocketAdapter;
    private Random random = new Random();

    HelloController(HelloRSocketAdapter helloRSocketAdapter) {
        this.helloRSocketAdapter = helloRSocketAdapter;
    }

    @GetMapping("hello-metadata")
    Mono<Void> metaData() {
        MimeType mimeType = MimeType.valueOf("message/x.rsocket.authentication.bearer.v0");
        String securityToken = "...";
        return helloRSocketAdapter.metaData(securityToken, mimeType);
    }

    @GetMapping("hello-forget")
    Mono<Void> fireAndForget() {
        return helloRSocketAdapter.fireAndForget("1024");
    }

    @GetMapping("/hello/{id}")
    Mono<HelloResponse> getCustomer(@PathVariable String id) {
        return helloRSocketAdapter.getCustomer(id);
    }

    @GetMapping(value = "/hello-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Publisher<HelloResponse> getCustomers() {
        List<String> ids = getRandomIds(5);
        log.info("random={}", ids);
        return helloRSocketAdapter.getCustomers(ids);
    }

    @GetMapping(value = "/hello-channel", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Publisher<HelloResponse> getCustomersChannel() {
        Flux<HelloRequest> map = Flux.interval(Duration.ofMillis(1000))
            .map(id -> new HelloRequest(getRandom(5)));
        return helloRSocketAdapter.getCustomerChannel(map);
    }

    private List<String> getRandomIds(int max) {
        return IntStream.range(0, max)
            .mapToObj(i -> getRandom(max))
            .collect(toList());
    }

    private String getRandom(int max) {
        int i = random.nextInt(max);
        return String.valueOf(i);
    }
}
