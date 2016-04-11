package com.test.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import com.test.domain.Pic;
import com.test.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		//mybatis的配置文件
//		String resource = "conf.xml";
//        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
//		InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
//		//构建sqlSession的工厂
//		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
//		//创建能执行映射文件中sql的sqlSession
//		SqlSession session = sessionFactory.openSession();
//		try{
//			InputStream in = new FileInputStream("1.png");
//			byte[] file = new byte[in.available()];
//			in.read(file);
//			Pic pic = new Pic();
//			pic.setFile(file);
//			pic.setId(1);
//			String statement = "com.test.mapping.userMapper.addPic";
//			int retResult = session.insert(statement, pic);
//			session.commit();
//			System.out.println(retResult);
//		}
//		
//		catch (Exception e){
//			System.out.println("Error::"+e);
//		}	
//		
//		String statement = "com.test.mapping.userMapper.getPic";
//		Pic pic = session.selectOne(statement, 3);
//		byte[] b = pic.getFile();
//		try {
//			OutputStream out = new FileOutputStream("2.png");
//			out.write(b);
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			System.out.println("Error::"+e);
//		}
		testAdd();
		testSelect();
	}
	public static void testAdd(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);	
		String statement = "com.test.mapping.userMapper.addPic";
		InputStream in = null;
		byte[] file = null;
		try{
			in = new FileInputStream("1.png");
			file = new byte[in.available()];
			in.read(file);
			in.close();
		}
		catch (Exception e){
			System.out.println("Error::"+e);
		}
		Pic pic = new Pic();	
		pic.setFile(file);
		pic.setId(1);
		int retResult = sqlSession.insert(statement, pic);
		sqlSession.close();		
	}
	
	public static void testSelect(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "com.test.mapping.userMapper.getPic";
		Pic pic = sqlSession.selectOne(statement, 1);
		byte[] b = pic.getFile();
		try {
			OutputStream out = new FileOutputStream("2.png");
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("Error::"+e);
		}
	}
}
