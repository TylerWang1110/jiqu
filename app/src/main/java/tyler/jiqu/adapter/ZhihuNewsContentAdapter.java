package tyler.jiqu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tyler.jiqu.R;
import tyler.jiqu.db.ZhihuNewsDaoHelper;
import tyler.jiqu.db.ZhihuNewsReaded;
import tyler.jiqu.model.ZhihuNewsModel;
import tyler.jiqu.util.DateUtil;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/4  21:28.
 * @描述 ${TODO}.
 */
public class ZhihuNewsContentAdapter extends RecyclerView.Adapter<ZhihuNewsContentAdapter.ViewHolder> {

    private List<ZhihuNewsModel.StoriesBean> mShowDatas;
    private Context mContext;
    private String mNewsDate;
    private Dao<ZhihuNewsReaded, Integer> mDao;
    private ZhihuNewsModel.StoriesBean mStoriesBean;
    private MyItemClicklistener mMyItemClicklistener;
    private static final String TAG = "ZhihuNewsContentAdapter";

    public interface MyItemClicklistener {
        public void onItemClick(View v, int position);
    }

    public ZhihuNewsContentAdapter(List<ZhihuNewsModel.StoriesBean> datas, String newsDate, Context context) {
        this.mShowDatas = datas;
        this.mContext = context;
        mNewsDate = newsDate;
        ZhihuNewsDaoHelper zhihuNewsDaoHelper = new ZhihuNewsDaoHelper(mContext);
        try {
            mDao = zhihuNewsDaoHelper.getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ZhihuNewsContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_zhihu_news_content, parent, false));

    }

    @Override
    public void onBindViewHolder(ZhihuNewsContentAdapter.ViewHolder holder, int position) {
        mStoriesBean = mShowDatas.get(position);

        QueryBuilder<ZhihuNewsReaded, Integer> builder = mDao.queryBuilder();
        // mDao.queryBuilder().where().eq("id", 1).or().eq("id", 2).query();
        try {
            List<ZhihuNewsReaded> list = builder.where().eq("newsId", mStoriesBean.getId())
                    .and().eq("isReaded", ZhihuNewsReaded.ISREADED_TRUE).query();
            if (list.size() > 0) {
                holder.tvContent.setTextColor(mContext.getResources().getColor(R.color.text_gray));
            } else {
                holder.tvContent.setTextColor(mContext.getResources().getColor(android.R.color.black));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        holder.tvContent.setText(mStoriesBean.getTitle());
        String url = mStoriesBean.getImages().get(0);
        Glide.with(mContext)
                .load(url)
                .into(holder.ivContent);
        holder.ivMore.setVisibility(mStoriesBean.isMultipic() ? View.VISIBLE : View.GONE);

        try {
            Date date = DateUtil.stringToDate(mNewsDate);
            String string = DateUtil.dateToString(date);
            String week = DateUtil.dateToWeek(date);
            //            L.d(TAG, " 有好日期 : ++++++++++++ " + string + week);
            //            L.d(TAG, " 上一天 : ++++++++++++ " + DateUtil.dateToString(DateUtil.getDayBefore
            // (date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());

        holder.tvDate.setText(date.equals(mNewsDate) ? "今日热闻" : date);
        holder.tvDate.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return mShowDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvContent;
        TextView tvDate;
        ImageView ivContent;
        ImageView ivMore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_item_zhihunews_content);
            tvDate = (TextView) itemView.findViewById(R.id.tv_item_zhihunews_content_date);
            ivContent = (ImageView) itemView.findViewById(R.id.iv_item_zhihunews_content);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_item_zhihunews_more);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mMyItemClicklistener != null) {
                mMyItemClicklistener.onItemClick(v, getPosition());
            }
        }
    }

    public void setMyItemClicklistener(MyItemClicklistener myItemClicklistener) {
        mMyItemClicklistener = myItemClicklistener;
    }
}
