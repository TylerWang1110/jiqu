package tyler.jiqu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/5  20:08.
 * @描述 ${TODO}.
 */
public class ZhihuNewsDaoHelper extends OrmLiteSqliteOpenHelper {

    public ZhihuNewsDaoHelper(Context context) {
        super(context, "ZhihuNewsReaded.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ZhihuNewsReaded.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ZhihuNewsReaded.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ZhihuNewsDaoHelper instance;

    public static synchronized ZhihuNewsDaoHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (ZhihuNewsDaoHelper.class) {
                if (instance == null) {
                    instance = new ZhihuNewsDaoHelper(context);
                }
            }
        }
        return instance;
    }

    private Dao<ZhihuNewsReaded, Integer> dao;

    // 获取操作数据库的DAO
    public Dao<ZhihuNewsReaded, Integer> getDao() throws SQLException {
        if (dao == null) {
            dao = getDao(ZhihuNewsReaded.class);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        dao = null;
    }
}
