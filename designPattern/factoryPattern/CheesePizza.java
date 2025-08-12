package designPattern.factoryPattern;

import designPattern.factoryPattern.ingredient.PizzaIngredientFactory;

public class CheesePizza extends Pizza {

    PizzaIngredientFactory pizzaIngredientFactory;

    public CheesePizza( PizzaIngredientFactory pizzaIngredientFactory ) {
        // 각 피자 클래스는 생성자로부터 팩토리를 전달받고 그 팩토리를 인스턴스 변수에 저장한다
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    void prepare() {

        System.out.println( "준비 중 : " + name );

        // 재료가 필요할 때 마다 팩토리에 있는 메서드를 호출해서 만든다.
        dough = pizzaIngredientFactory.createDough();
        cheese = pizzaIngredientFactory.createCheese();
    }
}
