# hello-cli

- java:
  - <https://github.com/apache/commons-cli>
  - <https://github.com/mojohaus/appassembler>

more:
- [Commons CLI](https://commons.apache.org/proper/commons-cli/)
- [args4j](http://args4j.kohsuke.org/)
- [JCommander](http://jcommander.org/)
- [picocli](https://picocli.info/)

- rust: 
  ```
  cargo new hello-cli-rust
  ```
  
- golang: 
  [cobra](github.com/spf13/cobra/cobra)

  ```
	- spf13/cobra
	- urfave/cli
  
```
  mkdir hello-cli-go
  cd hello-cli-go
  go mod init hello-cli-go
  ```

- gitignore:

  ```
  curl https://www.gitignore.io/api/go,rust,java,maven,intellij+iml,visualstudiocode,macos -o .gitignore
  ```