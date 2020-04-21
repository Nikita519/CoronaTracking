package com.shabalin.coronatracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.shabalin.coronatracking.data.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private SortedList<Country> mData;
    Context mContext;

    public CountryAdapter(List<Country> list, Context mContext) {
        this.mContext = mContext;
        mData = new SortedList<>(Country.class, new SortedList.Callback<Country>() {
            @Override
            public int compare(Country o1, Country o2) {

                return o2.getTotalConfirmed() - o1.getTotalConfirmed();
            }

            @Override
            public void onChanged(int position, int count) {

                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Country oldItem, Country newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Country item1, Country item2) {
                return item1.getCountry() == item2.getCountry();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
        mData.addAll(list);


    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind((Country) mData.get(position));
        holder.textViewTotalConfirmedCountry.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_anim));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        Country country;

        TextView textViewTitleCountry;
        TextView textViewTotalConfirmedCountry;
        CardView card;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            card= itemView.findViewById(R.id.cardItem);
            textViewTitleCountry = itemView.findViewById(R.id.textViewTitleCountry);
            textViewTotalConfirmedCountry = itemView.findViewById(R.id.textViewTotalConfirmedCountry);
        }

        public void bind(Country country){
            this.country = country;

            textViewTitleCountry.setText(country.getCountry());
            textViewTotalConfirmedCountry.setText(String.valueOf(country.getTotalConfirmed()));
        }
    }
}
