package com.kneem.endlessviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;


public class MainFragment extends Fragment {

    private int focusedPage = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewPager();
    }

    private void initViewPager() {
        ColorPageView blackPage = new ColorPageView(getActivity(), ColorPageView.PageColor.BLACK, 1);
        ColorPageView whitePage = new ColorPageView(getActivity(), ColorPageView.PageColor.WHITE, 2);
        ColorPageView greyPage = new ColorPageView(getActivity(), ColorPageView.PageColor.LIGHT_GREY, 3);


        ColorPageView[] pages = {blackPage, whitePage, greyPage};

        final ViewPager deviceViewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
        ColorPagerAdapter adapter = new ColorPagerAdapter(Arrays.asList(pages));

        deviceViewPager.setAdapter(adapter);

        deviceViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                //Define the focused page before your onCreate()..private int focusedPage = 0;
                focusedPage = position;
            }

            //We don't have to do anything here.
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            private int previousState, currentState;

            @Override
            public void onPageScrollStateChanged(int arg0) {
                int currentPage = deviceViewPager.getCurrentItem();       //ViewPager Type
                if (currentPage == 2 || currentPage == 0){
                    previousState = currentState;
                    currentState = arg0;
                    if (previousState == 1 && currentState == 0){

                        deviceViewPager.setCurrentItem(currentPage == 0 ? 2 : 0, false);

                    }
                }
            }
        });

    }

    private class ColorPagerAdapter extends PagerAdapter {

        private List<ColorPageView> mPages;
        private int mFakeCount = 0;

        public ColorPagerAdapter(List<ColorPageView> pages) {
            this.mPages = pages;
            mFakeCount = mPages.size() + 1;
        }

        @Override
        public int getCount() {
            return mPages.size();
            //return mFakeCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            if (position >= mPages.size()-1) {
//                int newPosition = position%mPages.size();
//
//                position = newPosition;
//                mFakeCount++;
//            }
            container.addView(mPages.get(position));
            return mPages.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
