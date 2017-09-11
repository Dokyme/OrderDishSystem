package com.odss.seu.controller;


import com.odss.seu.service.EvaluateService;
import com.odss.seu.vo.Evaluation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value="/evaluation")
public class EvaluateController {
    private EvaluateService evaluateService;

    @RequestMapping(value = "/{orderId}",method =RequestMethod.GET)
    public void updateEvaluate(@RequestBody Evaluation evaluation, @PathVariable Integer orderId) {
        evaluateService.commitEvaluate(orderId, evaluation);
    }

}
