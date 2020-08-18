package com.shinyv.cnr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.linker.xlyt.components.search.SearchActivity;
import com.linker.xlyt.module.comment.WebCommentActivity;
import com.linker.xlyt.module.homepage.MainActivitys;
import com.linker.xlyt.module.mine.subscribe.MySubscribeActivity;
import com.linker.xlyt.module.play.PlayActivity;
import com.linker.xlyt.module.play.SongNewActivity;
import com.linker.xlyt.module.play.programorder.ProgramListActivity;
import com.linker.xlyt.module.single.test.AlbumNewActivity;
import com.linker.xlyt.module.wallet.VipH5Activity;
import com.linker.xlyt.module.wallet.VirtualCoinRechargeActivity;
import com.linker.xlyt.module.wallet.WalletMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.shinyv.cnr
 * @Description:
 * @Author: Maclay
 * @Date: 2020/5/25 15:28
 */
public class JumpUtils {
//    路径1（30%概率）：开屏（3秒）-首页（5秒）-专辑详情（2秒）-单曲播放页（3秒）-专辑详情（10秒）-首页（延续）
//    路径2（20%概率）：开屏（3秒）-首页（10秒）-节目单（5秒）-电台播放页（3秒）-首页（延续）
//    路径3（10%概率）：开屏（3秒）-首页（3秒）-订阅（3秒）-专辑详情（10秒）-单曲播放页（3秒）-专辑详情（延续）
//    路径4（10%概率）：开屏（3秒）-首页（3秒）-单曲播放页（延续）
//    路径5（10%概率）：开屏（3秒）-首页（3秒）-电台播放页（延续）
//    路径6（10%概率）：开屏（3秒）-首页（3秒）-搜索（3秒）-搜索结果（5秒）-专辑详情（3秒）-单曲播放（5秒）-评论（延续）
//    路径7（10%概率）：开屏（3秒）-首页（5秒）-我的钱包（3秒）-云币充值（5秒）-VIP页（5秒）-首页（延续）
//
//    umeng的appkey：5eaa8d23570df3a3240003ed

    public static String pathStr = "";

    private static List<Intent> jumpIntents = new ArrayList<>();

    public static void initIntents(Context context) {
        int path = getRandomPath();
        if (path <= 3) {
            pathStr = "开屏-首页-专辑详情-单曲播放页-专辑详情-首页";
            jumpIntents.add(getIntent(context, MainActivitys.class, 40));
            jumpIntents.add(getIntent(context, AlbumNewActivity.class, 30));
            jumpIntents.add(getIntent(context, SongNewActivity.class, 30));
            jumpIntents.add(getIntent(context, AlbumNewActivity.class, 20));
            jumpIntents.add(getIntent(context, MainActivitys.class, 20));
            jumpIntents.add(getIntent(context, MainActivitys.class, -1));
        } else if (path <= 5) {
            pathStr = "开屏-首页-节目单-电台播放页-首页";
            jumpIntents.add(getIntent(context, MainActivitys.class, 55));
            jumpIntents.add(getIntent(context, ProgramListActivity.class, 30));
            jumpIntents.add(getIntent(context, PlayActivity.class, 34));
            jumpIntents.add(getIntent(context, MainActivitys.class, 15));
            jumpIntents.add(getIntent(context, MainActivitys.class, -1));
        } else if (path <= 6) {
            pathStr = "开屏-首页-订阅-专辑详情-单曲播放页-专辑详情";
            jumpIntents.add(getIntent(context, MainActivitys.class, 55));
            jumpIntents.add(getIntent(context, MySubscribeActivity.class, 25));
            jumpIntents.add(getIntent(context, AlbumNewActivity.class, 20));
            jumpIntents.add(getIntent(context, SongNewActivity.class, 25));
            jumpIntents.add(getIntent(context, AlbumNewActivity.class, 20));
            jumpIntents.add(getIntent(context, MainActivitys.class, -1));
        } else if (path <= 7) {
            pathStr = "开屏-首页-单曲播放页";
            jumpIntents.add(getIntent(context, MainActivitys.class, 80));
            jumpIntents.add(getIntent(context, SongNewActivity.class, 50));
            jumpIntents.add(getIntent(context, MainActivitys.class, -1));
        } else if (path <= 8) {
            pathStr = "开屏-首页-电台播放页";
            jumpIntents.add(getIntent(context, MainActivitys.class, 90));
            jumpIntents.add(getIntent(context, PlayActivity.class, 40));
            jumpIntents.add(getIntent(context, MainActivitys.class, -1));
        } else if (path <= 9) {
            pathStr = "开屏-首页-搜索-搜索结果-专辑详情-单曲播放-评论";
            jumpIntents.add(getIntent(context, MainActivitys.class, 55));
            jumpIntents.add(getIntent(context, SearchActivity.class, 30));
            jumpIntents.add(getIntent(context, SearchActivity.class, 20));
            jumpIntents.add(getIntent(context, AlbumNewActivity.class, 15));
            jumpIntents.add(getIntent(context, SongNewActivity.class, 20));
            jumpIntents.add(getIntent(context, WebCommentActivity.class, 15));
            jumpIntents.add(getIntent(context, MainActivitys.class, -1));
        } else {
            pathStr = "开屏-首页-我的钱包-云币充值-VIP页-首页";
            jumpIntents.add(getIntent(context, MainActivitys.class, 60));
            jumpIntents.add(getIntent(context, WalletMainActivity.class, 30));
            jumpIntents.add(getIntent(context, VirtualCoinRechargeActivity.class, 30));
            jumpIntents.add(getIntent(context, VipH5Activity.class, 20));
            jumpIntents.add(getIntent(context, MainActivitys.class, 10));
            jumpIntents.add(getIntent(context, MainActivitys.class, -1));
        }
    }

    private static Intent getIntent(Context context, Class clazz, int delay) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra("data", delay);
        return intent;
    }

    public static void doNextIntent(Context context) {
        System.out.println("minglog--------"+jumpIntents.size());

//        if(jumpIntents.size()==1)
//        {
//            ((Activity)context).finish();
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }

        if (jumpIntents.size() > 0) {
            Intent intent = jumpIntents.get(0);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
            jumpIntents.remove(0);
        }
        else
        {

        }
    }

    public static int getRandomPath() {
        return (int) (Math.random() * 10) + 1;
    }
}
