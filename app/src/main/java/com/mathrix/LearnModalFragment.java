package com.mathrix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class LearnModalFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_CONTENT = "content";
    private static final String ARG_SHOW_BACK = "showBackArrow";
    private static final String ARG_IS_FIRST_PART = "isFirstPart";
    private static final String ARG_CATEGORY = "category";

    private boolean isFirstPart;
    private String category;

    public static LearnModalFragment newInstance(String title, String content, boolean showBackArrow, boolean isFirstPart, String category) {
        LearnModalFragment fragment = new LearnModalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CONTENT, content);
        args.putBoolean(ARG_SHOW_BACK, showBackArrow);
        args.putBoolean(ARG_IS_FIRST_PART, isFirstPart);
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_learn, container, false);

        TextView titleView = view.findViewById(R.id.modal_title);
        TextView contentView = view.findViewById(R.id.modal_content);
        ImageView robotImage = view.findViewById(R.id.modal_robot_image);
        Button actionButton = view.findViewById(R.id.modal_button);

        Bundle args = getArguments();
        if (args != null) {
            titleView.setText(args.getString(ARG_TITLE));
            contentView.setText(args.getString(ARG_CONTENT));
            isFirstPart = args.getBoolean(ARG_IS_FIRST_PART, true);
            category = args.getString(ARG_CATEGORY);

            if ("matriz".equals(category)) {
                robotImage.setVisibility(View.VISIBLE);
                actionButton.setText(getString(R.string.lets_see_examples));
            } else if (isFirstPart) {
                robotImage.setVisibility(View.GONE);
                actionButton.setText(getString(R.string.next));
            } else {
                robotImage.setVisibility(View.GONE);
                actionButton.setText(getString(R.string.ok));
            }
        }

        actionButton.setOnClickListener(v -> {
            if (isFirstPart) {
                showSecondPart();
            } else {
                dismiss();
            }
        });

        return view;
    }

    private void showSecondPart() {
        if (getFragmentManager() != null && getContext() != null) {
            String part2Content = getContext().getString(
                    getResources().getIdentifier(category + "_content_second_part", "string", getContext().getPackageName())
            );

            LearnModalFragment secondModal = LearnModalFragment.newInstance(
                    getArguments().getString(ARG_TITLE),
                    part2Content,
                    true,
                    false,
                    category
            );
            secondModal.show(getFragmentManager(), "LearnModalPart2");
            dismiss();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View shadowView = view.findViewById(R.id.modal_shadow);
        View modalContainer = view.findViewById(R.id.modal_container);

        shadowView.setOnClickListener(v -> dismiss());
        modalContainer.setOnClickListener(v -> {
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
