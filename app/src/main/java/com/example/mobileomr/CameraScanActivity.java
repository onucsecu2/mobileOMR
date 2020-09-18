package com.example.mobileomr;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraScanActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "SCANNER_LOG:";
    private CameraBridgeViewBase mOpenCvCameraView;
    private  Rect                roi;
    private  Mat                 mRgba;
    private  Mat                 mRgbaCropped;
    private int                  mHeight,mWidth;
    private int                  mRadius,Q;
    private char                 A;
    private LinearLayout         layout;
    private Button               scan_btn;
    private TextView             c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20;
    private boolean              scan_indicator=true;
    private ImageView            review_img;
    private Bitmap bmp;
    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
//                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera_scan);

        layout=(LinearLayout)findViewById(R.id.side_bar);
        scan_btn=(Button)findViewById(R.id.scan_enable);
        c1=(TextView) findViewById(R.id.C1);
        c2=(TextView) findViewById(R.id.C2);
        c3=(TextView) findViewById(R.id.C3);
        c4=(TextView) findViewById(R.id.C4);
        c5=(TextView) findViewById(R.id.C5);
        c6=(TextView) findViewById(R.id.C6);
        c7=(TextView) findViewById(R.id.C7);
        c8=(TextView) findViewById(R.id.C8);
        c9=(TextView) findViewById(R.id.C9);
        c10=(TextView) findViewById(R.id.C10);
        c11=(TextView) findViewById(R.id.C11);
        c12=(TextView) findViewById(R.id.C12);
        c13=(TextView) findViewById(R.id.C13);
        c14=(TextView) findViewById(R.id.C14);
        c15=(TextView) findViewById(R.id.C15);
        c16=(TextView) findViewById(R.id.C16);
        c17=(TextView) findViewById(R.id.C17);
        c18=(TextView) findViewById(R.id.C18);
        c19=(TextView) findViewById(R.id.C19);
        c20=(TextView) findViewById(R.id.C20);

        review_img=(ImageView)findViewById(R.id.review_img);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) layout.getLayoutParams();
        params.width = width;
        params.setMargins(0,((height-width)/2)+200,0,0);
        layout.setLayoutParams(params);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.color_blob_detection_activity_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.enableFpsMeter();
        mOpenCvCameraView.setCvCameraViewListener(this);

        if(scan_indicator){
            scan_btn.setText("Disable Scan");
        }
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scan_indicator){
                    scan_indicator=false;
                    scan_btn.setText("Enable Scan");
                }else{
                    scan_indicator=true;
                    scan_btn.setText("Disable Scan");
                }
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            //Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            //Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }
    @Override
    public void onCameraViewStarted(int width, int height) {
        mHeight=height;
        mWidth=width;
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        roi = new Rect(0, 0, mWidth, 200);
    }
    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        mRgba= inputFrame.rgba();
        Core.rotate(mRgba, mRgba, Core.ROTATE_90_CLOCKWISE);
        if(scan_indicator) {
            Mat circles = new Mat();
            mRgbaCropped = new Mat(mRgba, roi);
            circles = detectCircle(mRgbaCropped);
            if (circles.cols() == 80) {
                scan_indicator=false;
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        scan_btn.setText("Enable Scan");
                    }
                });
                evaluateCircles(circles, mRgba, mRgbaCropped);

            }
        }
        Imgproc.rectangle(mRgba,new Point(0,0),new Point(mWidth,200),new Scalar(0, 0, 255),5 );
        Imgproc.rectangle(mRgba,new Point(25,25),new Point(160,175),new Scalar(0, 0, 255),3 );

        return mRgba;
    }

    private void reviewImage(Mat mRgba) {
        bmp = null;
        Mat cropped=new Mat(mRgba, roi);
        try {
            bmp = Bitmap.createBitmap(cropped.cols(), cropped.rows(), Bitmap.Config.RGB_565);
            Utils.matToBitmap(cropped, bmp);
            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    review_img.setImageBitmap(bmp);
                }
            });

        } catch (CvException e) {
            Log.d(TAG, e.getMessage());
        }

    }

    private void evaluateCircles(Mat circles, Mat mRgba, Mat mRgbaCropped) {
        List<Answer> answers = new ArrayList<Answer>();
        for (int x=0; x < Math.min(circles.cols(), 80); x++ ) {
            double circleVec[] = circles.get(0, x);

            if (circleVec == null) {
                break;
            }
            Point center = new Point((int) circleVec[0], (int) circleVec[1]);
            int radius = (int) circleVec[2];
            mRadius=radius;
            Imgproc.circle(mRgba, center, 3, new Scalar(128, 255, 255), 5);
            Imgproc.circle(mRgba, center, radius, new Scalar(50, 255, 255), 2);
        }

        List<Point> points = new ArrayList<Point>();
        points = sort_circles(circles);
        int i = 1;
        Imgproc.threshold(mRgbaCropped, mRgbaCropped, 95, 255, Imgproc.THRESH_BINARY_INV);
        for (Point point:points) {
            Q = get_q_num(i);
            A = get_ans(i);
            int b=0;
            int w=0;
            for(int j= (int)point.x-mRadius;j<(int)point.x+mRadius;j++){
                for(int k= (int)point.y-mRadius;k<(int)point.y+mRadius;k++){
                    double[] data1 = mRgbaCropped.get(k,j);
                    if(data1!=null){
                        if(data1[0]>10)
                            w++;
                        else
                            b++;
                    }
                }
            }
            int total=b+w;
            float ratio=((float)w/(float)total)*100;

            if(ratio>80){
                answers.add(new Answer(Q,A));
//                System.out.println("Yoo "+String.valueOf(Q)+" "+String.valueOf(A)+" "+String.valueOf(i));
                Imgproc.circle(mRgba, point, 3, new Scalar(0, 255, 0), 4);
                Imgproc.circle(mRgba, point, mRadius, new Scalar(0, 0, 255), 2);
            }

            i++;
        }
        reviewImage(mRgba);
        displayAnwers(answers);
    }
    private void displayAnwers(List<Answer> answers) {

        for(final Answer answer:answers){
            int question=answer.getQuestion_no();

            switch(question){
                case 1:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c1.setText("1."+answer.getAnswer());
                        }
                    });
                    break;
                case '2':
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c2.setText("2."+answer.getAnswer());
                        }
                    });
                    break;
                case 3:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c3.setText("3."+answer.getAnswer());
                        }
                    });
                    break;
                case 4:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c4.setText("4."+answer.getAnswer());
                        }
                    });
                    break;
                case 5:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c5.setText("5."+answer.getAnswer());
                        }
                    });
                    break;
                case 6:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c6.setText("6."+answer.getAnswer());
                        }
                    });
                    break;
                case 7:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c7.setText("7."+answer.getAnswer());
                        }
                    });
                    break;
                case 8:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c8.setText("8."+answer.getAnswer());
                        }
                    });
                    break;
                case 9:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c9.setText("9."+answer.getAnswer());
                        }
                    });
                    break;
                case 10:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c10.setText("10."+answer.getAnswer());
                        }
                    });
                    break;
                case 11:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c11.setText("11."+answer.getAnswer());
                        }
                    });
                    break;
                case 12:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c12.setText("12."+answer.getAnswer());
                        }
                    });
                    break;
                case 13:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c13.setText("13."+answer.getAnswer());
                        }
                    });
                    break;
                case 14:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c14.setText("14."+answer.getAnswer());
                        }
                    });
                    break;
                case 15:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c15.setText("15."+answer.getAnswer());
                        }
                    });break;
                case 16:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c16.setText("16."+answer.getAnswer());
                        }
                    });
                    break;
                case 17:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c17.setText("17."+answer.getAnswer());
                        }
                    });
                    break;
                case 18:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c18.setText("18."+answer.getAnswer());
                        }
                    });
                    break;
                case 19:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c19.setText("19."+answer.getAnswer());
                        }
                    });
                    break;
                case 20:
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            c20.setText("20."+answer.getAnswer());
                        }
                    });
                    break;
                    default:
                        System.out.println("Out of Range");
                        break;
            }
        }
    }
    private Mat detectCircle(Mat mRgbaCropped) {
        Mat circles= new Mat();
        org.opencv.core.Size s = new Size(5,5);
        Imgproc.GaussianBlur(mRgbaCropped,mRgbaCropped, s, 0);
        Imgproc.cvtColor(mRgbaCropped, mRgbaCropped, Imgproc.COLOR_RGB2GRAY);
        Imgproc.HoughCircles(mRgbaCropped, circles, Imgproc.CV_HOUGH_GRADIENT, 1.2, 10, 50, 30, 10, 15);
        return circles;
    }
    public void toast(String str){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, str, duration);
        toast.show();
    }
    private List<Point> sort_circles(Mat circles) {
        List<Point> points = new ArrayList<Point>();
        for (int x=0; x <80; x++ ) {
            circles.get(0, x);
            double circleVec[] = circles.get(0, x);

            if (circleVec == null) {
                break;
            }

            Point center = new Point((int) circleVec[0], (int) circleVec[1]);
            points.add(center);
        }
        Collections.sort(points,new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                return Double.compare(o1.x, o2.x);
            }
        });
        List<Point>tmp=new ArrayList<Point>();
        List<Point>sorted_points=new ArrayList<Point>();
        for (int i = 0,j=1; i < points.size(); i++,j++) {
            tmp.add(points.get(i));
            if(j%5==0){
                Collections.sort(tmp,new Comparator<Point>() {

                    public int compare(Point o1, Point o2) {
                        return Double.compare(o1.y, o2.y);
                    }
                });
                for (Point temp : tmp) {
                    sorted_points.add(temp);
                }
                tmp.clear();
            }
        }

        return sorted_points;
    }
    private int get_q_num(int n){
        int pos=(int)Math.ceil((double) n/20.0);
        int ans=n%5;
        if (ans==0)
            ans=5;
        return (pos-1)*5+ans;
    }
    private char get_ans(int n){
        int pos=(int)Math.ceil((double)n/5.0);
        int ans=pos%4;
        char ch;
        switch(ans) {
            case 0:
                ch='D';
                break;
            case 1:
                ch='A';
                break;
            case 2:
                ch='B';
                break;
            case 3:
                ch='C';
                break;
            default:ch='_';
        }
        return ch;
    }
}
