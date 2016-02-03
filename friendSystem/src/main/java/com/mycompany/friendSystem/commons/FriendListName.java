package com.mycompany.friendSystem.commons;

/**
 * Created by JinBingBing on 2016/2/3.
 */
public enum FriendListName {
    FRIEND("我的好友",1),STRANGER("黑名单",2),BLACKLIST("陌生人",3);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private FriendListName(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (FriendListName f : FriendListName.values()) {
            if (f.getIndex() == index) {
                return f.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
