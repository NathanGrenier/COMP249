public class University implements Comparison{
    int academicReputation;
    double industryIncome;

    public University() {
        this.academicReputation = 0;
        this.industryIncome = 0;
    }

    public University(int academicReputation, double industryIncome) {
        this.academicReputation = academicReputation;
        this.industryIncome = industryIncome;
    }

    public boolean topRank(Object obj) {
        University otherObj = (University) obj;
        if (otherObj.academicReputation > this.academicReputation) {
            System.out.println(otherObj + " is > " + this);
        }
        return otherObj.academicReputation > this.academicReputation;
    }

    public boolean lowRank(Object obj) {
        University otherObj = (University) obj;
        if (otherObj.industryIncome < this.industryIncome) {
            System.out.println(otherObj + " is < " + this);
        }
        return otherObj.industryIncome < this.industryIncome;
    }

    public String toString() {
        return "University{academicReputation=" + this.academicReputation + " industryIncome=" + this.industryIncome + "}";
    }
}
