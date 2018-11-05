package boufous.mohamed.launcher;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSingleton {

    static SparkSingleton instance = null;
    static{
        if(instance == null){
            instance = new SparkSingleton();
        }
    }

    public SparkSingleton() {

    }



    public SparkSession initSpark(){
        //  --  Reduce Logging
        Logger.getLogger("org").setLevel(Level.ERROR);
        Logger.getLogger("akka").setLevel(Level.ERROR);

        // -- Init spark session
        SparkSession spark = SparkSession
                .builder()
                .appName("A1")
                .master("local[2]")
                //.config("spark.io.compression.codec", "snappy")
                .getOrCreate();
        return spark;
    }

}