package designPattern.factoryPattern;

import designPattern.factoryPattern.ingredient.PizzaIngredientFactory;

public class ClamPizza extends Pizza {

    PizzaIngredientFactory pizzaIngredientFactory;

    public ClamPizza( PizzaIngredientFactory pizzaIngredientFactory ) {

        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    void prepare() {

        System.out.println( "준비 중 : " + name );

        // 재료가 필요할 때 마다 팩토리에 있는 메서드를 호출해서 만든다.
        dough = pizzaIngredientFactory.createDough();
        cheese = pizzaIngredientFactory.createCheese();
        clam = pizzaIngredientFactory.createClam();
    }
}
