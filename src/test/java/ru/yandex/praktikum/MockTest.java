package ru.yandex.praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/*
Тебе нужно написать автотест для единственного в мире малыша Котопса.
Проверь: методы createHalfCat() и createHalfDog() вызываются по одному разу. Используй моки.
 */


@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    @Mock
    private Cat cat;
    @Mock
    private Dog dog;

    @Test
    public void test() {
        Cat halfCat = cat.createHalfCat();
        Dog halfDog = dog.createHalfDog();
        Mockito.verify(cat, Mockito.times(1)).createHalfCat();
        Mockito.verify(dog,Mockito.times(1)).createHalfDog();
    }

    @Test
    public void test2() {
        Mockito.when(cat.createHalfCat()).thenReturn(new Cat(2, "Я самый умный"));
        Mockito.when(dog.createHalfDog()).thenReturn(new Dog(2, "Я самый весёлый"));

        Cat halfCat = cat.createHalfCat();
        Dog halfDog = dog.createHalfDog();
        CatDog catDog = new CatDog(halfCat, halfDog);

        assertEquals(4,  catDog.getLegsCount()) ;
        assertEquals("Единственный в мире малыш Котопёс",  catDog.getSound()) ;

    }

}
