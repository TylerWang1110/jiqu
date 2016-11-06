package tyler.jiqu.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/5  20:00.
 * @描述 ${ZhihuNewsReaded }.
 */
@DatabaseTable(tableName = "ZhihuNewsReaded")
public class ZhihuNewsReaded {

    //定义id，
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "newsId")
    private int newsId;

    @DatabaseField(columnName = "isReaded")
    private int isReaded;

    public static final int ISREADED_TRUE = 1;
    public static final int ISREADED_FALSE = 0;

    //无参数构造函数必须要，否则会报错
    public ZhihuNewsReaded() {

    }

    public ZhihuNewsReaded(int newsId, int isReaded) {
        this.newsId = newsId;
        this.isReaded = isReaded;
    }
}
