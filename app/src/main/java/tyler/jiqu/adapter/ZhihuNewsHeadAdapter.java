package tyler.jiqu.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tyler.jiqu.R;
import tyler.jiqu.model.ZhihuNewsModel;

/**
 * @创建者 Tyler.
 * @创建时间 2016/11/4  0:16.
 * @描述 ${TODO}.
 */
public class ZhihuNewsHeadAdapter extends PagerAdapter {

    List<ZhihuNewsModel.TopStoriesBean> mShowData;

    public ZhihuNewsHeadAdapter(List<ZhihuNewsModel.TopStoriesBean> list) {
        this.mShowData = list;
    }

    @Override
    public int getCount() {
        return mShowData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_zhihu_news_head, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_zhihunews_head);
        TextView tv = (TextView) view.findViewById(R.id.tv_zhihunews_head);
        Glide.with(container.getContext())
                .load(mShowData.get(position).getImage())
                .into(iv);
        tv.setText(mShowData.get(position).getTitle());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
