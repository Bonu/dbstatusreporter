package com.report.database.dbstatusreporter.domain;


import lombok.Getter;
import lombok.Setter;

public class Tables {

    @Setter @Getter String schemaname;
    @Setter @Getter String tablename;
    @Setter @Getter String tableowner;
    @Setter @Getter String tablespace;
    @Setter @Getter String hasindexes;
    @Setter @Getter String hasrules;
    @Setter @Getter String hastriggers;

}
