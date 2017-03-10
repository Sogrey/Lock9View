# Lock9View
手势图案密码

[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9) [ ![Download](https://api.bintray.com/packages/sogrey/maven/lock9view/images/download.svg) ](https://bintray.com/sogrey/maven/lock9view/_latestVersion) [ ![Download](https://api.bintray.com/packages/sogrey/maven/lock9view/images/download.svg?version=0.0.1) ](https://bintray.com/sogrey/maven/lock9view/0.0.1/link)

![运行示例项目](http://ojhqtezha.bkt.clouddn.com/sogrey_2017-03-10_094240.jpg)

## Usage

step 1:
Add dependency to your gradle file:

    compile 'org.sogrey:lock9view:0.0.1'

Or maven:

    <dependency>
      <groupId>org.sogrey</groupId>
      <artifactId>lock9view</artifactId>
      <version>0.0.1</version>
      <type>pom</type>
    </dependency>


step 2:

xml:

    <org.sogrey.lock9view.LockIndicator
        android:id="@+id/li"
        android:layout_gravity="center_horizontal"
        android:layout_width="10dp"
        android:layout_height="10dp"
        android:layout_marginTop="70dp"/>

    <TextView
        android:id="@+id/txt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:layout_gravity="center_horizontal"
        android:text="绘制图案"/>

    <org.sogrey.lock9view.Lock9View
        android:id="@+id/lock9view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:lock9_autoLink="true"
        app:lock9_enableVibrate="true">

    </org.sogrey.lock9view.Lock9View>

java:

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