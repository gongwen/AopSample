Aspectj使用练习
=============
* 根目录build.gradle添加classpath 'org.aspectj:aspectjtools:1.8.10'
```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.3'
        classpath 'org.aspectj:aspectjtools:1.8.10'
    }
}
```
* app目录下build.gradle 添加
```
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
```

```
final def log = project.logger
final def variants = project.android.applicationVariants
variants.all { variant ->
    if (!variant.buildType.isDebuggable()) {
        log.debug("Skipping non-debuggable build type '${variant.buildType.name}'.")
        return;
    }
    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.5",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        log.debug "ajc args: " + Arrays.toString(args)
        MessageHandler handler = new MessageHandler(true);
        new Main().run(args, handler);
        for (IMessage message : handler.getMessages(null, true)) {
            switch (message.getKind()) {
                case IMessage.ABORT:
                case IMessage.ERROR:
                case IMessage.FAIL:
                    log.error message.message, message.thrown
                    break;
                case IMessage.WARNING:
                    log.warn message.message, message.thrown
                    break;
                case IMessage.INFO:
                    log.info message.message, message.thrown
                    break;
                case IMessage.DEBUG:
                    log.debug message.message, message.thrown
                    break;
            }
        }
    }
}
```
* 创建一个AndroidLibrary，例如aoplibrary，实现aop

Apt使用练习
=============
viewfinder-annotation 负责声明注解
viewfinder-compiler－>model->(BindViewField,OnClickMethod) 负责解析注解及其相关数据
viewfinder-compiler－>model->AnnotatedClass 通过提供的TypeElement和Elements生成class文件
viewfinder-compiler－>ViewFinderProcessor 遍历获取注解信息
ViewFinder 通过生成的class文件实现所需功能
#### 参考：

##### aspectj
* <a href="http://www.jianshu.com/p/0fa8073fd144">Android中的AOP编程</a>
* <a href="http://www.codexiu.cn/android/blog/19867/">Android 基于AOP监控之——AspectJ使用指南</a>
* https://github.com/JakeWharton/hugo

##### apt
* Annotation-Processing-Tool详解(http://qiushao.net/2015/07/07/Annotation-Processing-Tool%E8%AF%A6%E8%A7%A3/)
* Android 利用 APT 技术在编译期生成代码(http://brucezz.itscoder.com/use-apt-in-android)
* 安卓AOP三剑客:APT,AspectJ,Javassist(http://www.jianshu.com/p/dca3e2c8608a)
