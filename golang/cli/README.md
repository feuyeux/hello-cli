

```bash
▶ cd golang

▶ go run main.go
2019/10/24 18:30:42 ====ExecRequestResponse====
2019/10/24 18:30:42 data: 你好 metadata: 世界
2019/10/24 18:30:42 response: FramePayload{FrameHeader{id=1,type=PAYLOAD,flag=N|CL|M},data=你好,metadata=世界}
2019/10/24 18:30:42 data: 你好
2019/10/24 18:30:42 ====ExecFireAndForget====
2019/10/24 18:30:42 GOT FNF: FrameFNF{FrameHeader{id=1,type=REQUEST_FNF,flag=M},data=hello,metadata=bonjour}
```


```bash
go run hello.go --cpu cores
go run hello.go cpu cores
```