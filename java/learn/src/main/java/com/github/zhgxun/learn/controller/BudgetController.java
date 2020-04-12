package com.github.zhgxun.learn.controller;

import com.github.zhgxun.learn.entity.second.Budget;
import com.github.zhgxun.learn.service.second.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BudgetController {

    // 1.   @Autowired 自动绑定注入, 但是该种方式后续不是推荐的方式了
    private BudgetService budgetService;

    // 2. 构造器注入, 该种方式推荐但是一个类有多个的时候特别繁琐和凌乱
//    public BudgetController(BudgetService budgetService) {
//        this.budgetService = budgetService;
//    }

    // 3. setter 方式注入, 该种方式也是推荐的做法, 但是要增加很多setter方式
    @Autowired
    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @RequestMapping("/budget/one")
    public Budget findOne(@RequestParam(name = "id") Long id) {
        return budgetService.findOne(id);
    }

    @RequestMapping("/budget/all")
    public List<Budget> findAll() {
        return budgetService.findAll();
    }
}
