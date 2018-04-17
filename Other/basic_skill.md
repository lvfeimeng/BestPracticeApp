### 阅读和理解开源库的方法
   首先开源库都会实现某个功能，我们先要理解这个库是干嘛的
   
   然后我们看demo，看示例代码，示例代码一般是实现这个功能的api
   
   然后我们看整个库（工程）的结构，根据单一职责的原则，一个工程要
实现某个功能要分为很多模块，各个模块都有不同的职责。
根据开闭原则和依赖倒置，各个模块之间的依赖肯定是依赖于接口或者抽象类
所以我们看接口或者抽象类的定义，根据接口名来初步判断这个模块是负责什么
职责的。
   
   这样我们能大概分清楚这个工程有几个模块构成。
根据里式替换原则，各个模块的抽象肯定有一个或者多个实现。
那么我们ctrl+t 观察他的所有子类，就知道这个模块具体实现类有哪些

   我们根据以往经验来根据命名来判断这个模块是什么职责
比如Transformer（变化器，对传入对象进行处理，返回处理后的对象），
Converter（转化器，一种数据类型转化为另一种，比如String转Json），
Dispatcher(分发器，读取数据，然后分发给指定的处理类)
XXXSource（肯定是负责提供某种资源的类）
XXXMaster(根据迪米特法则，肯定是负责创建和协调各个模块的类)
XXXBuilder(肯定是构建者模式来构造摸个对象的类)
XXXStrategy（肯定是策略模式中用到的类，具体XXX这种行为的策略，比如EncodeStrategy）
XXXFetcher(是获取XXX资源的类)


