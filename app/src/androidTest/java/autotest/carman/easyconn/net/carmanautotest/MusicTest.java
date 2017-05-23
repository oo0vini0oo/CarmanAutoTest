package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MusicTest extends TestBase {

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
    public void chekSwipUp() throws Exception{
        if (mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/rl_cover_album")).exists()){
            clickByResourceId("net.easyconn.carman:id/rl_cover_album");
        }else{
            clickByResourceId("net.easyconn.carman:id/rl_cover_default");
        }
        mDevice.waitForIdle(3000);
        clickByResIdIfExists("net.easyconn.carman:id/img_know");
        //点击乐库
        clickByResIdIfExists("net.easyconn.carman:id/tv_more");
    }

    //@Test
    public void checkMusicCollection() throws Exception
    {
        login();
        //点击音乐
        if (mDevice.findObject(new UiSelector().resourceId("net.easyconn.carman:id/rl_cover_album")).exists()){
            clickByResourceId("net.easyconn.carman:id/rl_cover_album");
        }
        mDevice.waitForIdle(3000);
        clickByResIdIfExists("net.easyconn.carman:id/img_know");
        //点击乐库
        clickByResIdIfExists("net.easyconn.carman:id/tv_more");
        swipeToUp();
        Thread.sleep(10000);
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

}
