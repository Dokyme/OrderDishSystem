package com.odss.seu.DAO;

public class User {

    public static User mock() {
        User user = new User();
        user.setId(1);
        user.setAccount("admin");
        user.setName("管理员");
        user.setPassword("123");
        user.setType(0);
        return user;
    }

    private int id;
    private String name;
    private String account;
    private String password;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
