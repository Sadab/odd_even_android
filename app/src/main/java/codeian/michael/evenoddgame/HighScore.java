package codeian.michael.evenoddgame;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class HighScore extends Activity {
    DatabaseConnect myDB;
    TextView highScoreFromDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_high_score);
        myDB = new DatabaseConnect(this);
        highScoreFromDB = (TextView) findViewById(R.id.highScoreFromDB);
        Cursor res = myDB.getData();
        if (res.getCount() == 0){
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            highScoreFromDB.setText(buffer.append(res.getString(0)));
        }
    }
}
