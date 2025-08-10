package designPattern.factoryPattern;

public class ChicagoPizzaStore extends PizzaStore{

    @Override
    protected Pizza createPizza( String type ) {
        System.out.println( "ChicagoPizza를 만들어요" );
        return new ChicagoStylePizza();
    }
}
