
imcache
=======
Imcache是一个Java缓存库。Imcache通过提供一种管理缓存数据的方法来加快程序。提供从小型应用到大型应用的解决方案。支持n级缓存层次结构，支持各种类型的缓存方法，如堆，offheap等。Imcache还支持第三方的缓存解决方案，redis等。要进行扩展，可以定义脱离缓存支持的heapcache，该缓存也支持数据库。如果在堆中没有找到密钥，将被要求脱离。

### Pom参考
使用imcache,需要指定如下依赖关系:
```xml
<dependency>
  <groupId>com.KyleDing</groupId>
  <artifactId>imcache</artifactId>
  <version>0.2.0</version><!--Can be updated for later versions-->
</dependency>
```
### 简单实例
```java
Cache<String,User> cache = CacheBuilder.heapCache().
cacheLoader(new CacheLoader<String, User>() {
    public User load(String key) {
        return userDAO.get(key);
    }
}).evictionListener(new EvictionListener<String, User>{
    public onEviction(String key, User user){
        userDAO.save(key, user);
    }
}).capacity(10000).build();
//如果堆缓存中没有用户，将通过userDAO加载.
User user = cache.get("#unique identifier");
User newUser = new User("email", "Richard", "Murray")
//当达到缓存的最大值时，发生驱逐事件.
//在驱逐的情况下，newUser将被保存到db.
cache.put(newUser.getEmail(), newUser);
```
### Cache接口
Imcache支持由缓存接口定义的简单操作。缓存接口提供由所有imcache缓存实现的一般方法。参阅下面的方法。

```java
public interface Cache<K, V> {
    void put(K key, V value);
    V get(K key);
    V invalidate(K key);
    void clear()
    double hitRatio();
    String getName();
    int size();
}
```
### 缓存构建器
缓存构建器是imcache的核心资源之一。可以通过缓存生成器创建简单的heapCaches到complexOffHeapCaches。看下面的缓存生成器。

```java
void example(){
    Cache<Integer,Integer> cache = CacheBuilder.heapCache().
    cacheLoader(new CacheLoader<Integer, Integer>() {
  	//这里可以从另一个缓存加载密钥，比如offheapcache
        public Integer load(Integer key) {
            return null;
        }
    }).capacity(10000).build();
}
```
### 缓存加载器
用于使用指定的键加载值的CacheLoader接口。从资源加载值的类实现此接口。当没有找到数据时，CacheLoader的加载方法被调用。

### Eviction Listener (驱逐事件监听)
用于接收驱逐事件的侦听器接口。对处理驱逐事件实现这个接口。当驱逐事件发生时，调用该对象的onEviction方法。

### HeapCache (堆缓存)
HeapCache通过LinkedHashMap的帮助使用LRU(Least Recently Used:最近时间段使用最少)作为驱逐策略。因此，当需要逐出时，HeapCache首先丢弃最近最少使用的项目。如果高速缓存的大小等于put操作中的高速缓存容量，则会发生驱逐。

### Concurrent Heap Cache(堆缓存并发)
ConcurrentHeapCache通过ConcurrentLinkedHashMap的帮助使用LRU(Least Recently Used:最近时间段使用最少)作为驱逐策略。因此，ConcurrentHeapCache首先在需要逐出丢弃最近最少使用的项目。如果高速缓存的大小等于put操作中的高速缓存容量，则会发生清除。

### Off Heap Cache(堆缓存)
OffHeapCache是一种高速缓存，它通过将项目序列化为字节来使用脱离字节缓冲区来存储或检索数据。为此，OffHeapCache使用指针来指向一个项目的数组位置。OffHeapCache定期清除缓冲区以获得可用空间，如果缓冲区脏（未使用的内存）。它还根据对对象的访问时间执行驱逐。要使脱离缓存工作到JVM 必须设置参数**“-XX：MaxDirectMemorySize = 4g”**。缓冲区容量为8 mb是启动OffHeapCache的推荐选择。

OffHeapCache的使用实例:

```java
void example(){
    //8388608 is 8 MB and 10 buffers. 8MB*10 = 80 MB.
    OffHeapByteBufferStore bufferStore = new OffHeapByteBufferStore(8388608, 10);
    final Cache<Integer,SimpleObject> offHeapCache = CacheBuilder.offHeapCache().
    storage(bufferStore).build();
}
```
在默认配置下，OffHeapCache将尝试定期清理未使用和标记为脏的地方。此外，它也会定期执行驱逐。

### Versioned Off Heap Cache(版本化堆缓存)
VersionedOffHeapCache是一种offheap缓存，其中缓存项具有为每个更新增加的版本。要使版本化的关闭堆缓存工作到JVM 必须设置参数**“-XX：MaxDirectMemorySize = 4g”**。缓冲容量为8 MB是启动VersionedOffHeapCache的推荐选择。

VersionedOffHeapCache的使用示例：

```java
void example(){
    //8388608 is 8 MB and 10 buffers. 8MB*10 = 80 MB.
    OffHeapByteBufferStore bufferStore = new OffHeapByteBufferStore(8388608, 10);
    final Cache<Integer,VersionedItem<SimpleObject>> offHeapCache =
    CacheBuilder.versionedOffHeapCache().storage(bufferStore).build();
    VersionedItem<SimpleObject> versionedItem = offHeapCache.get(12);
}
```

### Redis 缓存
RedisCache是一个使用redis服务器通过将项序列化为字节来存储或检索数据的缓存。查看[redis文档](http://redis.io/documentation)，并[下载redis服务器](http://redis.io/download)。Redis Cache没有任何管理或处理redis集群的能力。Redis缓存目前只能与一个redis服务器通信。

```java
void example(){
    Cache<Integer, String> cache = CacheBuilder.redisCache().
			hostName(HOSTNAME).port(PORT).build();
    cache.put(1, "apple");
    System.out.println(cache.get(1));
}
```

### 搜索，索引和查询
默认情况下，imcache提供搜索所有缓存。搜索由SearchableCache的execute方法完成。Execute方法将Query作为输入，并将结果作为列表返回。查询由条件和过滤器组成。以下是查询的示例用法。

```java
void example(){
    SearchableCache<Integer, SimpleObject> cache = CacheBuilder.heapCache().
    addIndex("j", IndexType.RANGE_INDEX).build();
    cache.put(0, createObject());
    cache.put(1, createObject());
    cache.put(2, createObject());
    List<SimpleObject> objects = cache.execute(CacheQuery.newQuery().
    setCriteria(new BetweenCriteria("j",1,3).or(new ETCriteria("j", 3))).
    setFilter(new LEFilter("k", 3)));
    for (SimpleObject simpleObject : objects) {
        System.out.println(simpleObject);
    }
}
```
请注意，不能执行对位于JVM外部的缓存的查询。因此，查询redis和memcache是不可能的。


