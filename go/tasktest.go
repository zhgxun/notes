// 测试任务
package main

import (
	"fmt"
	"notes/go/util"
	"os"
	"time"
)

func main() {
	r := util.New(10 * time.Second)
	r.Add(
		util.Task{testTask, []int{1, 2, 3}, []string{"2017-09-01", "2017-09-10"}},
		util.Task{testTask, []int{1, 2, 3}, []string{}},
		util.Task{testTask, []int{}, []string{"2017-09-01", "2017-09-10"}},
	)

	if err := r.Start(); err != nil {
		switch err {
		case util.ErrTimeout:
			fmt.Println(util.ErrTimeout)
			os.Exit(1)
		case util.ErrInterrupt:
			fmt.Println(util.ErrInterrupt)
			os.Exit(2)
		}
	}
}

// 测试传入整数
// interface{}可用于向函数传递任意类型的变量, 但对于函数内部, 该变量仍然为interface{}类型
// 接口类型向普通类型的转换称为类型断言(运行期确定)
func testTask(n ...interface{}) {
	// 故意制造超时事件
	//time.Sleep(5 * time.Second)

	// 写一个死循环, 手动kill中断运行
	//i := 0
	//for {
	//	i += 1
	//}

	// 处理正常事件
	for _, res := range n {
		switch r := res.(type) {
		case nil:
			fmt.Println("empty value")
		case []int:
			for _, num := range r {
				fmt.Println(num)
			}
		case []string:
			for _, s := range r {
				fmt.Println(s)
			}
		default:
			fmt.Println("unknown type error")
		}
	}
}
