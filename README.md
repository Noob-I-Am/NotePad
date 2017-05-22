#   记事本应用

####以谷歌NotePadDemo为基础进行修改


>已实现的基本功能修改 ：显示修改时间戳，添加标题搜索功能
>其他修改：界面美化，修改背景色并储存进数据库；


##一, 显示时间戳

![笔记条目](https://raw.githubusercontent.com/Noob-I-Am/NotePad/master/image/%E6%9D%A1%E7%9B%AE.PNG )

如图所示，在笔记条目的右下角显示了最后进入修改编辑状态的时间


##二,标题搜索功能

**通过TitleBar上的搜索按键进入实现搜索功能的activity**

<img src="https://raw.githubusercontent.com/Noob-I-Am/NotePad/master/image/mysearch.gif" width="50%"  />


##三,背景色更换

<img src="https://raw.githubusercontent.com/Noob-I-Am/NotePad/master/image/change.gif" width="50%"  />


##四，界面美化

应用的主要两个界面NoteEditor和NotesList都使用了黑色色调的标题栏

NotesList使用ThemeOverlay.Material.Dark的主题，而NoteEditor使用Theme.Holo.Light.DarkActionBar主题。

相应的在主界面的布局文件中将滑动条设置成黑色： 

`android:scrollbarThumbVertical="@android:color/black"`

主界面笔记条目显示部分，条目的方框使用自定义shape文件设置圆角

 `<corners android:radius="5dp"/>`

listView 的item布局文件中的background元素使用了该shape文件

  效果展示： ![](https://raw.githubusercontent.com/Noob-I-Am/NotePad/master/image/corner.PNG )

另外在布局文件中设置了整体的z轴高度

    android:translationZ="3dp"

    android:elevation="3dp"`
让笔记条目显示相对于背景的层次高度，显示出阴影，呈现立体感。

效果展示：

![](https://raw.githubusercontent.com/Noob-I-Am/NotePad/master/image/shadow.PNG )

notelist的背景部分使用了图片作为背景

![](https://raw.githubusercontent.com/Noob-I-Am/NotePad/master/image/pic.PNG )
