package cn.baizhi.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect   //代表这个类是切面类
@Component   //为该类创建对象
public class CacheAspect {

    @Autowired   //redis操作对象
private RedisTemplate redisTemplate;

    /*
    * 切点表达式
    * execution():方法级别
    * within():类级别
    * @annotation:注解方式
    *
    * */

    //环绕通知
    @Around("execution(* cn.baizhi.service.*Impl.query*(..))")
    public Object addCache(ProceedingJoinPoint joinPoint){

        System.out.println("环绕通知");

        //  key唯一 ：全限定名+方法名+实参
        StringBuilder sb = new StringBuilder(); //把全限定名+方法名+实参值拼接到一起 做到key唯一


        //全限定名 包名和类名
        String ClassName = joinPoint.getTarget().getClass().getName();
        System.out.println(ClassName);

        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);

        sb.append(ClassName).append(methodName);
        //获取实参值
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
            sb.append(arg);
        }
        System.out.println(sb);



        //如果redis中已经有这个缓存数据 就从redis中 拿  没必要放行了
        //sb.tostring:当前key

        Object obj = null;
        //取消键的序列化

      //  ValueOperations valueOperations = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (hashOperations.hasKey(ClassName, sb.toString())){
            //如果有这个key 根据建获取值
          obj = hashOperations.get(ClassName, sb.toString());
        }else {  //如果没有这个key  放行请求
            try {
                obj = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            //redis作缓存
            hashOperations.put(ClassName, sb.toString(), obj);

        }
        return obj; //这个数据会到达controller
    }


    //只要执行了增删改  就应该清除缓存 只要用户模块信息改变了 对于用户的查询都会受到影响
    //所以如果用户模块数据发生了变化 那么关于用户模块所有的查询缓存都应该删除
    //开发额外功能 ：删除redis缓存 切点 ：增删改方法  后置通知
    @After("@annotation(cn.baizhi.annotation.DeleteCache)")
    public void delCache(JoinPoint joinPoint){
        System.out.println("后置通知");
        //清除缓存
        //获取类的全限定名
        String ClassName = joinPoint.getTarget().getClass().getName();


        redisTemplate.delete(ClassName);

        //拿到所有key
//        Set keys = redisTemplate.keys("*");
//        for (Object key : keys) {
//            String newKey = (String)key;
//            if (newKey.startsWith(ClassName)){
//                redisTemplate.delete(key);
//            }
//        }

    }




}
