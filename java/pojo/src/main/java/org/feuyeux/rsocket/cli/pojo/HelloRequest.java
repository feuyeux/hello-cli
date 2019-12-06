package org.feuyeux.rsocket.cli.pojo;

import lombok.Getter;
import lombok.ToString;

/**
 * @author feuyeux@gmail.com
 */
@Getter
@ToString
public class HelloRequest {
    private String id;

    public HelloRequest() {
    }

    public HelloRequest(String id) {
        this.id = id;
    }
}
