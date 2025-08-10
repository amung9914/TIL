package designPattern.factoryPattern;

/**
 * 생산자클래스
 */
public abstract class PizzaStore {

    public Pizza orderPizza( String type ) {

        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();

        return pizza;
    }

    /**
     * 팩토리 메서드는 객체 생성을 서브 클래스에 캡슐화 할 수 있다.
     * 그러면 슈퍼클래스에 있는 클라이언트 코드와 서브클래스에 있는 객체 생성 코드를 분리할 수 있다.
     * 즉, 추상메서드로 선언해서 서브 클래스가 객체 생성을 책임지도록 하는 것이다.
     * @param type
     * @return
     */
    protected abstract Pizza createPizza( String type );
}
