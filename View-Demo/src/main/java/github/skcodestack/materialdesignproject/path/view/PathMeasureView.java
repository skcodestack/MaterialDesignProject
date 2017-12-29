package github.skcodestack.materialdesignproject.path.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/27
 * Version  1.0
 * Description:
 */

public class PathMeasureView extends View {

    private static final String TAG = "PathMeasureView";
    private Paint mPaint;
    private Path mPath;

    public PathMeasureView(Context context) {
        this(context,null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {


        /**
         * 用这个构造函数可创建一个空的 PathMeasure，但是使用之前需要先调用 setPath 方法来与 Path 进行关联。
         * 被关联的 Path 必须是已经创建好的，如果关联之后 Path 内容进行了更改，则需要使用 setPath 方法重新关联。
         */
//        new PathMeasure();

        /**
         * 用这个构造函数是创建一个 PathMeasure 并关联一个 Path， 其实和创建一个空的 PathMeasure 后调用 setPath 进行关联效果是一样的，
         * 同样，被关联的 Path 也必须是已经创建好的，如果关联之后 Path 内容进行了更改，则需要使用 setPath 方法重新关联。
         *
         * 该方法有两个参数，第一个参数自然就是被关联的 Path 了，第二个参数是用来确保 Path 闭合，
         * 如果设置为 true， 则不论之前Path是否闭合，都会自动闭合该 Path(如果Path可以闭合的话)。
         */
//        new PathMeasure(mPath,true);


//    //关联一个Path
//    void setPath(Path path, boolean forceClosed)
//    //是否闭合
//    boolean	isClosed()
//    //获取Path的长度
//    float	getLength()
//
//    /**
//     * 我们知道 Path 可以由多条曲线构成，但不论是 getLength , getgetSegment 或者是其它方法，
//     * 都只会在其中第一条线段上运行，而这个 nextContour 就是用于跳转到下一条曲线到方法，
//     * 如果跳转成功，则返回 true，如果跳转失败，则返回 false。
//     * @return
//     */
//    boolean	nextContour()
//
//    /**
//     *  截取片段
//     * @param startD            开始截取位置距离 Path 起点的长度
//     * @param stopD             结束截取位置距离 Path 起点的长度
//     * @param dst               截取的 Path 将会添加到 dst 中
//     * @param startWithMoveTo   起始点是否使用 moveTo    true---保证截取得到的 Path 片段不会发生形变 flase --保证存储截取片段的 Path(dst) 的连续性
//     * @return                  判断截取是否成功
//     */
//    boolean	getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)
//
//    /**
//     * 这个方法是用于得到路径上某一长度的位置以及该位置的正切值：
//     * @param distance  距离 Path 起点的长度
//     * @param pos       该点的坐标值
//     * @param tan       该点的正切值
//     * @return         判断获取是否成功
//     */
//    boolean	getPosTan(float distance, float[] pos, float[] tan)
//
//    /**
//     * 获取指定长度的位置坐标及该点Matrix(矩阵)
//     * @param distance      距离 Path 起点的长度
//     * @param matrix        根据 falgs 封装好的matrix
//     * @param flags         规定哪些内容会存入到matrix中
//     * @return          判断获取是否成功
//     */
//    boolean	getMatrix(float distance, Matrix matrix, int flags)
//




        mPath.moveTo(100,100);
        mPath.lineTo(100,200);
        mPath.lineTo(200,200);

        PathMeasure pathMeasure = new PathMeasure(mPath,true);
        float length = pathMeasure.getLength();
        Log.e(TAG,"PathMeasure.getLength() ==true=== " + length);
        pathMeasure = new PathMeasure(mPath,false);
        float length2 = pathMeasure.getLength();
        Log.e(TAG,"PathMeasure.getLength() ==false=== " + length2);

        canvas.drawPath(mPath,mPaint);

    }
}
