package com.github.zhgxun.learn.service.second;

import com.github.zhgxun.learn.entity.second.Budget;

import java.util.List;

public interface BudgetService {

    Budget findOne(Long id);

    List<Budget> findAll();
}
