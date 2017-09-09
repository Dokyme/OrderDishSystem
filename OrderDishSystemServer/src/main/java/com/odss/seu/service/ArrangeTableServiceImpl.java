package com.odss.seu.service;

import com.odss.seu.mapper.TableMapper;
import com.odss.seu.vo.Table;
import com.odss.seu.vo.TableExample;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrangeTableServiceImpl implements ArrangeTableService {

    private TableMapper tableMapper;

    @Autowired
    public ArrangeTableServiceImpl(TableMapper tableMapper) {
        this.tableMapper = tableMapper;
    }

    @Override
    public List<Table> queryAllTables() {
        return tableMapper.selectByExample(new TableExample());
    }

    //服务员设置该餐桌已经有人，state为1。
    @Override
    public void recordTable(Integer tableId) {
        Table table = tableMapper.selectByPrimaryKey(tableId);
        table.setState(TableState.FULLED.ordinal());
        tableMapper.updateByPrimaryKey(table);
    }

    @Override
    public void emptyTable(Integer tableId) {
        Table table = tableMapper.selectByPrimaryKey(tableId);
        table.setState(TableState.EMPTY.ordinal());
        tableMapper.updateByPrimaryKey(table);
    }
}
