package requester

import (
	"fmt"
	"github.com/feuyeux/hello-rsocket/common"
	"github.com/rsocket/rsocket-go/payload"
	"log"
	"math/rand"
	"strconv"
)

//ExecRequestStream ...
func ExecRequestStream() {
	fmt.Println()
	log.Println("====ExecRequestStream====")
	cli, _ := BuildClient()
	defer cli.Close()
	ids := RandomIds(5)

	request := &common.HelloRequests{}
	request.Ids = ids
	json, _ := request.ToJson()
	p := payload.New(json, []byte(Now()))
	f := cli.RequestStream(p)
	PrintFlux(f, "[Request-Stream]")
}

func RandomIds(max int) []string {
	ids := make([]string, max, max)
	for i := range ids {
		ids[i] = RandomId(max)
	}
	return ids
}

func RandomId(max int) string {
	return strconv.Itoa(rand.Intn(max))
}
