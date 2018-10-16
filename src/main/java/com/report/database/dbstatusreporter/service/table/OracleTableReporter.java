package com.report.database.dbstatusreporter.service.table;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OracleTableReporter implements TableReporter {
    @Override
    public List getEmptyTables() {
        return null;
    }

    @Override
    public List getTablesRowCount() {
        return null;
    }

    @Override
    public List getAllTableNames() {
        return null;
    }

    @Override
    public List getLastUpdateTimeTables() {
        return null;
    }
}
