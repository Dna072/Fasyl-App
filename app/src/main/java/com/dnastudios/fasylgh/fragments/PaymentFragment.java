package com.dnastudios.fasylgh.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dnastudios.fasylgh.Constants;
import com.dnastudios.fasylgh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    private Button mPayFeeBtn;
    private EditText mAmountEt;
    private TextView mBalanceTv;
    private TextView mCustomerName;
    private TextView mBranchName;

    public PaymentFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        mPayFeeBtn = (Button) v.findViewById(R.id.payBtn);
        mAmountEt = (EditText) v.findViewById(R.id.amount_et);
        mBalanceTv = (TextView) v.findViewById(R.id.balanceTv);
        mCustomerName = (TextView) v.findViewById(R.id.customer_name);
        mBranchName = (TextView) v.findViewById(R.id.branch_name);

        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);
        String custName = preferences.getString(Constants.CUSTOMER_FULL_NAME, "");
        String branch = preferences.getString(Constants.CUSTOMER_BRANCH, "");

        mCustomerName.setText(getString(R.string.customer_name, custName));
        mBranchName.setText(getString(R.string.branch_name, branch));

        mPayFeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payFees();
            }
        });

        //load balance
        getBalance();

        return v;
    }

    private void getBalance() {
        //load balance from url

    }

    private void payFees() {
        String amount = mAmountEt.getText().toString();

        //TODO: send to server and reload balance
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Making payment");
        progress.setIndeterminate(true);
        progress.show();
    }

}
