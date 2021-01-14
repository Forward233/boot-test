package com.boot.demo.thread;

/**
 * @author: yhl
 * @DateTime: 2021/1/13 17:37
 * @Description:
 */
class ChildThread extends Thread
{
    private String name = null;

    public ChildThread(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        System.out.println(this.name + "--child thead begin");

        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }

        System.out.println(this.name + "--child thead over");
    }
}

