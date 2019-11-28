package com.ownutils.test;

public class Generics<K, E>  {
    private K k;
    private E e;

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
