package org.sogrey.lock9viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.sogrey.lock9view.Lock9View;
import org.sogrey.lock9view.Lock9View.CallBack;
import org.sogrey.lock9view.LockIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final TextView      mTxtHint  =  (TextView)findViewById(R.id.txt);
       final LockIndicator mLockIndicator  =  (LockIndicator)findViewById(R.id.li);
       final Lock9View mLock9View=  (Lock9View)findViewById(R.id.lock9view);
        mLock9View.setCallBack(new CallBack() {
            @Override
            public void onFinish(String password) {
                mTxtHint.setText(password);
                mLockIndicator.setPath(password);
                if(password.length()<4){
                    mLock9View.setErr();
                }else{
                    mLock9View.setNormal();
                }
            }
        });
    }
}
