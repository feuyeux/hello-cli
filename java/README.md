#### pojo
```bash
▶ cd pojo
▶ mvn clean install
```

#### responder
```bash
▶ cd responder
▶ mvn spring-boot:run
```

#### requester
```bash
▶ cd requester
▶ mvn spring-boot:run
```

#### cli
```bash
curl http://localhost:8080/hello/1
curl http://localhost:8080/hello-stream
curl http://localhost:8080/hello-channel
curl http://localhost:8080/hello-forget
```

```bash
▶ mvn clean package appassembler:assemble

#TODO
target/hello-cli/bin/hello.sh -v
target/hello-cli/bin/hello.sh --cpu cores
```