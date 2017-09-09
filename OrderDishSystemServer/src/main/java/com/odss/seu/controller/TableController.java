package com.odss.seu.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.odss.seu.service.ArrangeTableService;
import com.odss.seu.vo.Table;
import com.odss.seu.vo.ViewLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping(value = "/table")
public class TableController {

    private ArrangeTableService arrangeTableService;

    @Autowired
    public TableController(ArrangeTableService arrangeTableService) {
        this.arrangeTableService = arrangeTableService;
    }

    //服务员引导用户入座后，修改桌子状态为已满。
    @RequestMapping(value = "/{tableId}", method = RequestMethod.POST)
    public void useTable(@PathVariable Integer tableId) {
        arrangeTableService.recordTable(tableId);
    }

    //顾客餐毕离开后，修改桌子状态为空。
    @RequestMapping(value = "/{tableId}", method = RequestMethod.DELETE)
    public void emptyTable(@PathVariable Integer tableId) {
        arrangeTableService.emptyTable(tableId);
    }

    //服务员查询所有桌子状态列表。
    @RequestMapping(method = RequestMethod.GET)
    @JsonView(ViewLevel.Summary.class)
    public List<Table> queryTableState() {
        return arrangeTableService.queryAllTables();
    }
}
