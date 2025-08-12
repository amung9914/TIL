package designPattern.factoryPattern.ingredient;

import designPattern.factoryPattern.Clam;

/**
 * 재료가 다른 피자를 만들어야 함. 재료를 생산하는 팩토리를 정의한다
 *  -> 추상 팩토리 형식.
 *     추상 팩토리로 제품군을 생성하는 인터페이스를 제공한다. 이를 통해 코드와 제품을 생산하는 팩토리를 분리할 수 있다.
 *     이렇게 해서 추상 팩토리를 바탕으로 같은 제품을 다른 방식으로 구현하는 구상 팩토리를 만들 수 있다.
 *
 *     추상 팩토리 (Abstract Factory Pattern) 정의 : 구상 클래스에 의존하지 않고도 서로 연관되거나 의존적인 객체로 이루어진
 *     제품군을 생산하는 인터페이스를 제공합니다.
 *     구상 클래스는 서브클래스에서 만듭니다.
 */
public interface PizzaIngredientFactory {
    public Dough createDough();
    public Veggies[] createVeggies();

    public Cheese createCheese();

    public Clam createClam();
}
