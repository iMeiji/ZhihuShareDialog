## 仿知乎分享控件

使用 BottomSheetDialog 实现仿知乎分享控件  
主要步骤：
1. 首先获取手机内所有支持分享的应用，得到 ResolveInfo 对象，利用反射获取应用图标等信息
2. 然后用 RecyclerView 的 GridLayoutManager 网格布局展示，自己实现点击事件就差不多完成了

## 截图

| ![知乎](/art/1.jpg) | ![仿](/art/2.jpg) | ![原生](/art/3.jpg) |
| :---------------: | :--------------: | :---------------: |
|      **知乎**       |      **仿**       |      **原生**       |



## 缺点

- 通过 PackageManager 的 queryIntentActivities 方法获取到的应用是按照应用安装的先后顺序，而 Lollipop(5.0) 开始原生分享已支持自动把常用应用排在最顶部。当然也可以自己实现应用的排序，但比较折腾，可参考源码 [ResolverActivity](https://github.com/android/platform_frameworks_base/blob/lollipop-release/core/java/com/android/internal/app/ResolverActivity.java#L1052)



## 优点

- 可以自定义分享界面，比如像知乎那样添加广告栏，设计一套属于自己的 UI ，设置应用的排列顺序等等