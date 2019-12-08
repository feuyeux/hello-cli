package requester

import (
	"context"
	"github.com/feuyeux/hello-rsocket/common"
	"log"

	"github.com/rsocket/rsocket-go/payload"
)

func ExecRequestResponse() {
	log.Println("====ExecRequestResponse====")
	client, _ := BuildClient()
	defer client.Close()
	// Send request
	request := &common.HelloRequest{Id: "1"}
	json, _ := request.ToJson()
	p := payload.New(json, nil)
	result, _ := client.RequestResponse(p).Block(context.Background())
	response := common.JsonToHelloResponse(result.Data())
	log.Println("[Request-Response] response id:", response.Id, ",name:", response.Name)
	log.Println("[Request-Response] data:", result.DataUTF8())
}
