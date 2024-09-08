### test

```bash
go run hello.go --cpu cores
go run hello.go cpu cores
```

### build&run
```bash
go build
./hello-cli-go hello.go --cpu cores
./hello-cli-go cpu cores
./hello-cli-go -h
```

### dependency
- urfave https://github.com/urfave/cli
- cobra
  -  [cobra](https://github.com/spf13/cobra): A Commander for modern Go CLI interactions
  -  [pflag](https://github.com/spf13/pflag): Forked from ogier/pflag Drop-in replacement for Go's flag package, implementing POSIX/GNU-style --flags.
  -  [viper](https://github.com/spf13/viper): Go configuration with fangs


