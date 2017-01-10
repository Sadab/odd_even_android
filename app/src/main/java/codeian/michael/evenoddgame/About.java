package codeian.michael.evenoddgame;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class About extends Activity {
    ImageView codeian;
    ImageView fb,git;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);

        codeian = (ImageView) findViewById(R.id.codeian);
        fb = (ImageView) findViewById(R.id.fb);
        git = (ImageView) findViewById(R.id.git);

        codeian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent codeian = new Intent();
                codeian.setAction(Intent.ACTION_VIEW);
                codeian.addCategory(Intent.CATEGORY_BROWSABLE);
                codeian.setData(Uri.parse("http://codeian.com.bd"));
                startActivity(codeian);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fb = new Intent();
                fb.setAction(Intent.ACTION_VIEW);
                fb.addCategory(Intent.CATEGORY_BROWSABLE);
                fb.setData(Uri.parse("https://facebook.com/sadab.michael"));
                startActivity(fb);
            }
        });

        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent git = new Intent();
                git.setAction(Intent.ACTION_VIEW);
                git.addCategory(Intent.CATEGORY_BROWSABLE);
                git.setData(Uri.parse("https://github.com/Sadab"));
                startActivity(git);
            }
        });
    }
}
