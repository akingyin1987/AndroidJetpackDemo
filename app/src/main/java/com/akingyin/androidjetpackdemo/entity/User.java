package com.akingyin.androidjetpackdemo.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.akingyin.androidjetpackdemo.BR;
import java.io.Serializable;
import java.util.Date;


/**
 * @author king
 * @version V1.0
 * @ Description:
 * @ Date 2018/11/19 17:11
 */

@Entity(tableName = "tb_user")
public class User extends BaseObservable implements Serializable {

  private static final long serialVersionUID = -5146398605421970521L;

  @PrimaryKey(autoGenerate = true)
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  private    String    name;


  private Date    createDay;

  private    int    age;

  public User() {
  }

  @Bindable
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
     notifyPropertyChanged(BR.name);

  }

  @Bindable
  public Date getCreateDay() {
    return createDay;
  }

  public void setCreateDay(Date createDay) {
    this.createDay = createDay;
  }

  @Bindable
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
