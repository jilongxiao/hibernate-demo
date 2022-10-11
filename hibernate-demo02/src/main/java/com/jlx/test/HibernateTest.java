package com.jlx.test;
import java.util.List;

import com.jlx.vo.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.jlx.vo.JlxUsertable;
import com.jlx.util.HibernateSessionFactory;
public class HibernateTest {
    public Session session;
    public static void main(String[] args) {
        HibernateTest ht=new HibernateTest();		//创建类对象
        ht.getCurrentSession();						//获得session对象
        //ht.saveUser();								//插入一条记录
        //System.out.println("增加一条记录后结果======");
        //ht.queryUser();               				//查看数据库结果
        //ht.updateUser();              				//修改该条记录
        //System.out.println("修改该条记录后结果======");
        //ht.queryUser();								//查看数据库结果
        ht.deleteUser();              				//删除该条记录
        System.out.println("删除该条记录后结果======");
        ht.queryUser();								//查看数据库结果
        ht.closeSession();             				//关闭session
    }

    //获得session方法
    public void getCurrentSession(){
        //调用 HibernateSessionFactory 的 getSession 方法创建 Session 对象
        session = HibernateSessionFactory.getSession();
    }
    //关闭session方法
    public void closeSession(){
        if(session!=null){
            HibernateSessionFactory.closeSession();	// 关闭Session
        }
    }

    //插入一条记录方法
    public void saveUser(){
        Transaction t1 = session.beginTransaction();// 创建事务对象
        JlxUsertable user = new JlxUsertable();
        user.setUsername("jilongxiao");
        user.setPassword("123456");
        session.save(user);
        t1.commit();								// 提交事务
    }
    //修改这条记录方法
    public void updateUser(){
        Transaction t2 = session.beginTransaction();
        JlxUsertable user = (JlxUsertable)session.get(JlxUsertable.class, 5);
        user.setUsername("JILONGXIAO");
        session.update(user);
        t2.commit();
    }
    //查询数据库结果方法
    public void queryUser(){
        try{
            Query query = session.createQuery("from JlxUsertable");
            List list=query.list();
            for(int i=0;i<list.size();i++){
                JlxUsertable user = (JlxUsertable)list.get(i);
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //删除该条记录方法
    public void deleteUser(){
        Transaction t3 = session.beginTransaction();
        JlxUsertable user = (JlxUsertable)session.get(JlxUsertable.class, 5);
        session.delete(user);
        t3.commit();
    }
}
