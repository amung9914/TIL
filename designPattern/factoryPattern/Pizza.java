package designPattern.factoryPattern;

import designPattern.factoryPattern.ingredient.Cheese;
import designPattern.factoryPattern.ingredient.Dough;
import designPattern.factoryPattern.ingredient.Veggies;

/**
 * 제품 클래스
 */
public abstract class Pizza {
    String name;

    // 만들어지는 재료는 어떤 팩토리를 쓰는지에 따라 달라진다.
    // 피자클래스는 어떤 재료가 배달되는지 전혀 신경쓰지 않는다.
    // 따라서 많은 형태를 만들 수 있고, 그대로 재사용 할 수 있다.
    Dough dough;
    Veggies veggies[];
    Cheese cheese;
    Clam clam;

    abstract void prepare();

    public void bake() {
        System.out.println("Pizza를 굽는 중...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
