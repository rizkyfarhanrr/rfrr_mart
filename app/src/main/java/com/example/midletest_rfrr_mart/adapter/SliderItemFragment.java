package com.example.midletest_rfrr_mart.adapter;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.example.midletest_rfrr_mart.R;

public class SliderItemFragment extends Fragment {
    private static final String ARG_POSITION = "slider_position";

    @StringRes
    private static final int[] PAGE_TITLE = new int[] {
            R.string.title_PackageTravel,
            R.string.title_PackageDestinationTour,
            R.string.title_PackageHotel,
            R.string.title_PackageGuide
    };

    @StringRes
    private static final int[] PAGE_TEXT = new int[] {
            R.string.travel_text,
            R.string.destination_text,
            R.string.hotel_text,
            R.string.guide_text
    };

    @StringRes
    private static final int[] PAGE_IMAGE = new int[] {
            R.drawable.intro_ic_travel,
            R.drawable.intro_ic_destination,
            R.drawable.intro_ic_hotel,
            R.drawable.intro_ic_guide
    };
    @StringRes
    private static final int[] BG_IMG = new int[] {
            R.drawable.intro_ic_bg_blue,
            R.drawable.intro_ic_bg_green,
            R.drawable.intro_ic_bg_red,
            R.drawable.intro_ic_bg_purple
    };

    private int position;

    public static SliderItemFragment newIntance(int position) {
        SliderItemFragment fragment = new SliderItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null ) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slider_item, container, false);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setBackground(requireActivity().getDrawable(BG_IMG[position]));

        TextView tittle = view.findViewById(R.id.textview_title_intro_1);
        TextView subtittle = view.findViewById(R.id.texview_subtitle_intro_1);
        ImageView imageView = view.findViewById(R.id.imageview_1);

        tittle.setText(PAGE_TITLE[position]);
        subtittle.setText(PAGE_TEXT[position]);
        imageView.setImageResource(PAGE_IMAGE[position]);
    }
}
