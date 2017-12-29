package github.skcodestack.materialdesignproject.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.CollapsibleActionView;
import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/27
 * Version  1.0
 * Description:
 */

public class CanvasView extends View {


    private Paint mPaint;

    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制直线
//        canvas.drawLine(100,100,700,700,mPaint);
        //绘制虚线
//        float []postions = {0,0,100,100,200,200,300,300,400,400,500,500,600,600,700,700,800,800,900,900};
//        canvas.drawLines(postions,mPaint);
        //绘制点
//        canvas.drawPoints(postions, mPaint);
        //绘制矩形
        RectF r = new RectF(100, 100, 400, 500);
//		canvas.drawRect(r, mPaint);
        //画圆角矩形
        //x-radius ,y-radius圆角的半径
//        canvas.drawRoundRect(r, 30, 30, mPaint);
        //画圆
//        canvas.drawCircle(500,500,100,mPaint);
        //画椭圆
//        canvas.drawOval(r,mPaint);

        RectF rectf = new RectF(100, 100, 400, 400);
        /**
         * 画圆弧
         * startAngle 起始角度，相对X轴正方向
         * sweepAngle 画多少角度的弧度
         * useCenter   false：只有一个纯弧线；true：闭合的边
         */
//        canvas.drawArc(rectf,0,270,false,mPaint);
//        canvas.drawArc(rectf,0,270,true,mPaint);


        //路径画线
        Path path = new Path();
        path.moveTo(100,100);
//        path.lineTo(200,200);
//        path.lineTo(200,100);
//        path.close();
//        canvas.drawPath(path,mPaint);

        //路径画圆角矩形
//        float radii[] = {10,10,10,10,10,10,50,60};
//        path.addRoundRect(rectf,radii, Path.Direction.CCW);
//        canvas.drawPath(path,mPaint);

        //路径画圆弧
//        path.addArc(rectf,0,60);
//        canvas.drawPath(path,mPaint);

        //路径画椭圆
//        path.addOval(r, Path.Direction.CCW);
//        canvas.drawPath(path,mPaint);

        //路径字
//        path.addArc(rectf,-180,180);
//        mPaint.setStrokeWidth(2);
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setTextSize(60);
//        canvas.drawTextOnPath("这是个神奇的类",path,20,20,mPaint);


        //创建一块矩形的区域
        Region region = new Region(50, 50, 200, 200);
//        Region region1 = new Region();
//		region1.setPath(path, region);//path的椭圆区域和矩形区域进行交集
        //结合区域迭代器使用（得到图形里面的所有的矩形区域）
//		RegionIterator iterator = new RegionIterator(region1);
//		Rect rect = new Rect();
//		while (iterator.next(rect)) {
//			canvas.drawRect(rect, mPaint);
//		}

        //合并
        Rect r1 = new Rect(100, 100, 400, 400);
//        region.union(r1);
        /**
         *   Region.Op：两个Region组合的方式，有下面6中方式，每种方式对应不同的结果：
             DIFFERENCE：在第一个Region的基础上减掉和第二个Region重合的区域。
             INTERSECT：两个Region交集部分。
             UNION：全集 两个Region的全集。
             XOR：补集，全集减去交集。
             REVERSE_DIFFERENCE：第二个Region的基础上减掉和第一个Region重合的部分。
             REPLACE: 第二个Region区域。
         */
//        region.op(r1, Region.Op.XOR);//交集部分
//        RegionIterator iterator = new RegionIterator(region);
//		Rect rect = new Rect();
//        mPaint.setStyle(Paint.Style.FILL);
//		while (iterator.next(rect)) {
//			canvas.drawRect(rect, mPaint);
//		}
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawPath(region.getBoundaryPath(),mPaint);

        /***********************Canvas变换技巧****************************/
        mPaint.setColor(Color.RED);
        canvas.save();
        //1.平移（Translate）
//        RectF re = new RectF(0, 0, 400, 500);
//		canvas.drawRect(re, mPaint);
//        canvas.translate(100, 100);
//        canvas.drawRect(re, mPaint);
//        canvas.restore();
//        RectF re2 = new RectF(50, 50, 400, 400);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(re2,mPaint);

        //2.缩放Scale
//        RectF re = new RectF(0, 0, 400, 500);
//        canvas.drawRect(re, mPaint);
//        canvas.scale(0.5f,0.5f);
//        canvas.drawRect(re, mPaint);
//        RectF re2 = new RectF(50, 50, 400, 400);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(re2,mPaint);

        //3.旋转Rotate
//        RectF re = new RectF(0, 0, 400, 500);
//        canvas.drawRect(re, mPaint);
//        canvas.rotate(50);
//        canvas.drawRect(re, mPaint);
//        RectF re2 = new RectF(50, 50, 400, 400);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(re2,mPaint);


        //4.斜拉画布Skew
//        RectF re = new RectF(0, 0, 400, 500);
//        canvas.drawRect(re, mPaint);
//        //sx,sy倾斜度：X轴方向上倾斜60度，tan60=根号3
//        canvas.skew(1.73f,0);
//        canvas.drawRect(re, mPaint);
//        RectF re2 = new RectF(20, 20, 400, 400);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(re2,mPaint);

        //5.裁剪画布clip
        RectF re = new RectF(200, 200, 400, 500);
        canvas.drawRect(re ,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.clipRect(new Rect(250, 250, 300, 400));
        canvas.drawColor(Color.BLUE);
    }
}
