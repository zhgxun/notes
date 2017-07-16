// 一个内置的排序算法需要知道三个东西: 序列的长度, 表示两个元素比较的结果, 一种交换两个元素的方式
package util

import (
	"time"
	"text/tabwriter"
	"os"
	"fmt"
	"sort"
)

// 音乐播放列表
// 标题, 艺术家, 相册, 年份, 运行时间
type Track struct {
	Title  string
	Artist string
	Album  string
	Year   int
	Length time.Duration
}

// 播放列表
// 每个元素都不是Track本身而是指向它的指针
var tracks = []*Track{
	{"Go", "Delilah", "From the Roots Up", 2012, length("3m38s")},
	{"Go", "Moby", "Moby", 1992, length("3m37s")},
	{"Go Ahead", "Alicia Keys", "As I Am", 2007, length("4m36s")},
	{"Ready 2 Go", "Martin Solveig", "Smash", 2011, length("4m24s")},
}

// 将时间字符串转化为time.Duration类型
func length(s string) time.Duration {
	d, err := time.ParseDuration(s)
	if err != nil {
		panic(s)
	}
	return d
}

// 打印排序后的播放列表
func printTracks(tracks []*Track) {
	const format = "%v\t%v\t%v\t%v\t%v\t\n"
	tw := new(tabwriter.Writer).Init(os.Stdout, 0, 8, 2, ' ', 0)
	fmt.Fprintf(tw, format, "Title", "Artist", "Album", "Year", "Length")
	fmt.Fprintf(tw, format, "-----", "------", "-----", "----", "------")
	for _, t := range tracks {
		fmt.Fprintf(tw, format, t.Title, t.Artist, t.Album, t.Year, t.Length)
	}
	tw.Flush()
}

// 按照艺术家排序
type byArtist []*Track

// 播放列表长度
func (x byArtist) Len() int {
	return len(x)
}

// 艺术家字符串比较
func (x byArtist) Less(i, j int) bool {
	return x[i].Artist < x[j].Artist
}

// 交换两个艺术家元素
func (x byArtist) Swap(i, j int) {
	x[i], x[j] = x[j], x[i]
}

// 按照排序的三种规则排序
func Sort() {
	sort.Sort(byArtist(tracks))
	printTracks(tracks)
}

// 逆序排序
func Reverse() {
	sort.Sort(sort.Reverse(byArtist(tracks)))
	printTracks(tracks)
}
