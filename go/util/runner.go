// 管理任务的运行和生命周期
package util

import (
	"errors"
	"os"
	"os/signal"
	"time"
)

// 注册任务名称和参数
type Task struct {
	Name    func(...interface{})
	Numbers []int
	Strings []string
}

// 任务管理控制
type runner struct {
	// signal包实现了对输入信号的访问
	// 该通道用于报告从操作系统发送的所有信号
	interrupt chan os.Signal

	// 报告处理任务完成结果
	complete chan error

	// 报告处理任务已经超时
	timeout <-chan time.Time

	// 需要处理的注册任务
	tasks []Task
}

// 任务执行超时错误
var ErrTimeout = errors.New("received timeout")

// 接收到中断信号
var ErrInterrupt = errors.New("received interrupt")

// 生成一个任务控制实例
func New(d time.Duration) *runner {
	return &runner{
		interrupt: make(chan os.Signal, 1),
		complete:  make(chan error),
		timeout:   time.After(d),
	}
}

// 注册任务到任务列表中
// 可以连续注册多个任务
func (r *runner) Add(task ...Task) {
	r.tasks = append(r.tasks, task...)
}

// 管理注册任务
// 监听任务执行完毕和超时的信号
func (r *runner) Start() error {
	// 原型: func Notify(c chan<- os.Signal, sig ...os.Signal)
	// Notify函数让signal包将输入信号转发到c
	// 如果没有列出要传递的信号, 会将所有输入信号传递到c;
	// 否则只传递列出的输入信号
	//
	// signal包不会为了向c发送信息而阻塞(就是说如果发送时c阻塞了, signal包会直接放弃)
	// 调用者应该保证c有足够的缓存空间可以跟上期望的信号频率
	// 对使用单一信号用于通知的通道, 缓存为1就足够了
	//
	// 可以使用同一通道多次调用Notify
	// 每一次都会扩展该通道接收的信号集
	// 唯一从信号集去除信号的方法是调用Stop
	// 可以使用同一信号和不同通道多次调用Notify; 每一个通道都会独立接收到该信号的一个拷贝
	signal.Notify(r.interrupt, os.Interrupt)

	// 异步执行每一个注册的任务
	go func() {
		r.complete <- r.run()
	}()

	// 接收任务完成和超时信号
	select {
	case err := <-r.complete:
		return err
	case <-r.timeout:
		return ErrTimeout
	}
}

// 执行注册任务
func (r *runner) run() error {
	for _, task := range r.tasks {
		if r.goInterrupt() {
			return ErrInterrupt
		}
		task.Name(task.Numbers, task.Strings)
	}

	return nil
}

// 接收中断信号
// 一般来说, select 语句在没有任何要接收的数据时会阻塞
// 默认的 default 分支将其转化为非阻塞模式
func (r *runner) goInterrupt() bool {
	select {
	// 接收到中断信号后, 停止继续接收信号
	case <-r.interrupt:
		signal.Stop(r.interrupt)
		return true
	default:
		return false
	}
}
