package com.liyafeng.practice;

public class AndroidFramework {


    //region Android UI

    /**
     * =====================
     * ### Android UI
     * =====================
     * */

    /**
     * 简述事件分发流程
     * 事件分发机制
     */
    public void a1() {
        /*
        * 最一开始ViewRootImpl 接收到触摸事件，然后会传递给DecorView
        * 的dispatchTouchEvent()，然后Decorview会将事件分发给子控件
        * 先判断当前View是否拦截事件，如果拦截就直接调用自己的ontouchevent
        * 如果没有拦截就依次分发给子view，直到最底层的view，在ontouchevent
        * 中返回是否消费，如果有消费下次就直接将事件传递给它，如果没有消费就依次
        * 调用父控件的ontouchevent,直到事件被消费。
        *
        * 整个过程是一个递归调用，是类似于是反向的树的前序遍历
        *
        * */

    }

    /**
     * View的渲染机制
     */
    public void a2() {
        /*
        *
        * Android的图形都是在canvas对象中绘制的，一个canvas持有一个bitmap对象
        * 然后用openGl es将多维图形通过gpu来渲染，进行光栅化，就是将矢量图转化为
        * 像素点，然后通过硬件时钟将像素点投影到屏幕上
        *
        * 其中16ms同步一次，帧率就是60fps ，所以我们完成一次绘制要在16ms内
        * 否则就会出现掉帧的情况，因为绘制时间超过16ms，就算绘制完成也不会同步，
        * 只能等待下一次同步，所以这一帧就没有被渲染，我们管他叫掉帧
        *
        * 我们滑动卡顿优化的原理也是根据这个来的
        *
        * */
    }


    /**
     * View的绘制流程
     * http://www.liyafeng.com/c/Android_APIsetContentView流程分析
     */
    public void a2_1() {
        /*
        * 首先会将xml解析成对象，addview添加到decorview中
        * 然后执行requestLayout()，最终在ViewRootImpl中执行doTraversals
        * 进行view树的遍历，最先执行performMeasure()初步确定view的宽高,
        * 然后是performLayout，确定子view在父布局中的位置，left top right bottom 四个参数
        * 最后执行performDraw ,将canvas对象传入，子view根据自己的ondraw方法进行绘制
        */
    }

    /**
     * Android 动画原理 、底层如何给上层信号？
     */
    public void a3() {
        /*
        * 分为 1.补间动画（tween 屯，两者之间）2.属性动画(attribute) 3.帧动画 frame
        *
        * 补间动画实际上操作的是canvas的matrix ，属性动画操作view的属性，有get set方法的属性
        * 帧动画就是一帧帧图片播放
        *
        * 他们都原理都是记录动画的执行时间，判断当前时间动画有没有结束，如果没有结束
        * 就调用invalidate方法进行重绘，一次次的重绘，改变位置，就会形成动画效果
        *
        * 给上层信号调用自身的的invalidate方法，里面调用父布局的invalidateChildInParent
        * 这里有一个while循环，会一直取父布局（的引用），直到调用viewrootimpl的invalidateChildInParent
        * 里面会调用scheduleTraversals()执行遍历，遍历调用view树的ondraw，这样就会刷新view的视图
        *
        * */

    }

    /**
     * Activity的加载流程
     * http://www.liyafeng.com/c/Android_APIstartActivity流程分析
     */
    public void a3_1() {
        /*
        * 首先用binder请求到ActivityManagerService ，然后会回调到本进程的
        * ActivityThread，在里面会通过反射方式new 出Activity的对象，然后会
        * 回调Activity的生命周期
        */
    }

    //endregion

    //region Android 内存/虚拟机

    /**
     * =====================
     * ### Android 内存
     * =====================
     * */
    /**
     * 说说Android的垃圾回收机制
     */
    public void a4() {
        /*
        * 虚拟机中的内存区域分为新生代和老年代，新分配的对象被存储在新生代中
        * 当需要申请内存而内存空间不足时，就触发Minor GC来回收新生代的内存，
        * 如果没被回收的对象就讲它年龄+1，一般到15的时候对象会被转移到老年代
        * 老年代占满，会触发Major GC，回收老年代的垃圾对象
        * 直到所有内存都占满，就触发Full GC，回收所有内存空间中的垃圾对象
        * Android使用的是标记-清除算法来回收垃圾对象
        *
        * 另外的GC算法还有复制算法（就是定义两个大小相等的区域，一次GC把非垃圾对象
        * 复制到另一块内存中，这样减少了内存碎片）
        * 标记-整理算法，标记非垃圾对象，并且整理到连续的内存中
        */
    }

    /**
     * 说说什么是内存泄漏，说一个典型的例子，怎么避免？
     */
    public void a5() {
        /*
        * 本该被回收的对象因为存在对他的强引用而没有被回收
        * Android中最典型的就是Activity对象的泄漏，比如用Handler发延时消息
        * 在Activity销毁后消息还存在队列中，但是此时Handler对象持有Activity的引用
        * 从而使Activity没有被回收，导致内存泄漏，解决方法就是用静态Handler或者在
        * onDestroy()中移除消息
        * -------
        * 还有一个例子是在Activity中使用AsyncTask，当Activity销毁时任务没有执行完
        * 因为AsyncTask持有Activity的引用，也会导致泄漏，解决方法是在onDestroy调用
        * 他的cancel方法来中断线程
        *
        */
    }

    /**
     * Android进程如何保活？系统杀掉后如何重启？为什么要保活？
     * http://blog.csdn.net/andrexpert/article/details/75045678
     */
    public void a6() {
        /*
        * 我们APP要及时接收到通知，那么就需要通知服务一直在后台运行
        * Android的进程回收机制是用Low Memory Killer
        *
        * 1.监听系统广播唤醒app
        * 2.启动前台service，在通知栏发个消息
        * 3.减少内存消耗，防止被杀死
        * 4.一像素保活（动态监听屏幕锁屏解锁广播，在锁屏时开启一个像素的Activity）
        *   在黑屏状态下保活
        * 5.循环播放一段无声的音频，用一键清理也保活
        * 6.双进程相互唤起
        *
        * linux会为每个进程分配一个优先级，叫oom_adj，数值越低优先级越高，越不容易被杀死
        * 普通app的值一般是大于0，系统进程一般是小于0
        * 用adb shell进入手机命令行模式，然后用 ps|grep com.xxx 来查看包下的所有进程
        * 然后用 cat /proc/进程id/oom_adj 来查看进程的优先级数值
        *
        *
        */
    }

    /**
     * Android Dalvik虚拟机和JVM的区别？
     */
    public void a6_1() {
        /*
        * 1.Android Dalvik 运行的是.dex 即Dalvik Executable,
        * 他是.class文件的压缩，这样占用的内存更少
        * 2.dvm是基于寄存器的，而jvm是基于栈的
        * http://rednaxelafx.iteye.com/blog/492667
        */
    }
    //endregion

    //region Android四大组件基本知识

    /**
     * 广播有几种注册方式？各有什么优点？
     * https://developer.android.google.cn/guide/components/broadcasts.html#receiving_broadcasts
     */
    public void a7() {
        /*
        * AndroidManifest中静态注册，代码中动态注册
        * 优点，代码注册的优先级比较高，而且有些隐式广播只能代码中注册
        * 缺点，注册广播的Activity的页面关闭后，广播就失效了
        * 静态的优点，是随时都能接收到广播
        *
        */
    }
    //endregion

    //region Android 操作系统
    /**
     * =====================
     * ### Android 操作系统
     * =====================
     * */

    /**
     * Android  6.0的权限机制？6.0之前的权限机制？权限机制的原理是什么？
     */
    public void a8() {
        /*
         * 权限请求
         * https://developer.android.google.cn/training/permissions/requesting.html?hl=zh-cn
         * -----------------------
         * 6.0后要动态申请权限，6.0之前可以在xml中申请权限，用户在安装的时候同意所有
         * 原理是调用系统api的时候回去判断这个应用有没有被授权，如果有则执行，没有就
         * 返回null或者异常。
         */
    }

    //endregion
}
