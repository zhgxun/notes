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

    @Autowired
    private BudgetService budgetService;

    @RequestMapping("/budget/one")
    public Budget findOne(@RequestParam(name = "id") Long id) {
        return budgetService.findOne(id);
    }

    @RequestMapping("/budget/all")
    public List<Budget> findAll() {
        return budgetService.findAll();
    }
}
