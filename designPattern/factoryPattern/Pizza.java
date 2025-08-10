package designPattern.factoryPattern;

/**
 * 제품 클래스
 */
public abstract class Pizza {
    String name;

    public void prepare() {
        System.out.println("Pizza를 만들 준비");
    }

    public void bake() {
        System.out.println("Pizza를 굽는 중...");
    }

    public String getName() {
        return name;
    }
}
