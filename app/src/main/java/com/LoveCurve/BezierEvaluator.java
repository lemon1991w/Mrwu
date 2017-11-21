package com.LoveCurve;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * 自定义贝塞尔曲线估值器
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {
    PointF mPointF1;
    PointF mPointF2;

    public BezierEvaluator(PointF mPointF1, PointF mPointF2) {
        this.mPointF1 = mPointF1;
        this.mPointF2 = mPointF2;
    }

    @Override
    public PointF evaluate(float t, PointF point0, PointF point3) {
        //t 百分比 0~1
        PointF pointF = new PointF();
        //套用公式进行计算
        pointF.x = point0.x * (1 - t) * (1 - t) * (1 - t)
                + 3 * mPointF1.x * t * (1 - t) * (1 - t)
                + 3 * mPointF2.x * t * t * (1 - t)
                + point3.x * t * t * t;

        pointF.y = point0.y * (1 - t) * (1 - t) * (1 - t)
                + 3 * mPointF1.y * t * (1 - t) * (1 - t)
                + 3 * mPointF2.y * t * t * (1 - t)
                + point3.y * t * t * t;
        return pointF;
    }
}
