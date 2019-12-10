package com.example.mobileomr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ImageActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private int correct=0;
    private int wrong=0;
    private int Q_num;
    private Button save;
    private Button calculate;
    private TextView result;
    public List<Integer> ans = new ArrayList<Integer>();
    private ImageView img;
    private  Mat                 mRgba;
    private  Mat                 mRgba1;
    private  Mat                 circles;
    private int                 radius;
    Bitmap bitmap;
    private static final String TAG = "*******";
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                    mRgba=new Mat();
                    mRgba1=new Mat();

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
        setContentView(R.layout.activity_image);
        img=(ImageView)findViewById(R.id.imageView);
        save=(Button)findViewById(R.id.ans_save);
        calculate=(Button)findViewById(R.id.enumerate);
        result=(TextView)findViewById(R.id.result);
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ans=ans;
                MainActivity.num_of_q=ans.size();
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct=0;
                wrong=0;
                Q_num=MainActivity.num_of_q;
                for(int i:ans){
                    if(get_q_num(i)>Q_num)
                        continue;
                    if(MainActivity.ans.contains(i)){
                        correct++;
                    }
                    else{
                        wrong++;
                    }
                }
                result.setText(String.valueOf("Result : Wrong= "+wrong+" Correct = " + correct));
            }
        });

    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                Bitmap bmp32 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                Utils.bitmapToMat(bmp32, mRgba);
                Utils.bitmapToMat(bmp32, mRgba1);
                Core.rotate(mRgba1, mRgba1, Core.ROTATE_90_COUNTERCLOCKWISE);
                Core.rotate(mRgba, mRgba, Core.ROTATE_90_COUNTERCLOCKWISE);

                Imgproc.cvtColor(mRgba1, mRgba1, Imgproc.COLOR_BGRA2GRAY);
                Size s = new Size(15,15);
                Imgproc.GaussianBlur(mRgba1,mRgba1, s, 0);

                circles = new Mat();
                Imgproc.HoughCircles(mRgba1, circles, Imgproc.CV_HOUGH_GRADIENT, 1.2, 50, 50, 30, 40, 60);

                toast(String.valueOf("size: " + circles.cols()) + ", " + String.valueOf(circles.rows()));
                if (circles.cols() == 80) {
                    int sum = 0;
                    for (int x = 0; x < 80; x++) {
                        double circleVec[] = circles.get(0, x);

                        if (circleVec == null) {
                            break;
                        }
//                        Point center = new Point((int) circleVec[0], (int) circleVec[1]);
                        sum = sum + (int) circleVec[2];

//                        Imgproc.circle(mRgba, center, 3, new Scalar(128, 255, 255), 4);
//                        Imgproc.circle(mRgba, center, radius, new Scalar(50, 255, 255), 2);
                    }
                    radius = sum / 80;
                    List<Point> points = new ArrayList<Point>();
                    points = sort_circles(circles);
                    int i = 1;
//                    for (Point tmp : points) {
//                        Imgproc.circle(mRgba, tmp, 3, new Scalar(128, 255, 255), 4);
//                        Imgproc.circle(mRgba, tmp, radius, new Scalar(50, 255, 255), 2);
//
//                        if (i == 2)
//                            break;
//                        i++;
//                    }
//                    Log.v(TAG,String.valueOf(get_q_num(14))+" "+String.valueOf(get_ans(14)));
                    Imgproc.threshold(mRgba1, mRgba1, 95, 255, Imgproc.THRESH_BINARY_INV);

                    for (Point point:points) {

                        int Q = get_q_num(i);
                        char A = get_ans(i);
                        int b=0;
                        int w=0;
                        for(int j= (int)point.x-radius;j<(int)point.x+radius;j++){
                            for(int k= (int)point.y-radius;k<(int)point.y+radius;k++){
                                double[] data1 = mRgba1.get(k,j);
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

                        if(ratio>50){
                            ans.add(i);
                            Log.v(TAG,String.valueOf(Q)+" "+String.valueOf(A)+" "+String.valueOf(i));
                            Imgproc.circle(mRgba, point, 3, new Scalar(128, 255, 255), 4);
                            Imgproc.circle(mRgba, point, radius, new Scalar(50, 255, 255), 2);
                        }
                        i++;
                    }

                }

                else{
                    toast("Take another clear picture or tune the system");
                }
                Imgproc.circle(mRgba, new Point(0,0), radius, new Scalar(255, 0,0), 8);
//                Core.rotate(mRgba1, mRgba1, Core.ROTATE_90_CLOCKWISE);
                Core.rotate(mRgba, mRgba, Core.ROTATE_90_CLOCKWISE);
                Utils.matToBitmap(mRgba, bitmap);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                toast("error");
            }
        }
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

    private void toast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }


}
