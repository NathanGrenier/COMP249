public class TestComparison {
    public static void main(String[] args) {
        JournalPaper p1 = new JournalPaper();
        JournalPaper p2 = new JournalPaper(10, 100);

        University u1 = new University();
        University u2 = new University(100, 10);

        // Journal Paper
        System.out.println(p2.lowRank(p1)); 
        System.out.println(p1.lowRank(p2)); 

        System.out.println(p2.topRank(p1)); 
        System.out.println(p1.topRank(p2)); 

        // University
        System.out.println(u2.lowRank(u1)); 
        System.out.println(u1.lowRank(u2)); 

        System.out.println(u2.topRank(u1)); 
        System.out.println(u1.topRank(u2)); 
    }
}
