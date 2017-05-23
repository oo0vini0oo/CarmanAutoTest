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
public class ExampleInstrumentedTest extends  TestBase{




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






    //@Test
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

    @Test
    public void testMusic() throws Exception {
        MusicTest music= new MusicTest();

        music.testMusicList();
        Log.d(TAG, "testMusic: ok");
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
        maxCnt = 200;
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
