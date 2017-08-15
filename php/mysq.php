<?php
1. 建表原则
定长与变长分离
每一单元值占得字符是固定的
核心且常用的字段，直接建成定长放在一张表中

常用字段和不常用字段相互分离

在1对多，需要关联的地方增加冗余字段
空间换时间、时间换空间

2. 列选择原则
字段类型优先级
整形>date_time>enum，char>varchar>text
考虑字符集和校对集（排序规则）

够用就行，不要慷慨

尽量避免使用NULL

Enum类型

3. 索引优化
btree索引
hash索引

索引是一种高效查询的数据结构。索引提高查询，排序，分组速度。

myisam
frm
myd
myi

memory表使用hash索引，查询速度更快，理论复杂度O(1)

hash计算的结果是随机的，地址有冲突，拉链算法，磁盘会有很多空洞，无法对范围查询进行优化
无法利用前缀索引，排序也无法优化

独立的索引，同时只能用一个
实际使用中，更多的是联合索引

多列索引如何发挥作用
a-b-c
where a=3
where a=3 and b=4
where a=3 and b=4 and c=5
必须按顺序使用，否则不能使用索引
左前缀原则

聚族索引和非聚簇索引
myisam 非聚族索引 一次索引和次级索引都指向磁盘上的位置
innodb 聚族索引 树上保存信息，不需要回行，即根据地址在此查找数据，次级索引指向对主键的引用，但是不规则数据会造成页分裂。

索引覆盖
如果索引已经存在，则不需要回行，否则需要回行。






