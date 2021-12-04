package com.huldarchen;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huldarchen.enums.SqlTypeEnum;
import com.huldarchen.model.lineage.FieldLineageModel;
import com.huldarchen.model.lineage.TableLineageModel;
import com.huldarchen.model.metadata.TableMetadataModel;

/**
 * @ClassName SqlParserAbstractFactory
 * @description:
 * @author: again
 * @Date: 2021/3/10 8:14 下午
 */
public abstract class SqlParserAbstract implements SqlParserService {

    public static final Logger logger = LoggerFactory.getLogger(SqlParserAbstract.class);

    private void notSupport(String msg) {
        logger.error("not support {}", msg);
//        throw new RuntimeException("not support");
    }

    @Override
    public SqlTypeEnum parseSqlType(String sql) {
        notSupport("parseSqlType");
        return null;
    }

    @Override
    public TableMetadataModel parseSqlMetadata(String sql) {
        notSupport("parseSqlMetadata");
        return null;
    }

    @Override
    public String parseSqlFormatter(String sql) {
        notSupport("parseSqlFormatter");
        return null;
    }

    @Override
    public TableLineageModel parseSqlTableLineage(String sql) {
        notSupport("parseSqlTableLineage");
        return null;
    }

    @Override
    public List<FieldLineageModel> parseSqlFieldLineage(String sql) {
        notSupport("parseSqlFieldLineage");
        return null;
    }
}
