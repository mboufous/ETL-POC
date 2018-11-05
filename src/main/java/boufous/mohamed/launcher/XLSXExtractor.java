package boufous.mohamed.launcher;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class XLSXExtractor extends Extractor {


    public XLSXExtractor(String exelFilePath) {
        super(exelFilePath);
        Dataset<Row> df = sparkSession.read().format("com.crealytics.spark.excel")
                .option("inferSchema", "true")
                .option("treatEmptyValuesAsNulls", "false")
                .option("useHeader", "true")
                .load(super.sourceFilePath);
    }

    @Override
    Dataset<Row> getTransformedData(String SQLQuery) {
        return null;
    }
}
