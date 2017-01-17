package countryinfo.rahul.com.countryinfo.infolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import countryinfo.rahul.com.countryinfo.R;
import countryinfo.rahul.com.countryinfo.detailinfo.DetailInfoActivity;
import countryinfo.rahul.com.countryinfo.model.CountryAPIObject;

/**
 * Created by C0244308 on 13/01/2017.
 */

public class InfoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<CountryAPIObject> mListOfCountries = new ArrayList<>();
    private Context mContext;

    public InfoListAdapter(List<CountryAPIObject> listOfAccounts, Context context)
    {
        mContext = context;
        mListOfCountries = listOfAccounts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)   {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch(viewType)
        {
            case VIEW_TYPES.Header:
                return new InfoListHeader(inflater.inflate(R.layout.info_list_header,parent,false));

            case VIEW_TYPES.Normal:
                return new InfoListViewHolder(inflater.inflate(R.layout.info_list_row,parent,false));

            default:
                return new InfoListViewHolder(inflater.inflate(R.layout.info_list_row,parent,false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return VIEW_TYPES.Header;
        }
        else {
            return VIEW_TYPES.Normal;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InfoListHeader) {
            // Do Nothing
        }
        else if(holder instanceof InfoListViewHolder)
        {
            ((InfoListViewHolder)holder).mItemPosition = position;
            ((InfoListViewHolder)holder).itemView.setTag(holder);

            CountryAPIObject tempCountryData = mListOfCountries.get(position-1);
            ((InfoListViewHolder)holder).mCountryName.setText(tempCountryData.getName());

            setClickListenerForCountries(holder);
        }
    }

    private void setClickListenerForCountries(RecyclerView.ViewHolder holder)
    {
        ((InfoListViewHolder)holder).getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoListViewHolder holder = (InfoListViewHolder) view.getTag();
                Intent intent = new Intent(mContext, DetailInfoActivity.class);
                intent.putExtra("SELECTED_POSITION",holder.mItemPosition-1);
                intent.putExtra("DATA",mListOfCountries.get(holder.mItemPosition-1));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListOfCountries.size() + 1;
    }

    public class InfoListViewHolder extends RecyclerView.ViewHolder
    {
        private View mItemView;
        public TextView mCountryName;
        public int mItemPosition;

        public View getView()
        {
            return mItemView;
        }

        public InfoListViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;

            mCountryName = (TextView) itemView.findViewById(R.id.country_name_tv);
        }
    }

    class InfoListHeader extends RecyclerView.ViewHolder {

        public InfoListHeader(View itemView) {
            super(itemView);
        }
    }

    private class VIEW_TYPES
    {
        public static final int Header = 1;
        public static final int Normal = 2;
    }
}