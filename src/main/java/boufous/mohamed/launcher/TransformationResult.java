package boufous.mohamed.launcher;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;

public class TransformationResult {
    private String path;
    private String[] headers;
    //private List<Row> data;
    private Dataset<Row> data;
    //private DB db;


    public TransformationResult(String path, String[] headers, Dataset<Row> d) {
        this.path = path;
        this.headers = headers;
        this.data = d;
//        db = dbSingleton.instance.getDb();
//        data = (List<Row>) db.indexTreeList("dataList", Serializer.JAVA).createOrOpen();
//        data.clear();
//        data.addAll(d);
//        System.out.println(data);
//        db.commit();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String[] getHeaders() {
        return headers;
    }

    public Dataset<Row> getData() {
        return data;
    }

    public void setData(Dataset<Row> data) {
        this.data = data;
    }
}
