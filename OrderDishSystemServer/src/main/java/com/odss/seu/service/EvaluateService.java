package com.odss.seu.service;

import com.odss.seu.vo.Evaluation;
import com.odss.seu.vo.Order;

//用户提交评价接口
public interface EvaluateService {
    void commitEvaluate(int table, Evaluation evaluation);
}
