package requester

import (
	"context"
	"github.com/feuyeux/hello-rsocket/common"
	"github.com/rsocket/rsocket-go/payload"
	"log"
)

func ExecRequestResponse() {
	log.Println("====ExecRequestResponse====")
	client, _ := BuildClient()
	defer client.Close()
	// Send request
	request := &common.HelloRequest{Id: "1"}
	json, _ := request.ToJson()
	p := payload.New(json, []byte(Now()))
	result, _ := client.RequestResponse(p).Block(context.Background())
	data := result.Data()
	response := common.JsonToHelloResponse(data)
	//redisClient := common.RedisClient{}
	//redisClient.Open("","6379","")
	//redisClient.Set("2019-12-09-RSOCKET", string(data))
	//redisData := redisClient.Get("2019-12-09-RSOCKET")
	//log.Println("[Request-Response] redisData:", redisData)
	log.Println("[Request-Response] response id:", response.Id, ",name:", response.Name)
}
