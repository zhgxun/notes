package com.github.zhgxun.learn.service.second.impl;

import com.github.zhgxun.learn.dao.second.BudgetDao;
import com.github.zhgxun.learn.entity.second.Budget;
import com.github.zhgxun.learn.service.second.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetDao budgetDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Budget findOne(Long id) {
        return budgetDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Budget> findAll() {
        return budgetDao.findAll();
    }
}
