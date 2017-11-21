package com.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by  on 16/10/30.
 */
public class AnimationUtils {

    /**
     * 点赞动画
     *
     * @param view
     * @param onAnimationListener
     */
    public static void startLikeAnimation(View view, Animation.AnimationListener onAnimationListener) {
        //加入点赞特效
        AnimationSet animaSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.3f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setRepeatMode(ScaleAnimation.REVERSE);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setFillBefore(true);
        alphaAnimation.startNow();
        animaSet.addAnimation(alphaAnimation);

        ScaleAnimation scaleAnimationBig = new ScaleAnimation(
                1f, 1.3f, 1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimationBig.setDuration(500);
        scaleAnimationBig.setRepeatMode(ScaleAnimation.REVERSE);
        scaleAnimationBig.setFillBefore(true);
        scaleAnimationBig.setRepeatCount(1);
        scaleAnimationBig.startNow();
        animaSet.addAnimation(scaleAnimationBig);

        if (onAnimationListener != null) {
            animaSet.setAnimationListener(onAnimationListener);
        }
        view.startAnimation(animaSet);
    }


    public static void riseAndFall(final View view) {
        float firstloc = view.getHeight();
        float riselen = view.getHeight() * 0.2f;
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator rise = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY() + firstloc, view.getTranslationY() - riselen).setDuration(500);
        ObjectAnimator fall = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationY(), view.getTranslationY() + riselen).setDuration(100);
        set.play(rise);
        set.play(fall).after(rise);

    }

    public static void startRise(final View view, long riseTime, long fallTime, long startOffset) {
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation readyAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.5f, TranslateAnimation.RELATIVE_TO_SELF, -0.1f);
        readyAnim.setDuration(riseTime);
        readyAnim.setStartOffset(startOffset);
        set.addAnimation(readyAnim);
        TranslateAnimation outAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_PARENT, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0.1f);
        outAnim.setDuration(fallTime);
        outAnim.setStartOffset(riseTime + startOffset);
        set.addAnimation(outAnim);
        view.startAnimation(set);
    }


    public static Animation getRiseAnimation(long riseTime, long fallTime, long startOffset, float readyValue, float outValue) {
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation readyAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f, TranslateAnimation.RELATIVE_TO_PARENT, readyValue);
        readyAnim.setDuration(riseTime);
        readyAnim.setStartOffset(startOffset);
        set.addAnimation(readyAnim);
        TranslateAnimation outAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_PARENT, 0, TranslateAnimation.RELATIVE_TO_PARENT, outValue);
        outAnim.setDuration(fallTime);
        outAnim.setStartOffset(riseTime + startOffset);
        set.addAnimation(outAnim);
        return set;
    }

    public static Animation getFallAnimation(long fallTime, long startOffset, float outValue, boolean fillAfter) {
        TranslateAnimation outAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_PARENT, outValue);
        outAnim.setDuration(fallTime);
        outAnim.setStartOffset(startOffset);
        outAnim.setFillAfter(fillAfter);
        return outAnim;
    }


    /**
     * 获取缩放动画
     *
     * @param startProportion
     * @param endProportion
     * @param duration
     * @param startOffset
     * @param fillAfter
     * @return
     */
    public static Animation getScaleAnimation(float startProportion, float endProportion, long duration, long startOffset, boolean fillAfter) {

        ScaleAnimation sacleAnimationSmall = new ScaleAnimation(startProportion, endProportion, startProportion, endProportion,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sacleAnimationSmall.setDuration(duration);
        sacleAnimationSmall.setFillAfter(fillAfter);
        sacleAnimationSmall.setStartOffset(startOffset);

        return sacleAnimationSmall;

    }

    /**
     * 获取透明度变化动画
     *
     * @param startAlph
     * @param endAlph
     * @param duration
     * @param startOffset
     * @param fillAfter
     * @return
     */
    public static Animation getAlphAnimation(float startAlph, float endAlph, long duration, long startOffset, boolean fillAfter) {

        AlphaAnimation alphAnimation = new AlphaAnimation(startAlph, endAlph);
        alphAnimation.setDuration(duration);
        alphAnimation.setFillAfter(fillAfter);
        alphAnimation.setStartOffset(startOffset);

        return alphAnimation;

    }


    /**
     * 弹窗 动画 </br> 实例 成就弹窗
     */
    public static Animation getBadgeAnimation(){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(AnimationUtils.getScaleAnimation(1.0f, 1.2f, 100, 0, true));
        animationSet.addAnimation(AnimationUtils.getScaleAnimation(1.2f, 0.7f, 600, 100, true));
        animationSet.addAnimation(AnimationUtils.getScaleAnimation(0.7f, 1.2f, 1000, 700, true));

        return animationSet;
    }

}
