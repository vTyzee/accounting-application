public class YearlyItem {
    String month;
    int amount;
    boolean isExpense;

    public YearlyItem(String month, boolean isExpense, int amount) {
        this.month = month;
        this.isExpense = isExpense;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "YearlyItem{" +
                "month='" + month + '\'' +
                ", amount=" + amount +
                ", isExpense=" + isExpense +
                '}';
    }
}
