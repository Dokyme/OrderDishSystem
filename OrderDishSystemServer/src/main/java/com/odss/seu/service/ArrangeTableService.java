package com.odss.seu.service;

import com.odss.seu.vo.Table;

import java.util.List;

public interface ArrangeTableService {
    void recordTable(Integer tableId);

    List<Table> queryAllTables();

    void emptyTable(Integer tableId);
}
