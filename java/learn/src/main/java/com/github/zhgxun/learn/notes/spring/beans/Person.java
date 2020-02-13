package com.github.zhgxun.learn.notes.spring.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long userId;
    private String name;
    private Integer age;
    private Double amount;
}
