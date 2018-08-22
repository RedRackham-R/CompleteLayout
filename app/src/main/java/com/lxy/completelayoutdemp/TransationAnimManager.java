package com.lxy.completelayoutdemp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.view.View;

/**
 * item添加消失的动画
 */
public class TransationAnimManager {
    private long DURATION = 200;


    private static final class Handler {
        private static final TransationAnimManager INSTANCE = new TransationAnimManager();
    }


    public static TransationAnimManager getInstance() {
        return Handler.INSTANCE;
    }


    private TransationAnimManager() {
    }

    public LayoutTransition creatDefualLayoutTransition() {
        LayoutTransition transition = new LayoutTransition();

        /**
         * view 动画改变时，布局中的每个子view动画的时间间隔
         */
        transition.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
        transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);

        transition.setAnimator(LayoutTransition.APPEARING, creatDefualAppearingAnimator());//view出现
        transition.setAnimator(LayoutTransition.DISAPPEARING, creatDefualDisappearingAnimator());//view消失


        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0, 1);
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 1);
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0, 1);
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 1);

        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, creatDefualChangeAppearingAnimator(pvhLeft,pvhTop,pvhRight,pvhBottom));//view出现 整个布局容器
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, creatDefualChangeDisappearingAnimator(pvhLeft,pvhTop,pvhRight,pvhBottom));//view出现 整个布局容器
        transition.setDuration(DURATION);
        return transition;
    }


    /**
     * 当view出现或者添加的时候，view出现的动画
     *
     * @return
     */
    private ObjectAnimator creatDefualAppearingAnimator() {

        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder alphaPvh = PropertyValuesHolder.ofFloat("alpha",0f,0.5f, 1f);
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder scaleXAnim = PropertyValuesHolder.ofFloat( "scaleX", 0, 1.2f, 1);
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder scaleYAnim = PropertyValuesHolder.ofFloat( "scaleY", 0, 1.2f, 1);

        return ObjectAnimator.ofPropertyValuesHolder(this,alphaPvh,scaleXAnim,scaleYAnim);

    }

    /**
     * 当view消失或者隐藏的时候，view消失的动画
     *
     * @return
     */
    private ObjectAnimator creatDefualDisappearingAnimator() {

        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder alphaPvh = PropertyValuesHolder.ofFloat("alpha",1f, 0.5f,0f);
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder scaleXAnim = PropertyValuesHolder.ofFloat( "scaleX", 1.2f);
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder scalYAnim = PropertyValuesHolder.ofFloat( "scaleY", 1.2f);
        return ObjectAnimator.ofPropertyValuesHolder(this,alphaPvh,scaleXAnim,scalYAnim);
    }


    /**
     * 当添加view导致布局容器改变的时候，整个布局容器的动画
     *
     * @return
     */
    private ObjectAnimator creatDefualChangeAppearingAnimator(PropertyValuesHolder pvhLeft,PropertyValuesHolder pvhTop,PropertyValuesHolder pvhRight,PropertyValuesHolder pvhBottom) {
        /**
         * view出现时，导致整个布局改变的动画
         */
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder changeAppearingAnim = PropertyValuesHolder.ofFloat("alpha", 0F, 1F);
        final ObjectAnimator changeInApp = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, changeAppearingAnim).
                setDuration(DURATION);
        changeInApp.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setAlpha(1.0f);
            }
        });
        return changeInApp;
    }

    /**
     * 当删除或者隐藏view导致布局容器改变的时候，整个布局容器的动画
     *
     * @return
     */
    private ObjectAnimator creatDefualChangeDisappearingAnimator(PropertyValuesHolder pvhLeft,PropertyValuesHolder pvhTop,PropertyValuesHolder pvhRight,PropertyValuesHolder pvhBottom) {
        /**
         * view出现时，导致整个布局改变的动画
         */
        @SuppressLint("ObjectAnimatorBinding")
        PropertyValuesHolder animator3 = PropertyValuesHolder.ofFloat("alpha", 1F, 0F);
        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, animator3).
                setDuration(DURATION);
        changeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setAlpha(1.0f);
            }
        });

        return changeIn;
    }


}
