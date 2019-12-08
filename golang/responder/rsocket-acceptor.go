package responder

import (
	"context"
	"fmt"
	"github.com/feuyeux/hello-rsocket/common"
	"log"
	"strconv"

	rsocket "github.com/rsocket/rsocket-go"

	"github.com/jjeffcaii/reactor-go/scheduler"
	"github.com/rsocket/rsocket-go/payload"
	"github.com/rsocket/rsocket-go/rx"
	"github.com/rsocket/rsocket-go/rx/flux"
	"github.com/rsocket/rsocket-go/rx/mono"
)

func RSocketAcceptor() rsocket.RSocket {
	return rsocket.NewAbstractSocket(
		rsocket.MetadataPush(func(item payload.Payload) {
			log.Println("[Responder::MetadataPush] GOT METADATA_PUSH:", item)
		}),
		rsocket.FireAndForget(func(p payload.Payload) {
			data := p.Data()
			request := common.JsonToHelloRequest(data)
			log.Println("[Responder::FireAndForget] GOT FNF:", request.Id)
		}),
		rsocket.RequestResponse(func(p payload.Payload) mono.Mono {
			data := p.Data()
			request := common.JsonToHelloRequest(data)
			log.Println("[Responder::RequestResponse] data:", request.Id, "metadata:", nil)
			response := common.HelloResponse{Id: request.Id, Name: "TEST"}
			json, _ := response.ToJson()
			rp := payload.New(json, nil)
			return mono.Just(rp)
		}),
		rsocket.RequestStream(func(pl payload.Payload) flux.Flux {
			s := pl.DataUTF8()
			m, _ := pl.MetadataUTF8()
			log.Println("[Responder::RequestStream] data:", s, "metadata:", m)
			totals := 5
			if n, err := strconv.Atoi(m); err == nil {
				totals = n
			}
			return flux.Create(func(ctx context.Context, emitter flux.Sink) {
				for i := 0; i < totals; i++ {
					// You can use context for graceful coroutine shutdown, stop produce.
					select {
					case <-ctx.Done():
						log.Println("[Responder::RequestStream] ctx done:", ctx.Err())
						return
					default:
						//time.Sleep(10 * time.Millisecond)
						emitter.Next(payload.NewString(fmt.Sprintf("%s_%d", s, i), m))
					}
				}
				emitter.Complete()
			})
		}),
		rsocket.RequestChannel(func(payloads rx.Publisher) flux.Flux {
			//return payloads.(flux.Flux)
			payloads.(flux.Flux).
				//LimitRate(1).
				SubscribeOn(scheduler.Elastic()).
				DoOnNext(func(elem payload.Payload) {
					log.Println("[Responder::RequestChannel] receiving:", elem)
				}).
				Subscribe(context.Background())
			return flux.Create(func(i context.Context, sink flux.Sink) {
				for i := 0; i < 3; i++ {
					sink.Next(payload.NewString("world", fmt.Sprintf("%d", i)))
				}
				sink.Complete()
			})
		}),
	)
}
