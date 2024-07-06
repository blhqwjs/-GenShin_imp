# -GenShin_imp
2023年移动应用开发课设：《原神游戏小助手》  

一、相关配置要求说明：  
    Android Studio 3.5.2  
    Build #Al-191.8026.42.35.5977832, built on October 31,2019  
    JRE: 1.8.0_202-release-1483-b03 amd64  
    JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o  
二、背景  
    在宏观层面，数字化娱乐和游戏产业的迅速发展对社会和文化产生了广泛影响。游戏已不再局限于娱乐消遣，而逐渐成为着一种具有社交、经济和文化影响力的现象表征。  
    数字游戏作为一种新兴的娱乐形式，融合了技术、艺术和设计，呈现出多元化和跨平台的特点。其在娱乐、教育、社交等领域都展现出了巨大的潜力。特别是移动平台的普及，使得游戏在全球范围内更加便捷地被广泛接受和使用。  
    与此同时，随着游戏行业的壮大，游戏设计和开发变得越发重要。研究者和开发者们致力于创造更具吸引力、深度和创新性的游戏体验，以满足不断增长的玩家需求。游戏辅助工具的开发也成为了一项关键的研究方向，旨在提供更便捷、智能的游戏管理和体验优化。  
    《原神游戏小助手》便于此背景下诞生，以满足玩家对于更深层次游戏体验的追求，为玩家提供了一个便捷的平台，帮助他们更好地理解、管理和享受游戏乐趣。在此基础上，让玩家能够深入了解游戏内的元素相互作用和角色特性。鼓励玩家通过模拟和探索，培养逻辑思维和策略规划能力。  
三、系统设计  
3.1 概要设计  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/765ac98a-d5ff-4631-9085-76b8f12f7294)  
3.2 详细设计  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/77e181f3-5c8a-4cb2-8803-314cb4a19b4c)   
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/29b96daa-a399-49c1-94f6-b711d270719b)   
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/7ad1008e-22de-4df6-9f21-72c5c1e7caf6)   
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/d278a204-1358-4f78-ab9e-7fb55e2d1d46)   
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/9819b1d4-729d-4b70-951b-af606605a979)   

四、功能说明  
4.1 角色管理模块实现  
（1）主页面展示：  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/748c3011-1a07-4156-b04d-c92b70901baa)  
在主页面（图4-1）中点击添加角色（左下角分区）按钮，跳转至添加角色功能页面，点击查看角色列表后可跳转到角色列表一栏。  
（2）添加角色页面  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/a5cbbeb8-9002-4412-8475-f9d3b77da0d5)  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/40c719e2-e7f3-4351-b239-619dde37b807)  
可就页面中提示来输入，点击保存，保存成功后返回主页面并显示角色添加成功  
（3）角色列表页面  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/3f3d1853-a628-4503-8deb-328f29562227)  
主页面点击后，可在此处显示已添加角色的摘要信息，上方对应显示搜索栏。  
输入信息点击搜索后显示对应搜索结果。如搜索：可莉（图4-5）  
 
（4）角色详情页面：  
① 在角色列表页面（图4-4）中，可就显示内容进行详情查看，（以keli为例）点击头像后跳转至角色详情页面（图4-6）。  
  ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/5e4b26ac-aa54-4d8d-b3ff-81c8206c2cbc)  

② 点击修改属性，可对角色属性进行调整（图4-7）  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/4ab4be13-187e-4d38-bb67-603db7ab11b1)  
 
③ 进行简单修改（图4-8），并点击确认修改。  
 刷新页面后，数据更新为修改之后的数据（图4-9）。  
④ 点击删除角色，提示是否删除（图4-10），点击确认后，角色被删除，列表中不再显示该角色（keli）信息，点击搜索验证（图4-11）。  
  4.2 怪物管理模块实现  
（1）页面选择：  
在主页面（图4-1）中点击增加怪物（左上角分区）按钮，跳转至增加怪物页面；点击管理怪物（右上角）后可跳转到怪物一栏。  
（2）增加怪物页面  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/663918c6-0acc-49f3-9387-dc0f0d88ea3b)
在页面中，可就页面中提示信息进行输入（图4-12），点击添加，添加成功后返回主页面并显示怪物添加成功（图4-13）。  
 （3）怪物列表页面  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/61624231-80a9-4b4a-afc0-dc0ec0aabaf3)  
在主页面（图4-1）点击“管理怪物”区域按钮后，可显示已添加怪物摘要  
（4）怪物属性页面：  
① 在怪物列表页面（图4-14）中，可就显示内容进行查看和修改，（以“他们也曾完美无瑕”为例）点击“查看/修改”按键后跳转至怪物属性页面（图4-15）。  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/6b4d9e61-442d-451b-ba49-cab93ba6cfae)  
② 在页面中，可以直接进行编辑属性操作，可对角色属性进行调整（图4-16）  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/d65d7143-3f23-481c-adc0-3b95f2bbff01)  
进行简单修改（图4-17），并点击保存修改，页面更新为修改后的属性。  
③ 点击删除怪物，提示是否删除（图4-18）点击确认后，怪物被删除。返回列表页面，显示当前列表中不再显示该怪物（他们也曾完美无暇）信息（图4-19）。  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/890e3963-1017-4b91-8567-903fadae1974)
4.3 角色伤害模拟模块实现  
（1）基础说明：  
① 该模块内容于角色详情页面（图4-6）中体现，在角色列表页面中选择角色（如：胡桃）  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/51629f16-89ab-4211-9037-92ae94bee3c7)  
② 于角色详情页面选择怪物，点击后下拉框，弹出怪物列表（如图4-21）  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/3e6d52ee-51f6-4736-a518-ae2054e09e48)  
（2）伤害模拟与元素反应：  
① 选定怪物（如：冰史莱姆lv66），点击“打怪”按钮，按照角色、怪物的属性和元素种类，于下方显示触发的反应和伤害值。  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/0b0af29e-09ee-46d2-85bc-e240c4734b0b)  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/0a6f6ed6-5a12-4f02-bc64-479a37e52a21)  
② 更改怪物种类，实现不同的模拟。  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/356b6b16-90b1-45c0-863d-b4b2d2b1c70d)  
图 4-25 伤害模拟-触发免疫  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/41245583-e2fb-42e9-a61d-3c22ead3ceb0)  
图 4-26 伤害模拟-超载反应+触发暴击  
③ 更改角色，如：夜兰，再次试验  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/a8b1c178-0447-4616-8e57-70431652a682)  
图 4-27 伤害模拟-导电+触发暴击  
④ 修改属性，再次点击“打怪”，对应发生变化  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/865ac64d-2c6f-4899-8636-64f0d94c569e)  
图 4-28 伤害模拟-属性修改后  
4.4 角色PV播放模块实现  
（1）基础说明：  
该模块内容于角色详情页面（图4-6）中体现，在角色列表页面中选择角色（如：胡桃），在页面中点击“角色PV”按钮实现对应的角色PV播放。  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/9ca0e9bf-d6ec-4a6f-b25b-bbdd5f0b9ee3)  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/4a1b564c-f210-41fc-a5c9-39ee0569a62e)  
点击开始按键，可播放对应的PV  
![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/17050745-05d0-4cfd-afbd-f33b99221790)  
点击暂停，即为停止播放  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/7ab4381c-a9f5-46c8-a626-e6c4669de337)  
（2）切换不同角色，可观看不同角色PV，  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/5b90c276-b1eb-497f-afce-7d5cb4592e11)  
如果角色没有对应PV，则提示视频无法播放    
4.5 WebView预览模块实现  
该模块内容于角色详情页面（图4-6）中体现，选中随意角色后点击“瞅瞅官网”即可操作。  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/882e9c94-c6a1-43bd-bac0-3a100342757c)  
图 4-35 瞅瞅官网按键位置  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/2ecc94a8-a0d9-43db-b6b5-82c5863bde62)  
图 4-36 web view预览  
 ![image](https://github.com/blhqwjs/-GenShin_imp/assets/100326209/cc40dbf1-2ae2-4253-bcbe-40bd75198b05)  
图 4-37 webview 内操作后显示  


 
