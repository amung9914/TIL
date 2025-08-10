package designPattern.factoryPattern;

public class NYPizzaStore extends PizzaStore{

    @Override
    protected Pizza createPizza( String type ) {
        System.out.println( "NYPizza를 만들어요" );
        return new NYStylePizza();
    }
}
