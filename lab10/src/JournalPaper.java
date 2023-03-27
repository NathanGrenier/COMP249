public class JournalPaper implements Comparison{
    double citation;
    int impactFactor;

    public JournalPaper() {
        this.citation = 0;
        this.impactFactor = 0;
    }

    public JournalPaper(double citation, int impactFactor) {
        this.citation = citation;
        this.impactFactor = impactFactor;
    }

    public boolean topRank(Object obj) {
        JournalPaper otherObj = (JournalPaper) obj;
        if (otherObj.impactFactor > this.impactFactor) {
            System.out.println(otherObj + " is > " + this);
        }
        return otherObj.impactFactor > this.impactFactor;
    }

    public boolean lowRank(Object obj) {
        JournalPaper otherObj = (JournalPaper) obj;
        if (otherObj.citation < this.citation) {
            System.out.println(otherObj + " is < " + this);
        }
        return otherObj.citation < this.citation;
    }

    public String toString() {
        return "JournalPaper{citaton=" + this.citation + " impactFactor=" + this.impactFactor + "}";
    }
}
