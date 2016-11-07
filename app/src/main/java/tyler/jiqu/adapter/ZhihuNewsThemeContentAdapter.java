package tyler.jiqu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tyler.jiqu.R;
import tyler.jiqu.model.ZhihuNewsThemeContentModel;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/7  15:36.
 * @描述 ${TODO}.
 */
public class ZhihuNewsThemeContentAdapter extends RecyclerView.Adapter<ZhihuNewsThemeContentAdapter
        .ViewHolder> {

    private final List<ZhihuNewsThemeContentModel.StoriesBean> mShowItems;
    private final Context mContext;

    public ZhihuNewsThemeContentAdapter(List<ZhihuNewsThemeContentModel.StoriesBean> datas, Context context) {
        this.mShowItems = datas;
        this.mContext = context;
    }

    @Override
    public ZhihuNewsThemeContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_zhihu_news_content, parent, false));
    }

    @Override
    public void onBindViewHolder(ZhihuNewsThemeContentAdapter.ViewHolder holder, int position) {
        ZhihuNewsThemeContentModel.StoriesBean storiesBean = mShowItems.get(position);
        holder.tv.setText(storiesBean.getTitle());
        List<String> images = storiesBean.getImages();
        if (images != null && images.size() > 0) {
            Glide.with(mContext)
                    .load(images.get(0))
                    .into(holder.iv);
        }else {
            holder.rl.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mShowItems == null ? 0 : mShowItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;
        RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item_zhihunews_content);
            iv = (ImageView) itemView.findViewById(R.id.iv_item_zhihunews_content);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_item_zhihunews_content_images);
        }
    }

}
