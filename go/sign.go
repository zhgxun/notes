package main

import (
	"fmt"
	"sort"
	"crypto/md5"
	"encoding/hex"
	"github.com/spf13/cast"
)

func main() {
	req := make(map[string]interface{}, 0)
	req["appid"] = 2004
	req["order_id"] = "20134460486412345678"
	req["pay_id"] = 13
	req["app_channel_id"] = 30
	req["refund_no"] = "510000273002"
	req["trade_no"] = "2012091778619123"
	req["refund_fee"] = 4900
	//req["refund_reason"] = "预售订单收款申请退款"
	req["notify_url"] = "yourrefundurl"
	//req["batch_no"] = "202002204200207189000120"

	apiKey := "OxtWYBXsngZTdqcNDsMDhooV7ovfRatG"

	ks := make([]string, 0, len(req))
	for k := range req {
		if k == "sign" {
			continue
		}
		ks = append(ks, k)
	}
	ctx := md5.New()
	sort.Strings(ks)
	buf := make([]byte, 256)
	for _, k := range ks {
		v := cast.ToString(req[k])
		if v == "" {
			continue
		}
		buf = buf[:0]
		buf = append(buf, k...)
		buf = append(buf, '=')
		buf = append(buf, v...)
		buf = append(buf, '&')
		ctx.Write(buf)
	}
	buf = buf[:0]
	buf = append(buf, "key="...)
	buf = append(buf, apiKey...)
	ctx.Write(buf)
	sign := ctx.Sum(nil)

	fmt.Println(hex.EncodeToString(sign))
}