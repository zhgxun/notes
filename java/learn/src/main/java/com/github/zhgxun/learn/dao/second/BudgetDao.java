package com.github.zhgxun.learn.dao.second;

import com.github.zhgxun.learn.entity.second.Budget;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BudgetDao {

    Budget findOne(@Param("id") Long id);

    List<Budget> findAll();
}
