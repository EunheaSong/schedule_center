package com.seed_planner.schedule_center.common;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        String originalText = logicalName.getText();
        String customText = originalText.substring(0, originalText.length() - 6);
        return super.toPhysicalTableName(new Identifier(customText, logicalName.isQuoted()), jdbcEnvironment);
    }
}