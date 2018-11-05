package boufous.mohamed.launcher;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public abstract class Extractor {

    String sourceFilePath;
    SparkSession sparkSession;
    Dataset<Row> df;

    public Extractor() {
        sparkSession = SparkSingleton.instance.initSpark();

    }

    public Extractor(String sourceFilePath) {
        this();
        this.sourceFilePath = sourceFilePath;

    }

    abstract Dataset<Row> getTransformedData(String SQLQuery);
}
