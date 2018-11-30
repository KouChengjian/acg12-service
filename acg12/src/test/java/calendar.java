import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/5 10:26
 * Description:
 */
public class calendar {

    public static void main(String[] args){

        System.out.print("请选择年份(大于1990年)：");
        Scanner input = new Scanner(System.in);
        int year = input.nextInt();
        System.out.print("请选择该年的月份：");
        int month = input.nextInt();
        // 先判断year是否是闰年
        boolean Renyear;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + "是闰年");
            Renyear = true;
        } else {
            System.out.println(year + "是平年");
            Renyear = false;
        }
        //记录当月的天数
        int day = 0;
        switch (month){
            case 1:
                day = 31;
                break;
            case 3:
                day = 31;
                break;
            case 4:
                day = 30;
                break;
            case 5:
                day = 31;
                break;
            case 6:
                day = 30;
                break;
            case 7:
                day = 31;
                break;
            case 8:
                day = 31;
                break;
            case 9:
                day = 30;
                break;
            case 10:
                day = 31;
                break;
            case 11:
                day = 30;
                break;
            case 12:
                day = 31;
                break;
            default:
                if (Renyear) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
        }
        System.out.println(year + "年" + month + "月" + "有" + day + "天" + "\t");
        System.out.println(month + "月份的日历如下：");
        // 从1990年到指定日期的天数，1990年1月1日为星期一。
        int sum = 0;
        for(int i = 1990;i < year; i++) {
            // 判断是否是闰年
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                sum = sum + 366;
            } else {
                sum = sum + 365;
            }
        }
        // 统计月份的天数
        for (int i = 1; i < month; i++) {

            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) { // 大月
                sum = sum + 31;

            } else if (i == 4 || i == 6 || i == 9 || i == 11){  // 小月
                sum = sum + 30;
            } else {
                // 二月
                if (Renyear) {
                    sum = sum + 29;
                } else {
                    sum = sum + 28;
                }
            }
        }

        int blank = sum % 7 + 1;//blank开始输出的空格数 +1是从星期天开始；
        System.out.println("星期天\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");

        int temp = 1;
        for (int i = 0; i < blank; i++) {
            System.out.print("\t");
            temp++;
        }
        if (temp > 7){
            System.out.println("");
            temp = 1;
        }
        for (int i = 1; i <= day; i++) {
            if (temp % 7 == 0){
                System.out.print(i + "\n");//控制换行
                temp = 1;
            }
            else{
                System.out.print(i + "\t");
                temp++;
            }
        }

    }
}
