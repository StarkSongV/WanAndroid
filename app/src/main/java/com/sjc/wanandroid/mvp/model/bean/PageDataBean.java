package com.sjc.wanandroid.mvp.model.bean;

import java.util.List;

/**
 * Created by sjc on 2018/6/28 15:15
 */

public class PageDataBean<T>{

    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"大大纸飞机","chapterId":245,"chapterName":"集合相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3023,"link":"https://www.jianshu.com/p/407afb4a267a\n\n","niceDate":"2018-06-19","origin":"","projectLink":"","publishTime":1529372331000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"荐一个<em class='highlight'>面试<\/em>必问的Java系列专题","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"书呆子Rico","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":3002,"link":"https://blog.csdn.net/justloveyou_/article/details/78653929","niceDate":"2018-06-07","origin":"","projectLink":"","publishTime":1528371566000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"<em class='highlight'>面试<\/em>/笔试第五弹 &mdash;&mdash; Java<em class='highlight'>面试<\/em>问题集锦（下篇）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"HIT-Alibaba","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2945,"link":"https://hit-alibaba.github.io/interview/basic/network/HTTP.html","niceDate":"2018-05-22","origin":"","projectLink":"","publishTime":1526988545000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"笔试<em class='highlight'>面试<\/em>知识整理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"大神带我来搬砖","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2920,"link":"https://www.jianshu.com/p/9fca3a2bf7ec","niceDate":"2018-05-14","origin":"","projectLink":"","publishTime":1526305488000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"从技术<em class='highlight'>面试<\/em>官的角度谈谈简历和<em class='highlight'>面试<\/em>那些事儿","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Java架构","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2919,"link":"https://www.jianshu.com/p/eecc252876bc","niceDate":"2018-05-14","origin":"","projectLink":"","publishTime":1526305472000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"Java<em class='highlight'>面试<\/em>中需要准备哪些多线程并发的技术要点","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"神奇的小蘑菇","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2893,"link":"https://www.jianshu.com/p/7b35a47822f8","niceDate":"2018-05-07","origin":"","projectLink":"","publishTime":1525694725000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"2018Android大厂<em class='highlight'>面试<\/em>经验","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"qing的世界","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2892,"link":"https://www.jianshu.com/p/9f3b96937253","niceDate":"2018-05-07","origin":"","projectLink":"","publishTime":1525694705000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"Android程序员<em class='highlight'>面试<\/em>会遇到的算法(part 4 消息队列的应用)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Java3y","chapterId":67,"chapterName":"网络基础","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2881,"link":"https://www.jianshu.com/p/fca985f0b40d","niceDate":"2018-05-02","origin":"","projectLink":"","publishTime":1525275286000,"superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"HTTP<em class='highlight'>面试<\/em>题都在这里","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"醒着的码者","chapterId":245,"chapterName":"集合相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2826,"link":"https://www.jianshu.com/p/99ad883041d6","niceDate":"2018-04-18","origin":"","projectLink":"","publishTime":1524051390000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"搞懂 HashSet & LinkedHashSet 源码 以及集合常见<em class='highlight'>面试<\/em>题目","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"guoxiaoxing","chapterId":0,"chapterName":"","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2815,"link":"http://www.wanandroid.com/blog/show/2109","niceDate":"2018-04-15","origin":"","projectLink":"","publishTime":1523762718000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Android <em class='highlight'>面试<\/em>题集 包含答案","type":0,"userId":-1,"visible":0,"zan":0},{"apkLink":"","author":"wustor","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2784,"link":"http://www.jianshu.com/p/91718de18979","niceDate":"2018-04-07","origin":"","projectLink":"","publishTime":1523107437000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"我所经历的Android<em class='highlight'>面试<\/em>","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"android的那点事","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2488,"link":"https://www.jianshu.com/p/fb815eaf628f","niceDate":"2018-03-16","origin":"","projectLink":"","publishTime":1521132454000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"互联网大型公司（阿里腾讯百度等）android<em class='highlight'>面试<\/em>题目(有答案)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"CyC2018","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2478,"link":"http://www.wanandroid.com/blog/show/2081","niceDate":"2018-03-14","origin":"","projectLink":"","publishTime":1520990535000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"技术<em class='highlight'>面试<\/em>需要掌握的基础知识整理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"AWeiLoveAndroid","chapterId":73,"chapterName":"面试相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2449,"link":"https://www.jianshu.com/p/c70989bd5f29","niceDate":"2018-03-05","origin":"","projectLink":"","publishTime":1520260863000,"superChapterId":184,"superChapterName":"热门专题","tags":[],"title":"最全的BAT大厂<em class='highlight'>面试<\/em>题整理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"qing的世界","chapterId":0,"chapterName":"","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2350,"link":"http://www.jianshu.com/p/01f250eb877a","niceDate":"2018-02-22","origin":"","projectLink":"","publishTime":1519309207000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Android程序员<em class='highlight'>面试<\/em>会遇到的算法(part 2 广度优先搜索)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"qing的世界","chapterId":0,"chapterName":"","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2351,"link":"http://www.jianshu.com/p/6f179f37ad79","niceDate":"2018-02-22","origin":"","projectLink":"","publishTime":1519309207000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Android程序员<em class='highlight'>面试<\/em>会遇到的算法(part 1 关于二叉树的那点事) 附Offer情况","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"qing的世界","chapterId":0,"chapterName":"","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2349,"link":"http://www.jianshu.com/p/d48ed65b7a3a","niceDate":"2018-02-22","origin":"","projectLink":"","publishTime":1519309206000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Android程序员<em class='highlight'>面试<\/em>会遇到的算法(part 3 深度优先搜索-回溯backtracking)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":298,"chapterName":"我的博客","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2323,"link":"http://www.wanandroid.com/blog/show/2038","niceDate":"2018-02-07","origin":"","projectLink":"","publishTime":1517969831000,"superChapterId":298,"superChapterName":"原创文章","tags":[],"title":"从一道<em class='highlight'>面试<\/em>题开始说起 枚举、动态代理的原理","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"任玉刚","chapterId":0,"chapterName":"","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2265,"link":"http://blog.csdn.net/singwhatiwanna/article/details/49230997","niceDate":"2018-02-04","origin":"","projectLink":"","publishTime":1517733698000,"superChapterId":0,"superChapterName":"","tags":[],"title":"给Android程序员的一些<em class='highlight'>面试<\/em>建议","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"看书的小蜗牛","chapterId":0,"chapterName":"","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2251,"link":"http://www.jianshu.com/p/18e4efa2e0a2","niceDate":"2018-02-02","origin":"","projectLink":"","publishTime":1517548568000,"superChapterId":0,"superChapterName":"","tags":[],"title":"Android<em class='highlight'>面试<\/em>题：bindService获取代理是同步还是异步","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 3
     * size : 20
     * total : 49
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<T> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

}
