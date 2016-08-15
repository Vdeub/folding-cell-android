package com.ramotion.foldingcell.examples.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.ramotion.foldingcell.examples.R;
import com.ramotion.foldingcell.examples.model.TvShow;

import java.util.List;

public class TvShowListAdapter extends BaseAdapter {

    protected int _layout;
    protected List<TvShow> _data;
    protected static LayoutInflater _inflater = null;
    protected ImageLoader _imageLoader;
    protected Drawable _loadingBanner;
    protected Drawable _defaultBanner;

    public TvShowListAdapter(Context context, List<TvShow> data) {
        _data = data;
        _inflater = LayoutInflater.from(context);
        _imageLoader = ImageLoader.getInstance();

    }

    @Override
    public int getCount()
    {
        if (_data != null)
            return _data.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position)
    {
        if (_data != null)
            return _data.get(position);
        else
            return 0;
    }

    @Override
    public long getItemId(int position)
    {
        if (_data != null && position > 0 && position <= _data.size())
            //return Long.valueOf(_data.get(position-1).getId()); // minus 1 for header row
            return Long.valueOf(_data.get(position).getId());
        else
            return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        return convertView;
    }
}
