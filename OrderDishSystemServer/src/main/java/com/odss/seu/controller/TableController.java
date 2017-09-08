package com.odss.seu.controller;


import com.odss.seu.vo.Table;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/table")
public class TableController {

    @RequestMapping(method= RequestMethod.POST)
    public void changeTableState ()
    {
        return;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Table> queryTableState()
    {
        return new ArrayList<>();
    }
}
