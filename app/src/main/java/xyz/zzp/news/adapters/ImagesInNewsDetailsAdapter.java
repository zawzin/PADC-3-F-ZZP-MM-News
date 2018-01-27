package xyz.zzp.news.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.zzp.news.R;
import xyz.zzp.news.viewholders.ItemNewsViewHolder;
import xyz.zzp.news.viewitems.ImageInNewsDetailsViewItem;

/**
 * Created by Lenovo on 12/10/2017.
 */

public class ImagesInNewsDetailsAdapter extends PagerAdapter {

    private List<String> mImages;

    public ImagesInNewsDetailsAdapter() {
        mImages = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {//check itemview is correced with want
//        if (object instanceof View)
//            return true;
//        else
//            return false;
//        return (object instanceof View);
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//itemview instantiatiItem in ViewAdapter
        Context context = container.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ImageInNewsDetailsViewItem view =(ImageInNewsDetailsViewItem) layoutInflater.inflate(R.layout.items_news_details_images,container,false);
        view.setData(mImages.get(position));


        container.addView(view); // must write in using ViewPager
        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
