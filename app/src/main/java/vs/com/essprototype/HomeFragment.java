package vs.com.essprototype;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private boolean expanded = false;
    private ImageButton fab ;
    private TextView txt,txt2,txt3;
    private View fabAction1;
    private View fabAction2;
    private View fabAction3;
    private Fragment fragment;
    private float offset1;
    private float offset2;
    private float offset3;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion == Build.VERSION_CODES.LOLLIPOP){

            final ViewGroup fabContainer = (ViewGroup) rootView.findViewById(R.id.fab_container);
            fab = (ImageButton) rootView.findViewById(R.id.fab);
            fabAction1 = rootView.findViewById(R.id.fab_action_1);
            fabAction2 = rootView.findViewById(R.id.fab_action_2);
            fabAction3 = rootView.findViewById(R.id.fab_action_3);
            txt = (TextView) rootView.findViewById(R.id.textView);
            txt2 = (TextView) rootView.findViewById(R.id.textView2);

            txt3 = (TextView) rootView.findViewById(R.id.textView3);


            txt.setVisibility(View.GONE);
            txt2.setVisibility(View.GONE);
            txt3.setVisibility(View.GONE);
           fabAction1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   fragment = new PersonalLeaveFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            fabAction2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = new OnBehalfLeaveFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            fabAction3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = new Supervisorleave();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    expanded = !expanded;
                    if (expanded) {
                        txt.setVisibility(View.VISIBLE);
                        txt2.setVisibility(View.VISIBLE);
                        txt3.setVisibility(View.VISIBLE);
                        expandFab();
                    } else {
                        txt.setVisibility(View.GONE);
                        txt2.setVisibility(View.GONE);
                        txt3.setVisibility(View.GONE);
                        collapseFab();

                    }
                }
            });
            fabContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    fabContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                    offset1 = fab.getY() - fabAction1.getY();
                    fabAction1.setTranslationY(offset1);
                    offset2 = fab.getY() - fabAction2.getY();
                    fabAction2.setTranslationY(offset2);
                    offset3 = fab.getY() - fabAction3.getY();
                    fabAction3.setTranslationY(offset3);
                    return true;
                }
            });
        } else{
            // do something for phones running an SDK before froyo
        }




        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Home");

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void collapseFab() {
        fab.setImageResource(R.drawable.animated_minus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createCollapseAnimator(fabAction1, offset1),
                createCollapseAnimator(fabAction2, offset2),
                createCollapseAnimator(fabAction3, offset3));
        animatorSet.start();
        animateFab();
    }

    private void expandFab() {
        fab.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(fabAction1, offset1),
                createExpandAnimator(fabAction2, offset2),
                createExpandAnimator(fabAction3, offset3));
        animatorSet.start();
        animateFab();
    }

    private static final String TRANSLATION_Y = "translationY";

    private Animator createCollapseAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
                .setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
    }

    private Animator createExpandAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
                .setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
    }

    private void animateFab() {
        Drawable drawable = fab.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        getActivity().setTitle("Home");
    }
}