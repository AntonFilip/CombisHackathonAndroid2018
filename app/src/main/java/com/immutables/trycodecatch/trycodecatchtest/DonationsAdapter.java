package com.immutables.trycodecatch.trycodecatchtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.DonationResponseData;

import java.util.ArrayList;

public class DonationsAdapter extends RecyclerView.Adapter<DonationsAdapter.ViewHolder> {
    private ArrayList<DonationResponseData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mDonationTV;
        public TextView mDescriptionTV;
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            mDonationTV = v.findViewById(R.id.donationTimestampView);
            mDescriptionTV = v.findViewById(R.id.donationDescriptionView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DonationsAdapter(ArrayList<DonationResponseData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DonationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_holder, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mDonationTV.setText("Donation: " + ((DonationResponseData)mDataset.get(position)).id);
        holder.mDescriptionTV.setText("Description: " + ((DonationResponseData)mDataset.get(position)).description);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationResponseData data = mDataset.get(position);
                
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}