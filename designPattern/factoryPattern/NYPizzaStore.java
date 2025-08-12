package designPattern.factoryPattern;

import designPattern.factoryPattern.ingredient.NYPizzaIngredientFactory;
import designPattern.factoryPattern.ingredient.PizzaIngredientFactory;

/**
 * 추상 팩토리의 클라이언트는 PizzaStore의 인스턴스인 NYPizzaStore 입니다.
 * 클라이언트를 만들때는 추상 팩토리를 바탕으로 만듭니다.
 * 실제 팩토리는 실행 시에 결정됩니다.
 */
public class NYPizzaStore extends PizzaStore{

    @Override
    protected Pizza createPizza( String item ) {
        System.out.println( "NYPizza를 만들어요" );

        Pizza pizza = null;
        // 각 팩토리에서 서로 다른 제품군을 구현합니다.
        PizzaIngredientFactory pizzaIngredientFactory = new NYPizzaIngredientFactory();

        if ( item.equals("cheese")) {
            pizza = new CheesePizza( pizzaIngredientFactory );
            pizza.setName("뉴옥 스타일 치즈 피자");
        } else if ( item.equals("clam")) {
            pizza = new ClamPizza( pizzaIngredientFactory );
            pizza.setName("뉴욕 스타일 조개 피자");
        }

        return pizza;
    }
}
