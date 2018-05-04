package com.acg12.conf.search;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/3.
 */
public class SubjectStaffConstant {

    // 书籍
    public static HashMap<String , String> booksMap = new HashMap<>();
    // 动画
    public static HashMap<String , String> animationMap = new HashMap<>();
    // 游戏
    public static HashMap<String , String> gameMap = new HashMap<>();

    static {
        booksMap.put("原作","原作");
        booksMap.put("作者","作者");
        booksMap.put("作画","作画");
        booksMap.put("插图","イラスト");
        booksMap.put("出版社","出版社");
        booksMap.put("连载杂志","掲載誌");
        booksMap.put("译者","译者");
        booksMap.put("客串","ゲスト");
    }

    static {
        animationMap.put("原作","原作");
        animationMap.put("总导演","総監督");
        animationMap.put("导演","監督 シリーズ監督");
        animationMap.put("脚本","シナリオ");
        animationMap.put("分镜","コンテ  ストーリーボード  画コンテ  絵コンテ");
        animationMap.put("演出","演出");
        animationMap.put("音乐","楽曲  音楽");
        animationMap.put("人物原案","キャラ原案");
        animationMap.put("人物设定","キャラ設定");
        animationMap.put("分镜构图","レイアウト");
        animationMap.put("系列构成","シナリオディレクター  構成  シリーズ構成  脚本構成");
        animationMap.put("副导演","助監督");
        animationMap.put("美术监督","アートディレクション 背景監督");
        animationMap.put("美术设计","美術設定");
        animationMap.put("色彩设计","色彩設定");
        animationMap.put("总作画监督","チーフ作画監督");
        animationMap.put("作画监督","作監 アニメーション演出");
        animationMap.put("机械设定","メカニック設定");
        animationMap.put("摄影监督","撮影監督");
        animationMap.put("CG 导演","CG 監督");
        animationMap.put("监修","シリーズ監修 スーパーバイザー");
        animationMap.put("道具设计","プロップデザイン");
        animationMap.put("原画","作画 原画");
        animationMap.put("第二原画","原画協力");
        animationMap.put("动画检查","動画チェック");
        animationMap.put("制作","製作 製作スタジオ");
        animationMap.put("动画制作","アニメーション制作 アニメ制作 アニメーション");
        animationMap.put("OP・ED 分镜","OP・ED 分鏡");
        animationMap.put("音乐制作","楽曲制作 音楽制作");
        animationMap.put("助理制片人","協力プロデューサー");
        animationMap.put("副制片人","アソシエイトプロデューサー");
        animationMap.put("背景美术","背景");
        animationMap.put("色彩指定","色彩指定");
        animationMap.put("数码绘图","数码绘图");
        animationMap.put("3DCG","3DCG");
        animationMap.put("制作管理","制作マネージャー 制作担当 制作班長");
        animationMap.put("剪辑","編集");
        animationMap.put("原案","原案");
        animationMap.put("主题歌编曲","主题歌编曲");
        animationMap.put("主题歌作曲","主题歌作曲");
        animationMap.put("主题歌作词","主题歌作词");
        animationMap.put("主题歌演出","主题歌演出");
        animationMap.put("插入歌演出","插入歌演出");
        animationMap.put("企画","プランニング  企画開発");
        animationMap.put("宣传","パブリシティ  宣伝  広告宣伝  番組宣伝  製作宣伝");
        animationMap.put("录音","録音");
        animationMap.put("录音助理","録音アシスタント  録音助手");
        animationMap.put("系列监督","系列监督");
        animationMap.put("制作","製作");
        animationMap.put("设定","設定");
        animationMap.put("音响监督","音响监督");
        animationMap.put("音响","音響");
        animationMap.put("音效","音響効果");
        animationMap.put("特效","視覚効果");
        animationMap.put("配音监督","配音监督");
        animationMap.put("联合导演","联合导演");
        animationMap.put("背景设定","基本設定  場面設定  場面設計  設定");
        animationMap.put("补间动画","動画");
        animationMap.put("执行制片人","製作総指揮");
        animationMap.put("助理制片人","協力プロデューサー");
        animationMap.put("制片人","プロデュース  プロデューサー");
        animationMap.put("助理录音师","助理录音师");
        animationMap.put("助理制片协调","助理制片协调");
        animationMap.put("演员监督","キャスティングコーディネーター監督");
        animationMap.put("总制片","チーフプロデューサー  チーフ制作  総合プロデューサー");
        animationMap.put("联合制片人","联合制片人");
        animationMap.put("台词编辑","台詞編集");
        animationMap.put("后期制片协调","ポストプロダクション協力");
        animationMap.put("制作助手","制作アシスタント 制作補佐 製作補");
        animationMap.put("制作协调","制作コーディネーター");
        animationMap.put("友情協力","特别鸣谢");
    }

    static {
        gameMap.put("开发","開発元");
        gameMap.put("发行","発売元");
        gameMap.put("遊戲設計師"," ゲームクリエイター");
        gameMap.put("原作","");
        gameMap.put("导演","監督 演出 シリーズ監督");
        gameMap.put("企画","");
        gameMap.put("监修","監修");
        gameMap.put("剧本","腳本");
        gameMap.put("系列构成","シリーズ構成");
        gameMap.put("作画监督","作画監督");
        gameMap.put("原画","");
        gameMap.put("人物设定","キャラ設定 キャラクターデザイン");
        gameMap.put("机械设定","メカニック設定");
        gameMap.put("美工","美術");
        gameMap.put("CG监修","CG監修");
        gameMap.put("SD原画","");
        gameMap.put("背景","");
        gameMap.put("音响监督","");
        gameMap.put("音乐","音楽");
        gameMap.put("程序","プログラム");
        gameMap.put("动画制作","アニメーション制作 アニメ制作 アニメーション");
        gameMap.put("动画监督","アニメーション監督");
        gameMap.put("动画剧本","アニメーション脚本");
        gameMap.put("制作总指挥","");
        gameMap.put("QC","");
        gameMap.put("关卡设计","");
        gameMap.put("主题歌作曲","");
        gameMap.put("主题歌作词","");
        gameMap.put("主题歌演出","");
        gameMap.put("插入歌演出","");
        gameMap.put("协力","協力");
    }

}
