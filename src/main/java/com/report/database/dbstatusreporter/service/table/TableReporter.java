package com.report.database.dbstatusreporter.service.table;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableReporter {

    // report emtpy table

    List getEmptyTables();

    List getTablesRowCount();

    List getAllTableNames();

    List getLastUpdateTimeTables();

}
