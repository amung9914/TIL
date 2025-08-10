package designPattern.factoryPattern;

/**
 * 팩토리 메서드 패턴 : 사용하는 서브클래스에 따라 생산되는 객체 인스턴스가 결정된다
 *
 * 팩토리 메서드 패턴을 사용하면 여러번 재 사용이 가능한 프레임워크를 만들 수 있다.
 * 생성하는 제품을 마음대로 변경할 수 있기 때문이다.
 * 우리는 팩토리 메서드 패턴을 통해 제품을 생산하는 부분과 사용하는 부분을 분리할 수 있다.
 */
public class PizzaTestDrive {

    public static void main(String[] args) {
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println( "에단이 주문한 " + pizza.getName() + "\n" );

        pizza = chicagoPizzaStore.orderPizza("cheese");
        System.out.println( "조엘이 주문한 " + pizza.getName() );

        /** 출력 결과 :
         * NYPizza를 만들어요
         * Pizza를 만들 준비
         * Pizza를 굽는 중...
         * 에단이 주문한 뉴욕 스타일 소스와 치즈 피자
         *
         * ChicagoPizza를 만들어요
         * Pizza를 만들 준비
         * Pizza를 굽는 중...
         * 조엘이 주문한 시카고 스타일 치즈 피자
         */
    }
}
