package com.akingyin.androidjetpackdemo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author king
 * @version V1.0
 * @ Description:
 * @ Date 2018/11/19 17:11
 */
public class User implements Serializable {

  private static final long serialVersionUID = -5146398605421970521L;
  public   String    name;


  public Date    createDay;

  public   int    age;
}
