package com.hibernate.main;

import java.sql.*;
import org.hibernate.*;
import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		StudentDemo stud = new StudentDemo();
		stud.setName("Andrew Anderson");
		
		PassportDemo passport = new PassportDemo();
		passport.setPassportNumber("PASS12345");
		stud.setPassportDemo(passport);
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.persist(passport);
			session.persist(stud);
			
			tx.commit();
			StudentDemo s = session.get(StudentDemo.class, 1); 
			/*
			 here we need to put id of predefined data as , on writing stud.getSid() will set id of currently created object and at fetch and eager case data created by hibernate is for both student and passport internally so changes will not shows properly 
			note : @OneToOne will executes Eager initialization
			*/
			System.out.println("--------> StudentId : "+s.getSid()+" StudentName : "+s.getName());
			System.out.println("--------> PassportId : "+s.getPassportDemo().getPid()+" PassportNumber : "+s.getPassportDemo().getPassportNumber());
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback takes place..!!");
			}
		}
	}
	public static void createDatabaseIfNotExist() {
		String URL = "jdbc:mysql://localhost:3306/";
		String DATABASE = "hibernate330";
		String USER = "root";
		String PASS = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			if(con!=null)
				System.out.println("Connection established Successfully");
			String query = "create database if not exists "+DATABASE;
			Statement stmt = con.createStatement();
			stmt.execute(query);
			System.out.println("Database created succesfully");
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception : "+e);
		}
	}
}


/*
 // fetch type : eager 
 
 Hibernate: 
    create table passportDemo (
        pid integer not null auto_increment,
        passportnumber varchar(255),
        primary key (pid)
    ) engine=InnoDB
Hibernate: 
    create table StudentDemo (
        sid integer not null auto_increment,
        studentname varchar(255),
        passport_id integer,
        primary key (sid)
    ) engine=InnoDB
Hibernate: 
    alter table StudentDemo 
       drop index UK_d2og321h8o12wqlgdcm025cb5
Hibernate: 
    alter table StudentDemo 
       add constraint UK_d2og321h8o12wqlgdcm025cb5 unique (passport_id)
Hibernate: 
    alter table StudentDemo 
       add constraint FK94ghm0c1qcpkgr4uvi42fvkwv 
       foreign key (passport_id) 
       references passportDemo (pid)
Hibernate: 
    insert 
    into
        passportDemo
        (passportnumber) 
    values
        (?)
Hibernate: 
    insert 
    into
        StudentDemo
        (studentname, passport_id) 
    values
        (?, ?)
--------> StudentId : 1 StudentName : Andrew Anderson
--------> PassportId : 1 PassportNumber : PASS12345 
----------------------------------------------------------

 fetch type : lazy
 
 Hibernate: 
    insert 
    into
        passportDemo
        (passportnumber) 
    values
        (?)
Hibernate: 
    insert 
    into
        StudentDemo
        (studentname, passport_id) 
    values
        (?, ?)
Hibernate: 
    select
        sd1_0.sid,
        sd1_0.studentname,
        sd1_0.passport_id 
    from
        StudentDemo sd1_0 
    where
        sd1_0.sid=?
--------> StudentId : 1 StudentName : Andrew Anderson
Hibernate: 
    select
        pd1_0.pid,
        pd1_0.passportnumber 
    from
        passportDemo pd1_0 
    where
        pd1_0.pid=?
--------> PassportId : 1 PassportNumber : PASS12345

 
*/
