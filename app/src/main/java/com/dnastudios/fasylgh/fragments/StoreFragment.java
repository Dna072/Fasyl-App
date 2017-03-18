package com.dnastudios.fasylgh.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dnastudios.fasylgh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {


    private Button mAirtimePayButton;
    private Button mBundlePayButton;

    private EditText mAirtimeEt;
    private EditText mBundleEt;

    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_store, container, false);

        mAirtimePayButton = (Button) v.findViewById(R.id.airtimePayBtn);
        mBundlePayButton = (Button) v.findViewById(R.id.bundlePayBtn);

        mBundleEt = (EditText) v.findViewById(R.id.bundle_amount_et);
        mAirtimeEt = (EditText) v.findViewById(R.id.airtime_amount_et);

        mAirtimePayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAirTimePayment();
            }
        });

        mBundlePayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeBundlePayment();
            }
        });

        return v;
    }

    private void makeBundlePayment() {
        showDialog();
    }



    private void makeAirTimePayment() {
        showDialog();
    }

    private void showDialog() {
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Making payment");
        progress.setIndeterminate(true);
        progress.show();
    }




}
