package tyler.jiqu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tyler.jiqu.R;
import tyler.jiqu.model.ZhihuNewsCommentsModel;
import tyler.jiqu.widget.GlideCircleTransform;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/9  14:23.
 * @描述 ${TODO}.
 */
public class ZhihuNewsCommentsAdapter extends RecyclerView.Adapter<ZhihuNewsCommentsAdapter.ViewHolder> {

    private final Context mContext;
    private List<ZhihuNewsCommentsModel.CommentsBean> mShowItems;

    public ZhihuNewsCommentsAdapter(Context context, List<ZhihuNewsCommentsModel.CommentsBean> datas) {
        mContext = context;
        this.mShowItems = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhihu_news_comments,
                null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZhihuNewsCommentsModel.CommentsBean commentsBean = mShowItems.get(position);
        holder.tvName.setText(commentsBean.getAuthor());
        holder.tvBody.setText(commentsBean.getContent());
        holder.tvCount.setText(commentsBean.getLikes() + "");
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String friendlyTime = format.format(new Date(commentsBean.getTime()));
        holder.tvTime.setText(friendlyTime);
        Glide.with(mContext)
                .load(commentsBean.getAvatar())
                .placeholder(R.mipmap.comment_avatar)
                .transform(new GlideCircleTransform(mContext))
                .error(R.mipmap.comment_avatar)
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return mShowItems == null ? 0 : mShowItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvCount;
        private TextView tvBody;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_item_zhihunews_comment_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_zhihunews_comment_name);
            tvCount = (TextView) itemView.findViewById(R.id.tv_item_zhihunews_comment_count);
            tvBody = (TextView) itemView.findViewById(R.id.tv_item_zhihunews_comment_body);
            tvTime = (TextView) itemView.findViewById(R.id.tv_item_zhihunews_comment_time);
        }
    }
}
