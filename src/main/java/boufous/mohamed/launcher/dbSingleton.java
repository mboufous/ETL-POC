package boufous.mohamed.launcher;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class dbSingleton {

    public static dbSingleton instance;
    private DB db;

    static {
        instance = new dbSingleton();
    }

    public dbSingleton() {
        db = DBMaker
                .fileDB("res.db")
                .closeOnJvmShutdown()
                .checksumHeaderBypass()
                .fileDeleteAfterClose()
                .make();
    }

    public DB getDb() {
        return db;
    }
}
