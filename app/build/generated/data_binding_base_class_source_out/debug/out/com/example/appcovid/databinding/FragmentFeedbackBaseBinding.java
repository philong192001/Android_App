// Generated by view binder compiler. Do not edit!
package com.example.appcovid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.example.appcovid.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFeedbackBaseBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TabLayout feedbackBaseTabs;

  @NonNull
  public final ViewPager2 feedbackBaseViewpager;

  private FragmentFeedbackBaseBinding(@NonNull LinearLayout rootView,
      @NonNull TabLayout feedbackBaseTabs, @NonNull ViewPager2 feedbackBaseViewpager) {
    this.rootView = rootView;
    this.feedbackBaseTabs = feedbackBaseTabs;
    this.feedbackBaseViewpager = feedbackBaseViewpager;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFeedbackBaseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFeedbackBaseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_feedback_base, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFeedbackBaseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.feedback_base_tabs;
      TabLayout feedbackBaseTabs = ViewBindings.findChildViewById(rootView, id);
      if (feedbackBaseTabs == null) {
        break missingId;
      }

      id = R.id.feedback_base_viewpager;
      ViewPager2 feedbackBaseViewpager = ViewBindings.findChildViewById(rootView, id);
      if (feedbackBaseViewpager == null) {
        break missingId;
      }

      return new FragmentFeedbackBaseBinding((LinearLayout) rootView, feedbackBaseTabs,
          feedbackBaseViewpager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
