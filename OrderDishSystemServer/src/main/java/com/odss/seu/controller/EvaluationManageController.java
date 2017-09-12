package com.odss.seu.controller;


import com.odss.seu.service.EvaluateService;
import com.odss.seu.vo.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//api checked
@Controller
@RequestMapping(value = "/evaluation")
public class EvaluationManageController {

    private EvaluateService evaluateService;

    @Autowired
    public EvaluationManageController(EvaluateService evaluateService) {
        this.evaluateService = evaluateService;
    }

    @RequestMapping(value = "/{orderId}",method = RequestMethod.PUT)
    public void updateEvaluate(@RequestBody Evaluation evaluation, @PathVariable Integer orderId) {
        evaluateService.commitEvaluate(orderId, evaluation);
    }

}
