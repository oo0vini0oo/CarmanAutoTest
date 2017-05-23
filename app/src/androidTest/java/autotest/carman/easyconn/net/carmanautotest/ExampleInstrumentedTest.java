package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Instrumentation test,
 * 用来做专项测试
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends  TestBase{
    /**
     * 测试准备工作
     * 连接设备、启动应用
     * 调用goHomePage方法
     * */
    @Before
    public void initEnv() throws Exception
    {
        initDevice();
        startMainActivityFromHomeScreen();
        goHomePage();
    }


    @Test
    public void addition_isCorrect() throws Exception {
        //呼出语音助手
        int maxCnt =200;
        UiObject iv_voice_new = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/iv_voice_new"));
        while (maxCnt-- >0){
            iv_voice_new.click();
            Log.d(TAG, " iv_voice_new click .....");
            System.out.print(maxCnt);
            mDevice.waitForIdle(3000);
            mDevice.pressBack();
        }

        //语音识别跑一段时间
//
//        while (maxCnt-- > 0) {
//            Thread.sleep(1500);
//            UiObject button = mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/speech_multi_choice_iv_back"));
//            if (button.exists())//弹出了多选界面
//            {
//                button.click();
//            }
//        }
//        while(mDevice.getCurrentPackageName().equals(BASIC_SAMPLE_PACKAGE)) {
//            Thread.sleep(500);
//            mDevice.pressBack();
//        }
        //退出应用。
    }
}
