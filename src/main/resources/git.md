# git命令

### 常用

- git status :查看状态，可以显示文件有那些改动
- git init ：初始化
- git add . :将文件从本地放到暂存区
- git commit -m "提交描述，不能为空"
- git log :查看所有提交记录
    - git log --author="提交人的名字" ：查看单个人改动记录
- git push origin

- git config 使用
  > 1、git config --global user.name="0-SoS-0" :配置用户名（一般是git登录账户）
  >
  > 2、git config --global user.email="15924630084@163.com" :配置邮箱（一般是git登录邮箱）
  >
  > 3、git config --global --list :查看当前配置的用户信息

### 文件操作

> 1、git rm 文件名 ：删除指定文件
>
>2、git mv filename1 filename2 :将文件重命名 或者是移动文件并重命名

#### 查看文件前后变化

- git log --pretty=oneline 文件名 ：得到上次提交的id
- 2、git show id :得到该文件修改信息

#### 文件还原

 - git diff :展示文件修改前后不同，然后手动修改
 - 命令行还原（针对未提交的）
   - git status:找到修改过的文件
   - git checkout -- 文件名 ：还原到上一提交版本
 - 撤销追踪（执行git add . 后添加到暂存区，就会对文件实施追踪 ）
    - git reset HEAD 文件名（全路径）:撤销追踪
    - git checkout -- 文件名 ：还原到上一提交版本
####版本回退
 - git reset --hard HEAD^(版本ID)：回退到上一个版本或者回到指定版本