package requester

import (
	"fmt"
	"github.com/feuyeux/hello-rsocket/common"
	"github.com/rsocket/rsocket-go/payload"
	"log"
)

//ExecFireAndForget ...
func ExecFireAndForget() {
	fmt.Println()
	log.Println("====ExecFireAndForget====")
	client, _ := BuildClient()
	defer client.Close()
	request := &common.HelloRequest{Id: "1"}
	json, _ := request.ToJson()
	client.FireAndForget(payload.New(json, nil))
}
