cli

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