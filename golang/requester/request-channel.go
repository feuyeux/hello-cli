package requester

import (
	"context"
	"fmt"
	"github.com/feuyeux/hello-rsocket/common"
	"log"
	"time"

	"github.com/rsocket/rsocket-go/payload"
	"github.com/rsocket/rsocket-go/rx/flux"
)

func ExecRequestChannel() {
	fmt.Println()
	log.Println("====ExecRequestChannel====")
	cli, _ := BuildClient()
	defer cli.Close()

	send := flux.Create(func(i context.Context, sink flux.Sink) {
		for i := 1; i <= 5; i++ {
			request := &common.HelloRequest{}
			request.Id = RandomId(5)
			json, _ := request.ToJson()
			p := payload.New(json, []byte(Now()))
			sink.Next(p)
			time.Sleep(100 * time.Millisecond)
		}
		time.Sleep(1000 * time.Millisecond)
		sink.Complete()
	})

	f := cli.RequestChannel(send)
	PrintFlux(f, "[Request-Channel]")
}

func Now() string {
	return time.Now().Format("2006-01-02 3:4:50 pm")
}

// PrintFlux ...
func PrintFlux(f flux.Flux, s string) (err error) {
	_, err = f.
		DoOnNext(func(p payload.Payload) {
			data := p.Data()
			response := common.JsonToHelloResponse(data)
			log.Println(s, "response:", response)
		}).
		BlockLast(context.Background())
	return
}
