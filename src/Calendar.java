public class Calendar {
    static int[][] calendar = new int[6][7];
    
    static int getDayOfWeek(int month, int year) {
        if (month < 3) {
            month += 12;
            year -= 1;
        }
        int k = year % 100;
        int j = year / 100;
        int h = (1 + (13 * (month + 1)) / 5 + k + (k / 4) + (j / 4) - (2 * j)) % 7;
        return ((h + 5) % 7) + 1;
    }
    
    static int getDaysInMonth(int month, int year) {
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return days[month];
    }
    
    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    static String getMonthName(int month) {
        String[] months = {"", "January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
        return months[month];
    }
    
    static void fillCalendarArray(int month, int year) {
        int dayOfWeek = getDayOfWeek(month, year);
        int daysInMonth = getDaysInMonth(month, year);
        
        int day = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < dayOfWeek - 1) {
                    calendar[i][j] = 0;
                } else if (day <= daysInMonth) {
                    calendar[i][j] = day++;
                } else {
                    calendar[i][j] = 0;
                }
            }
        }
    }
    
    static void printCalendar() {
        System.out.println(" S  M  T  W  Th  F  S");
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (calendar[i][j] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.printf("%2d ", calendar[i][j]);
                }
            }
            System.out.println();
            
            boolean hasMoreDays = false;
            if (i < 5) {
                for (int j = 0; j < 7; j++) {
                    if (calendar[i + 1][j] != 0) {
                        hasMoreDays = true;
                        break;
                    }
                }
                if (!hasMoreDays) break;
            }
        }
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Calendar month year");
            return;
        }
        
        int month = Integer.parseInt(args[0]);
        int year = Integer.parseInt(args[1]);
        
        if (month < 1 || month > 12) {
            System.out.println("Month should be between 1 and 12");
            return;
        }
        
        System.out.println(getMonthName(month) + " " + year);
        fillCalendarArray(month, year);
        printCalendar();
    }
}