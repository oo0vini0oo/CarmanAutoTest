package autotest.carman.easyconn.net.carmanautotest;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Console;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public static final String TAG="ExampleInstrumentedTest";
    private static final String BASIC_SAMPLE_PACKAGE
            = "net.easyconn.carman";
    Context mContext;
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        while (mDevice == null) {
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        Assert.assertThat(launcherPackage, IsNull.notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        mContext = InstrumentationRegistry.getContext();
        final Intent intent = mContext.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);

        // Wait for the app to appear
        BySelector bySelector = By.pkg(BASIC_SAMPLE_PACKAGE);
        if (bySelector != null) {
            try {
                mDevice.wait(Until.hasObject(bySelector.depth(0)), LAUNCH_TIMEOUT);
            } catch (Throwable ee) {
                ee.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void clickFeedback() throws Exception {
        UiObject tv_cancel = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/tv_cancel"));
        if (tv_cancel.exists()) {
            tv_cancel.click();
            Thread.sleep(1000);
        }
    }

    private void lunchApp() {
        // Launch the app
        mContext = InstrumentationRegistry.getContext();
        final Intent intent = mContext.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);

    }
    private boolean permission_allow()
    {
        UiObject permission_allow_button = mDevice.findObject(new UiSelector().resourceId("com.android.packageinstaller:id/permission_allow_button"));
        if(permission_allow_button.waitForExists(1000) && permission_allow_button.exists()) {
            try {
                Log.d(TAG, "permission_allow_button.....");
                return permission_allow_button.click();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    private  void clearEnv()
    {
        //清空测试环境
    }

    public void goHomePage() throws  Exception
    {
        clearEnv();
        Log.d(TAG, "waitForIdle.....");
        mDevice.waitForIdle(3000);

        while (permission_allow())
        {
            Thread.sleep(1000);
        }
        UiObject tv_skip = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/tv_skip"));
        mDevice.waitForIdle(3000);
        if(tv_skip.waitForExists(1000) && tv_skip.exists())
        {
            Log.d(TAG, "tv_skip button click .....");
            tv_skip.click();
        }

        UiObject cb_statement_status = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/cb_statement_status"));
        mDevice.waitForIdle(3000);
        if(cb_statement_status.waitForExists(1000) && cb_statement_status.exists())
        {
            Log.d(TAG, "cb_statement_status button click .....");
            cb_statement_status.click();
        }
        UiObject statement_ok = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/statement_ok"));
        mDevice.waitForIdle(3000);
        if(statement_ok.waitForExists(1000) && statement_ok.exists())
        {
            Log.d(TAG, "statement_ok button click .....");
            statement_ok.click();
        }
    }
    /**
     * 如果没有登录，就用15971130771账号登录
     * @return
     */
    public void login() throws Exception
    {
        UiObject id_home_main_user =mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/id_home_main_user"));
        id_home_main_user.click();
        UiObject tv_nick_name = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/tv_nick_name"));
        if("登录/注册".equals(tv_nick_name.getText()))
        {
            tv_nick_name.click();
            UiObject et_phone = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/et_phone"));
            et_phone.setText("15971130771");
            UiObject btn_get = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/btn_get"));
            btn_get.click();

            Thread.sleep(1000);


            UiObject et_verification_code = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/et_verification_code"));
            et_verification_code.setText("567149");
            UiObject btn_login = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/btn_login"));
            btn_login.click();

        }
        mDevice.pressBack();
        mDevice.waitForIdle(2000);
    }


    public void clickByResourceId(String resId)
    {
        try{
            UiObject uiObject = mDevice.findObject(new UiSelector().resourceId(resId));
            //点击音乐
            uiObject.waitForExists(6000);//等待按钮显示出来
            uiObject.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public UiObject clickByText(String text) {
        UiObject uiObject = null;
        try {
            uiObject = mDevice.findObject(new UiSelector().text(text));
            //点击音乐
            uiObject.waitForExists(6000);//等待按钮显示出来
            uiObject.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uiObject;
    }
    public UiObject clickByTextIfExists(String text) {
        UiObject uiObject = null;
        try {
            uiObject = mDevice.findObject(new UiSelector().text(text));
            if (uiObject.waitForExists(3000))
                uiObject.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uiObject;
    }

    public  void clickByResIdIfExists(String resId) throws  Exception
    {
        UiObject img_know = mDevice.findObject(new UiSelector().resourceId(resId));
        if(img_know.exists())
            img_know.click();
    }
    @Test
    public void checkMusicCollection() throws Exception
    {
        goHomePage();
        login();
        //点击音乐
        if (mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/rl_cover_album")).exists()){
        clickByResourceId("net.easyconn.carman:id/rl_cover_album");
        }else{
            clickByResourceId("net.easyconn.carman:id/rl_cover_default");
        }

        mDevice.waitForIdle(3000);
        clickByResIdIfExists("net.easyconn.carman:id/img_know");
        //点击乐库
        clickByResourceId("net.easyconn.carman:id/tv_more");
        //点击收藏
        clickByText("收藏");
        Thread.sleep(1000);

        UiObject object= clickByTextIfExists("罗辑思维");
        boolean isCollected =   object.exists();
        clickByText("推荐");
        mDevice.waitForIdle(3000);

        UiObject2 firstItem= mDevice.findObject(By.text("罗辑思维"));
        assertTrue(firstItem!=null);
        UiObject2 iv_icon= firstItem.getParent().getParent().findObject(By.res("net.easyconn.carman","iv_icon"));
        assertTrue(iv_icon!=null);
        iv_icon.click();

        //点击收藏
        clickByResourceId("net.easyconn.carman:id/img_list_favi");
        mDevice.pressBack();
        clickByText("收藏");
        Thread.sleep(1000);
        object = mDevice.findObject(new UiSelector().text("罗辑思维"));
        Assert.assertTrue(isCollected != object.exists());

    }






//    @Test
    public void addition_isCorrect() throws Exception {

        UiObject iv_music_play_pause = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/iv_music_play_pause"));
        //点击播放音乐
        iv_music_play_pause.waitForExists(6000);//等待按钮显示出来
        iv_music_play_pause.click();
        Log.d(TAG, " iv_music_play_pause click .....");
        //呼出语音助手
        UiObject iv_voice_new = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/iv_voice_new"));
        iv_voice_new.click();
        Log.d(TAG, " iv_voice_new click .....");


        int maxCnt;

        //for (int i = 0; i < 10; i++) {
        Thread.sleep(3000);


        //语音识别跑一段时间
        maxCnt = 3;
        while (maxCnt-- > 0) {
            Thread.sleep(1500);
            clickFeedback();
            UiObject button = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/speech_multi_choice_iv_back"));
            if (button.exists())//弹出了多选界面
            {
                button.click();
            }
        }


        while(mDevice.getCurrentPackageName().equals(BASIC_SAMPLE_PACKAGE)) {
            Thread.sleep(500);
            mDevice.pressBack();
        }
        //退出应用。
    }
}
