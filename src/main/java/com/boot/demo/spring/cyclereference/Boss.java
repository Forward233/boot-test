package com.boot.demo.spring.cyclereference;

/**
 * @author: yhl
 * @DateTime: 2020/8/25 11:18
 * @Description:
 */
public class Boss {

    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
