package boufous.mohamed.launcher;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class CSVExtractor extends Extractor{

    public CSVExtractor(String sourceFilePath) {
        super(sourceFilePath);
        df = sparkSession.read().format("csv")
                .option("sep", ";")
                .option("inferSchema", "true")
                .option("header", "true")
                .load(sourceFilePath);

    }


    @Override
    Dataset<Row> getTransformedData(String SQLQuery){
        df.createOrReplaceTempView("table");
        SQLQuery = SQLQuery.substring(0, SQLQuery.length()-1);
        System.out.println(SQLQuery);
        Dataset<Row> sqlDF = sparkSession.sql(SQLQuery + " From table");
        //sqlDF.printSchema();
        return sqlDF;
    }
}
