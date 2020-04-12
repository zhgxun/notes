package main

import "fmt"
import "encoding/json"

type responseDelivery struct {
	Code    int            `json:"code"`
	Message string         `json:"message"`
	Data    resultDelivery `json:"data"`
}

type resultDelivery struct {
	Result map[string][]ExpressInfo `json:"resultMap"`
}

type ExpressInfo struct {
	ExpressSn string `json:"expressSn"`
	Content   string `json:"content"`
}

func main() {
	body := "{\"code\":0,\"message\":\"ok\",\"data\":{\"resultMap\":{\"4191007128604127\":[{\"expressSn\":\"defg458\",\"content\":\"ok\"},{\"expressSn\":\"defg459\",\"content\":\"ok\"}]}}}"
	fmt.Println(body)

	resp := responseDelivery{}

	err := json.Unmarshal([]byte(body), &resp)
	if err == nil && resp.Code == 0 {
		for k, v := range resp.Data.Result {
			fmt.Println(k)
			fmt.Println(v)
		}
	}
}
