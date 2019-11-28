package com.ownutils.test;

public class test {

    public static void main(String[] args) {
        AnimalAbstract cat = new  Cat();
        cat.work();
        AnimalAbstract dog = new Dog();
        dog.work();


        GenericsSub generics = new GenericsSub();
        generics.setE(0);
        generics.setE(0.2F);
        generics.setE(10);
//        generics.setE(0.2F);
        System.out.println( generics.getE().getClass().getName());
        Integer d = 1;
        System.out.println(d.getClass().isInstance(generics.getE()));
    }
}
