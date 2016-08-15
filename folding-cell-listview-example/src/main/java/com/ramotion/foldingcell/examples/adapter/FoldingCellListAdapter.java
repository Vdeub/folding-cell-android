package com.ramotion.foldingcell.examples.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.ramotion.foldingcell.FoldingCell;
import com.ramotion.foldingcell.examples.R;
import com.ramotion.foldingcell.examples.listview.Item;

import java.util.HashSet;
import java.util.List;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    protected ImageLoader _imageLoader;
    protected Drawable _loadingBanner;
    protected Drawable _defaultBanner;


    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
        _imageLoader = ImageLoader.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to viewHolder
            viewHolder.tvShowName = (TextView) convertView.findViewById(R.id.tv_show_name);
            viewHolder.tvShowBanner = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.tvShowBannerLoading = (ProgressBar) convertView.findViewById((R.id.progress));
            viewHolder.tvShowNetwork = (TextView) convertView.findViewById(R.id.tv_show_network);
            viewHolder.tvShowNetworkDivider = convertView.findViewById(R.id.tv_show_network_divider);
            viewHolder.tvShowDescription = (TextView) convertView.findViewById(R.id.tv_show_description);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }


        // bind data from selected element to view through viewHolder
        viewHolder.tvShowId.setText(item.getId());
        viewHolder.tvShowName.setText(item.getName());

        String network = item.getNetwork();
        if (network != null && !network.equals((""))) {
            viewHolder.tvShowNetwork.setText(network);
            viewHolder.tvShowNetwork.setVisibility(View.VISIBLE);
            viewHolder.tvShowNetworkDivider.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvShowNetwork.setVisibility(View.GONE);
            viewHolder.tvShowNetworkDivider.setVisibility(View.GONE);
        }

        viewHolder.tvShowDescription.setText(item.getDescription());


        String banner = item.getBanner();

        if (banner != null && !banner.equals("")) {
            String urlBase = "http://thetvdb.com/banners/";
            viewHolder.tvShowBannerLoading.setVisibility((View.VISIBLE));
            //viewHolder.tvShowBanner.setVisibility((View.INVISIBLE));
            viewHolder.tvShowBanner.setImageDrawable(_loadingBanner);
            final ProgressBar progress = viewHolder.tvShowBannerLoading;

            _imageLoader.displayImage(urlBase + banner, viewHolder.tvShowBanner, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    AlphaAnimation a = new AlphaAnimation(0.0f, 1.0f);
                    a.setDuration(400);
                    view.setAnimation(a);
                    progress.setVisibility(View.GONE);
                }
            });
        } else {
            viewHolder.tvShowBanner.setImageDrawable(_defaultBanner);
            viewHolder.tvShowBannerLoading.setVisibility(View.GONE);
        }



        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvShowId;
        TextView tvShowName;
        ImageView tvShowBanner;
        ProgressBar tvShowBannerLoading;
        TextView tvShowNetwork;
        View tvShowNetworkDivider;
        TextView tvShowDescription;
    }
}
