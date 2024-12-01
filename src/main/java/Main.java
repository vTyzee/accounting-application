import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Считать все месячные отчёты");
            System.out.println("2. Считать годовой отчёт");
            System.out.println("3. Сверить отчёты");
            System.out.println("4. Вывести информацию о месячных отчётах");
            System.out.println("5. Вывести информацию о годовом отчёте");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    monthlyReport.readMonthlyReports();
                    System.out.println("Месячные отчёты успешно загружены.");
                    break;

                case 2:
                    yearlyReport.readYearlyReport();
                    System.out.println("Годовой отчёт успешно загружен.");
                    break;

                case 3:
                    if (monthlyReport.isLoaded() && yearlyReport.isLoaded()) {
                        monthlyReport.compareWithYearlyReport(yearlyReport);
                        System.out.println("Сверка отчётов выполнена.");
                    } else {
                        System.out.println("Сначала загрузите все отчёты.");
                    }
                    break;

                case 4:
                    if (monthlyReport.isLoaded()) {
                        monthlyReport.printMonthlyReportInfo();
                    } else {
                        System.out.println("Сначала загрузите месячные отчёты.");
                    }
                    break;

                case 5:
                    if (yearlyReport.isLoaded()) {
                        yearlyReport.printYearlyReportInfo();
                    } else {
                        System.out.println("Сначала загрузите годовой отчёт.");
                    }
                    break;

                case 0:
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
