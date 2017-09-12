package com.odss.seu.controller;


import com.odss.seu.service.EvaluateService;
import com.odss.seu.vo.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/evaluation")
public class EvaluateController {
    private EvaluateService evaluateService;

    @Autowired
    public EvaluateController(EvaluateService evaluateService){
        this.evaluateService = evaluateService;
    }

    @RequestMapping(value = "/{orderId}",method =RequestMethod.PUT)
    public void updateEvaluate(@PathVariable Integer orderId, @RequestBody Evaluation evaluation) {
        evaluateService.commitEvaluate(orderId, evaluation);
    }

}
