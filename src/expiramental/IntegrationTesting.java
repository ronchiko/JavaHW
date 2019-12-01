package expiramental;

import expiramental.inegral.IntegralUnit;

public class IntegrationTesting {
    public static void main(String[] args) {
        IntegralUnit unit = new IntegralUnit("x^2 + (x + 9)^3");
        unit.integrate(0, 2);
    }
}
