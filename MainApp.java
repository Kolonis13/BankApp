public class MainApp {
    public static void main(String[] args) {
        double[] table = new double[50];


        // 1. Γέμισμα πίνακα με τυχαίους αριθμούς
        MyTable.fillTableRandomly(table); 

        // 2. Εκτύπωση πίνακα οριζόντια
        System.out.println("Horizontal table: "); 
        MyTable.printTableH(table);

        // 3. Εκτύπωση πίνακα κάθετα
        System.out.println("Vertical table: ");
        MyTable.printTableV(table);

        // 4. Swap δύο τιμών
        System.out.println("Swap values: ");
        MyTable.swapValues(table, 0, 1);
        MyTable.printTableH(table);

        // 5. Εύρεση θέσης μικρότερου στοιχείου από θέση 10
        int minIndex = MyTable.minLocationFrom(table, 10);
        System.out.println("Minimum from location 10: index = " + minIndex + ", value = " + table[minIndex]);

        // 6. Εύρεση θέσης μεγαλύτερου στοιχείου από θέση 10
        int maxIndex = MyTable.maxLocationFrom(table, 10);
        System.out.println("Maximum from location 10: index = " + maxIndex + ", value = " + table[maxIndex]);

        // 7. Κλωνοποίηση πίνακα
        double[] cloneTable = MyTable.cloneTable(table);
        System.out.println("Clone table: ");
        MyTable.printTableH(cloneTable);

        // 8. Merged table
        double[] secondTable = new double[10];
        MyTable.fillTableRandomly(secondTable);
        double[] mergedTable = MyTable.mergeTables(table, secondTable);
        System.out.println("Merged table: ");
        MyTable.printTableH(mergedTable);


    }
    
}
