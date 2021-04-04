package elhlafi.cs98.hlfgram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.FileDescriptor;
import java.io.IOException;

import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.image_view);

    }
    public void choosePhoto(View v){
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }
    public void setSepia(View v){
        Glide.with(this).load(image).apply(RequestOptions.bitmapTransform(new SepiaFilterTransformation())).into(imageView);



    }
    public void setBrightness(View v){
        Glide.with(this).load(image).apply(RequestOptions.bitmapTransform(new BrightnessFilterTransformation())).into(imageView);

    }
    public void setInvert(View v){
        Glide.with(this).load(image).apply(RequestOptions.bitmapTransform(new InvertFilterTransformation())).into(imageView);

    }
    public void setToon(View v){
        Glide.with(this).load(image).apply(RequestOptions.bitmapTransform(new ToonFilterTransformation())).into(imageView);

    }
    public void setKuwahara(View v){
        Glide.with(this).load(image).apply(RequestOptions.bitmapTransform(new KuwaharaFilterTransformation())).into(imageView);

    }
    public void setSketch(View v){
        Glide.with(this).load(image).apply(RequestOptions.bitmapTransform(new SketchFilterTransformation())).into(imageView);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( data!=null){
            System.out.println("oK ");
            try {


                Uri uri = data.getData();
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
                FileDescriptor fileDescriptor=parcelFileDescriptor.getFileDescriptor();
                image= BitmapFactory.decodeFileDescriptor(fileDescriptor);
                parcelFileDescriptor.close();
                imageView.setImageBitmap(image);
            }catch (IOException e){
                Log.e("hlf.cs98","file doesn't exist");
            }
        }
        else   System.out.println("not oK ");
    }
}
