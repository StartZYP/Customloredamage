package com.github.qq4492004.Customloredamage.Entity;

public class PlayerAttribute {
    double Damage;
    double Defense;
    double CritRate;
    double CritDouble;
    TempAttribute tempAttribute;

    public  PlayerAttribute GetAllAttr(){
        if (this.tempAttribute == null) {
            return this;
        } else {
            PlayerAttribute playerAttribute =new PlayerAttribute(this.Damage,this.Defense,this.CritRate,this.CritDouble);
            playerAttribute.Damage = this.tempAttribute.getDamage() + this.Damage;
            playerAttribute.Defense = this.tempAttribute.getDefense() + this.Defense;
            playerAttribute.CritRate = this.tempAttribute.getCritRate() + this.CritRate;
            playerAttribute.CritDouble = this.tempAttribute.getCritDouble() + this.CritDouble;
            return playerAttribute;
        }
    }


    @Override
    public String toString() {
        return "PlayerAttribute{" +
                "Damage=" + Damage +
                ", Defense=" + Defense +
                ", CritRate=" + CritRate +
                ", CritDouble=" + CritDouble +
                ", tempAttribute=" + tempAttribute +
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

    public TempAttribute getTempAttribute() {
        return tempAttribute;
    }

    public void setTempAttribute(TempAttribute tempAttribute) {
        this.tempAttribute = tempAttribute;
    }

    public PlayerAttribute(double damage, double defense, double critRate, double critDouble) {
        Damage = damage;
        Defense = defense;
        CritRate = critRate;
        CritDouble = critDouble;
    }

    public PlayerAttribute(double damage, double defense, double critRate, double critDouble, TempAttribute tempAttribute) {
        Damage = damage;
        Defense = defense;
        CritRate = critRate;
        CritDouble = critDouble;
        this.tempAttribute = tempAttribute;
    }
}
