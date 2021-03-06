package com.lyy.fengxiao.algorithm.queue;

public class MyCircularDeque {

    /**
     * 设计循环双端队列：641。
     * 设计实现双端队列。
     * 你的实现需要支持以下操作：
     * MyCircularDeque(k)：构造函数,双端队列的大小为k。
     * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
     * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
     * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
     * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
     * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
     * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
     * isEmpty()：检查双端队列是否为空。
     * isFull()：检查双端队列是否满了。
     */

    private final int[] num;
    private int head=0;
    private int tail=0;
    private int count=0;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        num=new int[k];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (count==num.length){
            return false;
        }
        if (count >= 0) System.arraycopy(num, 0, num, 1, count);
        num[0]=value;
        head=value;
        if (count==1){
            tail=head;
        }
        count++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (count==num.length){
            return false;
        }

        num[count]=value;
        tail=value;
        if (count==1){
            head=tail;
        }
        count++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (count==0){
            return false;
        }
        if (count - 1 >= 0) System.arraycopy(num, 1, num, 0, count - 1);

        count--;
        head=num[0];
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (count==0){
            return false;
        }

        count--;
        if (count!=0) {
            tail = num[count - 1];
        }
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (count==0){
            return -1;
        }
        return head;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (count==0){
            return -1;
        }
        return tail;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count == num.length;
    }

    public static void main(String[] args) {
        MyCircularDeque myCircularDeque=new MyCircularDeque(5);
        myCircularDeque.insertFront(7);
        myCircularDeque.insertLast(0);
        System.out.println(myCircularDeque.getFront());
        myCircularDeque.insertLast(3);
        System.out.println(myCircularDeque.getFront());
        myCircularDeque.insertFront(9);
        System.out.println(myCircularDeque.getRear());
        System.out.println(myCircularDeque.getFront());
        System.out.println(myCircularDeque.getFront());
        myCircularDeque.deleteLast();
        System.out.println(myCircularDeque.getRear());
//        myCircularDeque.insertFront(9);
//        myCircularDeque.deleteFront();
//        myCircularDeque.insertFront(8);
//        System.out.println(myCircularDeque.getRear());
//        myCircularDeque.insertFront(2);
//        System.out.println(myCircularDeque.isFull());
    }
}
