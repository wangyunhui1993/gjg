package com.demo.server;


import java.util.List;

import entity.Stu;

public interface DBService {
     boolean add(String sname, String snumber);

    boolean add2(String sname, String snumber);

    List<Stu> select1(int page, int size, String where);

    boolean update1(List<Stu> stuList);

    boolean delete1(String id);
}
