package com.github.qq4492004.Customloredamage.Entity;

public class TempAttribute {
    double Damage;
    double Defense;
    double CritRate;
    double CritDouble;

    @Override
    public String toString() {
        return "TempAttribute{" +
                "Damage=" + Damage +
                ", Defense=" + Defense +
                ", CritRate=" + CritRate +
                ", CritDouble=" + CritDouble +
                '}';
    }

    public double getDamage() {
        return Damage;
    }

    public void setDamage(double damage) {
        Damage = damage;
    }

    public double getDefense() {
        return Defense;
    }

    public void setDefense(double defense) {
        Defense = defense;
    }

    public double getCritRate() {
        return CritRate;
    }

    public void setCritRate(double critRate) {
        CritRate = critRate;
    }

    public double getCritDouble() {
        return CritDouble;
    }

    public void setCritDouble(double critDouble) {
        CritDouble = critDouble;
    }

    public TempAttribute(double damage, double defense, double critRate, double critDouble) {
        Damage = damage;
        Defense = defense;
        CritRate = critRate;
        CritDouble = critDouble;
    }
}
