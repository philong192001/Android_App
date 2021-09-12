package com.example.appcovid.feedback_fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.appcovid.R;
import com.jaiselrahman.hintspinner.HintSpinner;
import com.jaiselrahman.hintspinner.HintSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FeedbackFragment extends Fragment {
    public FeedbackFragment() {
        // Required empty public constructor
    }

    List<String> provinces = new ArrayList<>();
    List<String> districts = new ArrayList<>();
    List<String> blocks = new ArrayList<>();

    HintSpinner provincesSpn;
    HintSpinner districtsSpn;
    HintSpinner blocksSpn;

    TextView txtTime;

    public static FeedbackFragment newInstance() {
        FeedbackFragment fragment = new FeedbackFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSpinners(view);
        initTimeText(view);
    }

    private void initSpinners(View view) {
        provincesSpn = view.findViewById(R.id.spn_province);
        districtsSpn = view.findViewById(R.id.spinner_district);
        blocksSpn = view.findViewById(R.id.spinner_block);

        provincesSpn.setAdapter(new HintSpinnerAdapter<>(requireContext(), provinces, "Chọn Tỉnh/Thành"));
        districtsSpn.setAdapter(new HintSpinnerAdapter<>(requireContext(), districts, "Chọn Quận/Huyện"));
        blocksSpn.setAdapter(new HintSpinnerAdapter<>(requireContext(), blocks, "Chọn Xã/Phường"));
    }

    private void initTimeText(View view){
        txtTime = view.findViewById(R.id.txt_time);
        Calendar now = Calendar.getInstance();
        txtTime.setText(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ", " +
                now.get(Calendar.DAY_OF_MONTH) + "/" + now.get(Calendar.MONTH) + "/" + now.get(Calendar.YEAR));
        txtTime.setOnClickListener(v -> {
            DatePickerDialog dpd = new DatePickerDialog(requireContext(),
                    (DatePickerDialog.OnDateSetListener) (datePicker, i, i1, i2) ->
                            txtTime.setText(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ", " + i2 + "/" + i1 + "/" + i)
                    , now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
            dpd.show();
        });
    }
}


















