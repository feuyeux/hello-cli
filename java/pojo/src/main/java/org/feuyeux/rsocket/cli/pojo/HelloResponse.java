package org.feuyeux.rsocket.cli.pojo;

import lombok.Getter;
import lombok.ToString;

/**
 * @author feuyeux@gmail.com
 */
@Getter
@ToString
public class HelloResponse {

    private String id;

    private String name;

    public HelloResponse() {
    }

    public HelloResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }
}