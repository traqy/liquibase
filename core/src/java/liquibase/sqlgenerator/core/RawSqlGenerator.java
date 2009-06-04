package liquibase.sqlgenerator.core;

import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.statement.RawSqlStatement;
import liquibase.sqlgenerator.SqlGenerator;

public class RawSqlGenerator implements SqlGenerator<RawSqlStatement> {
    public int getPriority() {
        return PRIORITY_DEFAULT;
    }

    public boolean supports(RawSqlStatement statement, Database database) {
        return true;
    }

    public ValidationErrors validate(RawSqlStatement rawSqlStatement, Database database) {
        ValidationErrors validationErrors = new ValidationErrors();
        validationErrors.checkRequiredField("sql", rawSqlStatement.getSql());
        return validationErrors;
    }

    public Sql[] generateSql(RawSqlStatement statement, Database database) {
        return new Sql[] {
           new UnparsedSql(statement.getSql(), statement.getEndDelimiter())     
        };
    }
}