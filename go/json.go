// json格式说明
package main

import (
	"encoding/json"
	"fmt"
	"log"
)

func main() {
	// 后面的标签表明格式化为json后的键名, 如果指定omitempty, 则当个该字段为空时不参数json格式组成
	type Movie struct {
		Title  string
		Year   int  `json:"released"`
		Color  bool `json:"color,omitempty"`
		Actors []string
	}

	var movies = []Movie{
		{Title: "Casablanca", Year: 1942, Color: false,
			Actors: []string{"Humphrey Bogart", "Ingrid Bergman"}},
		{Title: "Cool Hand Luke", Year: 1967, Color: true,
			Actors: []string{"Paul Newman"}},
		{Title: "Bullitt", Year: 1968, Color: true,
			Actors: []string{"Steve McQueen", "Jacqueline Bisset"}},
	}

	data, err := json.Marshal(movies)
	if err != nil {
		log.Fatalf("JSON marshaling failed: %s", err)
	}
	fmt.Printf("%s\n", data)
	fmt.Println("Done")

	//同步包裹运单号
	type PackageExpressReq struct {
		OutPackageId string `json:"outPackageId"`
		ExpressSn    string `json:"expressSn"`
		ExpressName  string `json:"expressName"`
		BizCode      string `json:"bizCode"`
	}

	var reqObj = []PackageExpressReq{
		{
			OutPackageId: "abc",
			ExpressSn:    "asdv",
			BizCode:      "jd",
			ExpressName:  "京东",
		},
	}

	reqObjBytes, e := json.Marshal(reqObj)
	if e != nil {
		log.Fatalf("JSON marshaling failed: %s", e)
	}
	fmt.Printf("%s\n", reqObjBytes)
	fmt.Println("Done")

}
