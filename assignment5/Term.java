package assignment5;

public class Term{
    final int power;
    final int coefficient;
    /**
     * validates and initializes the Term instance
     * @param power
     * @param coefficient
     * @throws InstantiationError
     */
    public Term(int power,int coefficient) throws InstantiationError{
        if(power<0)
            throw new InstantiationError("pls use postivie power for terms");
        if(coefficient==0)
            throw new InstantiationError("coefficient zero makes no sense of term");
        this.power=power;
        this.coefficient=coefficient;
    }
}
