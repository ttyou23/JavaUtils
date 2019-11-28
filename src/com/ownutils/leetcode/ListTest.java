package com.ownutils.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    private NodeTest head;
    private NodeTest end;
    private int size;

    public class NodeTest {
        public int data;
        public NodeTest next;
    }

    public void add(int data) {
        NodeTest node = new NodeTest();
        node.data = data;
        if (head == null) {
            head = node;
            end = node;
            size = 1;
        } else {
            end.next = node;
            end = node;
            size++;
        }
    }

    public void diplay() {
        NodeTest temp = head;
        List list = new ArrayList();
        do {
            list.add(temp.data);
//            System.out.println(temp.data);

        } while ((temp = temp.next) != null);
        System.out.println(Arrays.toString(list.toArray()));
    }

    public void work(int count) {

        int index = 0;
        NodeTest pre_end = null, cur_head = head, pre_temp, temp = null;
        int old_n = -1;

        NodeTest next_node = head;
        pre_temp = head;
        int yushu = size % count;
        System.out.println("yushu: " + yushu);
        while (next_node != null) {
            temp = next_node;
            next_node = next_node.next;
            if (index < yushu) {
                pre_end = temp;
            } else {
                int n = (index - yushu) / count;
                int m = (index - yushu) % count;
                if (n == old_n) {
                    if (m == count - 1) {
                        temp.next = pre_temp;
                        if (pre_end == null){
                            head = temp;
                        } else {
                            pre_end.next = temp;
                        }
                        pre_end = cur_head;
                        pre_temp = temp;
                    } else {
                        temp.next = pre_temp;
                        pre_temp = temp;
                    }
                } else if (n > old_n) {
                    old_n = n;
                    cur_head = temp;
                    pre_temp = temp;
                    pre_temp.next = null;
                }
            }
            index++;
        }
    }

    public void rehead(){

        NodeTest pre_node, temp_node, next_node;
        temp_node = head;
        next_node = head.next;
        while (next_node != null){
            pre_node = temp_node;
            temp_node = next_node;
            next_node = next_node.next;

            temp_node.next = pre_node;
        }

        head.next = null;
        head = temp_node;
    }


    public static void main(String[] args) {
        ListTest listTest = new ListTest();
        for (int i = 0; i < 20; i++) {
            listTest.add(i + 1);
        }
        listTest.diplay();
//        listTest.work(5);
        listTest.rehead();
        listTest.diplay();
    }
}
