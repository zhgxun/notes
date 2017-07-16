package main

import (
	"container/list"
)

func isValid(s string) bool {
	// 成对出现, 必为2的倍数
	if len(s)%2 != 0 {
		return false
	}
	// 双向链表
	var l list.List
	// 每一个符号之后必须是该符号的关闭符号
	for _, c := range s {
		switch c {
		case '(':
			// 将一个新元素插入链表的最后一个位置, 返回生成的新元素
			l.PushBack('(')
		case '{':
			l.PushBack('{')
		case '[':
			l.PushBack('[')
		case '}':
			if l.Len() == 0 {
				return false
			} else if l.Back().Value != '{' {
				return false
			} else {
				l.Remove(l.Back())
			}
		case ')':
			if l.Len() == 0 {
				return false
			} else if l.Back().Value != '(' {
				return false
			} else {
				l.Remove(l.Back())
			}
		case ']':
			if l.Len() == 0 {
				return false
			} else if l.Back().Value != '[' {
				return false
			} else {
				l.Remove(l.Back())
			}
		}
	}
	return l.Len() == 0
}
