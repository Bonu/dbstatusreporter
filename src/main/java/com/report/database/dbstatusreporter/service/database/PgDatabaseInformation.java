package com.report.database.dbstatusreporter.service.database;

public class PgDatabaseInformation implements DatabaseInformation {

    public static final String databaseType = "Postgres";

    @Override
    public String getConnectionString() {
        return null;
    }
}
