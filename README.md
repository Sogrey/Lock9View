# Lock9View
手势图案密码

[![Travis](https://img.shields.io/badge/License-Apache2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Twitter](https://img.shields.io/badge/Gradle-3.3-brightgreen.svg)](https://gradle.org/releases)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg?style=flat)](https://www.android.com)
[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![Download](https://api.bintray.com/packages/sogrey/maven/lock9view/images/download.svg) ](https://bintray.com/sogrey/maven/lock9view/_latestVersion)

[![Get automatic notifications about new "lock9view" versions](https://www.bintray.com/docs/images/bintray_badge_color.png)](https://bintray.com/sogrey/maven/lock9view?source=watch)

![运行示例项目](http://ojhqtezha.bkt.clouddn.com/sogrey_2017-03-10_094240.jpg)

## Usage

### step 1:

Add dependency to your gradle file:

    compile 'org.sogrey:lock9view:{lastVersion}'

Or maven:

    <dependency>
      <groupId>org.sogrey</groupId>
      <artifactId>lock9view</artifactId>
      <version>{lastVersion}</version>
      <type>pom</type>
    </dependency>

Or download aar [![Download](https://api.bintray.com/packages/sogrey/maven/lock9view/images/download.svg) ](https://bintray.com/sogrey/maven/download_file?file_path=org%2Fsogrey%2Flock9view%2F0.0.2%2Flock9view-0.0.2.aar)

> 其中：{lastVersion} 用上面 Download 徽章后面的版本号替换。

### step 2:

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



## License

    Copyright 2016 Sogrey

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.