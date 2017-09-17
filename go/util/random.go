// 生成随机字符串
package util

import (
	"math/rand"
	"time"
)

// Rand 根据类型输出随机字符串
// number 数字个数
// lower 小写字母个数
// upper 大写字母个数
func Rand(size int, number int, lower int, upper int, style int) []byte {
	// 给出的类型不在指定范围内, 则默认使用类型 mix
	warehouse := [][]int{{10, 48}, {65, 97}, {26, 65}}
	output := make([]byte, size)

	// 使用给定的seed将默认资源初始化到一个确定的状态; 如未调用Seed, 默认资源的行为就好像调用了Seed(1).
	rand.Seed(time.Now().UnixNano())

	for i := 0; i < size; i++ {
		if style < 0 || style > 2 {
			style = rand.Intn(3)
		}
		// 每种分类的边界值
		scope, base := warehouse[style][0], warehouse[style][1]
		// rand.Intn(n) 返回一个取值范围在 [0, n) 的伪随机int值, 如果 n<=0 会panic.
		output[i] = uint8(base + rand.Intn(scope))
	}
	return output
}
