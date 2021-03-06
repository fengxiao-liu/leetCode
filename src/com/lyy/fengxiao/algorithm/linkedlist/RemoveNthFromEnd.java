package com.lyy.fengxiao.algorithm.linkedlist;

public class RemoveNthFromEnd {

    /**
     * 问题描述：19。
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：给定的 n 保证是有效的。
     *
     * 解题思路：
     * 倒数第n个可以转换成正数第m个。假设链表的节点个数为sum，那么m=sum-n+1。
     * 这样删除倒数第n个的问题就可以转换为删除正数第m个节点。
     * 然后直接循环到第m个节点删除节点即可，不过需要一个节点来存当前节点的前一个节点。
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
          val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head==null){
            return null;
        }
        //删除倒数第n个，就是从前往后第 节点个数-n+1 位置上的节点
        int count=0;
        ListNode first=head;
        while (first!=null){
            count++;
            first=first.next;
        }

        int index=count+1-n;
        if (index==1){
            return head.next;
        }

        first=head;
        count=1;
        ListNode pre=head;
        while (first!=null){
            if (count==index){
                pre.next=first.next;
                break;
            }
            count++;
            pre=first;
            first=first.next;
        }

        return head;
    }
}
