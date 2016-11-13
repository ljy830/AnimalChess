import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by lujingyi on 08/11/2016.
 */
public class AnimalChess {

    //定义方法map
    public static void map(int animal[][], int tile[][]) {
        int row = 0;
        while (row < 7) {
            int column = 0;
            while (column < 9) {
                if (animal[row][column] == 0 && tile[row][column] == 0)
                    System.out.print(" 　 ");
                else if (animal[row][column] == 0 && tile[row][column] == 1)
                    System.out.print(" 水 ");
                else if (animal[row][column] == 0 && (tile[row][column] == 2 || tile[row][column] == 4))
                    System.out.print(" 陷 ");
                else if (animal[row][column] == 0 && (tile[row][column] == 3 || tile[row][column] == 5))
                    System.out.print(" 家 ");
                else if (animal[row][column] == 1)
                    System.out.print("1鼠 ");
                else if (animal[row][column] == 2)
                    System.out.print("2猫 ");
                else if (animal[row][column] == 3)
                    System.out.print("3狼 ");
                else if (animal[row][column] == 4)
                    System.out.print("4狗 ");
                else if (animal[row][column] == 5)
                    System.out.print("5豹 ");
                else if (animal[row][column] == 6)
                    System.out.print("6虎 ");
                else if (animal[row][column] == 7)
                    System.out.print("7狮 ");
                else if (animal[row][column] == 8)
                    System.out.print("8象 ");
                else if (animal[row][column] == 11)
                    System.out.print(" 鼠1");
                else if (animal[row][column] == 12)
                    System.out.print(" 猫2");
                else if (animal[row][column] == 13)
                    System.out.print(" 狼3");
                else if (animal[row][column] == 14)
                    System.out.print(" 狗4");
                else if (animal[row][column] == 15)
                    System.out.print(" 豹5");
                else if (animal[row][column] == 16)
                    System.out.print(" 虎6");
                else if (animal[row][column] == 17)
                    System.out.print(" 狮7");
                else if (animal[row][column] == 18)
                    System.out.print(" 象8");
                column = column + 1;
            }
            System.out.println();
            row = row + 1;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Scanner animalSc = new Scanner(new File("animal.txt"));
        Scanner tileSc = new Scanner(new File("tile.txt"));

        int [][]tile = new int [7][9];
        int [][]animal = new int [7][9];

        for(int row = 0; row < 7; row++) {
            String tileString = tileSc.nextLine();
            for (int column = 0; column < 9; column++) {
                tile[row][column] = Integer.parseInt(tileString.substring(column, column + 1));
            }
        }

        for (int row = 0; row < 7; row++){
            String animalString = animalSc.nextLine();
            for (int column = 0; column < 9; column++){
                animal[row][column] = Integer.parseInt(animalString.substring(column * 2, column * 2 + 2));
            }
        }
        int currentStep = 0;
        int lastStep = 0;
        int[][][] animalHistory = new int[100000][7][9];

        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 9; column++) {
                animalHistory[currentStep][row][column] = animal[row][column];
            }
        }


        while (true) {
            stop2:
            {
                currentStep = 0;
                lastStep = 0;

                for (int row = 0; row < 7; row++) {
                    for (int column = 0; column < 9; column++) {
                        animal[row][column] = animalHistory[0][row][column];
                    }
                }
                map(animal, tile);
                System.out.println("左方玩家行动：");
                String move = scanner.next();

                while (true) {
                    int row = 0;
                    int column = 0;
                    while (true) {
                        stop:
                        {
                            row = 0;
                            //输出帮助
                            if (move.equals("help")) {
                                System.out.println("指令介绍：");
                                System.out.println("1、移动指令");
                                System.out.println("移动指令由两个部分组成。");
                                System.out.println("第一个部分是数字1-8，根据战斗力分别对应鼠（1），猫（2），狼（3），狗（4），豹（5），虎（6），狮（7），象（8）");
                                System.out.println("第二个部分是字母wasd中的一个，w对应上方向，a对应左方向，s对应下方向，d对应右方向");
                                System.out.println("比如指令'1d'表示鼠向右走，'4w'表示狗向左走");
                                System.out.println("2、游戏指令");
                                System.out.println("输入restart重新开始游戏");
                                System.out.println("输入help查看帮助");
                                System.out.println("输入undo悔棋");
                                System.out.println("输入redo取消悔棋");
                                System.out.println("输入exit退出游戏");
                                System.out.println("左方玩家继续行动：");
                                break stop;
                            }

                            //左方输入1d
                            if (move.equals("1d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 1) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (tile[row][column] != 1) {
                                        if (animal[row][column + 1] == 18 || animal[row][column + 1] == 11 || animal[row][column + 1] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column + 1] = 1;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column + 1] >= 12 && animal[row][column + 1] <= 17) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    }else{
                                        if (animal[row][column + 1] == 0 || animal[row][column + 1] == 11){
                                            animal[row][column + 1] = 1;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop;
                                        }
                                    }
                                }else if(tile[row][column + 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 1;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    if (animal[row][column + 1] == 0 || animal[row][column + 1] == 11) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 1;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    } else {
                                        System.out.println("水里有动物，不能下水，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入2d
                            else if (move.equals("2d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 2) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || animal[row][column + 1] == 11 || animal[row][column + 1] == 12) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 2;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 13 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if (tile[row][column + 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if(tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 2;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入3d
                            else if (move.equals("3d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 3) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 13)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 3;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 14 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if(tile[row][column + 1] == 3) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 3;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("狼不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入4d
                            else if (move.equals("4d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 4) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 14)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 4;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 15 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if(tile[row][column + 1] == 3) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if(tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 4;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入5d
                            else if (move.equals("5d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 5) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 15)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 5;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 16 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if(tile[row][column + 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if  (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 5;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入6d
                            else if (move.equals("6d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 6) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 16)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 6;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 17 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if(tile[row][column + 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 6;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row][column + 1] != 11 && animal[row][column + 2] != 11 && animal[row][column + 3] != 11) {
                                        if (animal[row][column + 4] >= 11 && animal[row][column + 4] <= 16 || animal[row][column + 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column + 4] = 6;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column + 4] == 17 || animal[row][column + 4] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row][column + 4] >= 1 && animal[row][column + 4] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入7d
                            else if (move.equals("7d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 7) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 17)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 7;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] == 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if (tile[row][column + 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 7;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    if (animal[row][column + 1] != 11 && animal[row][column + 2] != 11 && animal[row][column + 3] != 11) {
                                        if (animal[row][column + 4] >= 11 && animal[row][column + 4] <= 17 || animal[row][column + 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column + 4] = 7;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column + 4] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row][column + 4] >= 1 && animal[row][column + 4] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入8d
                            else if (move.equals("8d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 8) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 3 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] > 11 && animal[row][column + 1] <= 18)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 8;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] == 11) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if(tile[row][column + 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if(tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 8;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入1a
                            else if (move.equals("1a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 1) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if (tile[row][column] != 1) {
                                        if (animal[row][column - 1] == 18 || animal[row][column - 1] == 11 || animal[row][column - 1] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column - 1] = 1;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column - 1] >= 12 && animal[row][column - 1] <= 17) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    }else{
                                        if (animal[row][column - 1] == 0 || animal[row][column - 1] == 11){
                                            animal[row][column - 1] = 1;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop;
                                        }
                                    }
                                }else if (tile[row][column - 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column - 1] == 2 && animal[row][column - 1] == 4 ){
                                    animal[row][column - 1] = 1;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    animal[row][column - 1] = 1;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                            }

                            //左方输入2a
                            else if (move.equals("2a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 2) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if ((animal[row][column - 1] >= 11 && animal[row][column - 1] <= 12) || animal[row][column - 1] == 0) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 2;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 13 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if (tile[row][column - 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column - 1] == 2 || animal[row][column - 1] == 4){
                                    animal[row][column - 1] = 2;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入3a
                            else if (move.equals("3a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 3) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if ((animal[row][column - 1] >= 11 && animal[row][column - 1] <= 13) || animal[row][column - 1] == 0) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 3;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 14 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if (tile[row][column - 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column - 1] == 2 || animal[row][column - 1] == 4) {
                                    animal[row][column - 1] = 3;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狼不能下水，请重新输入：");
                                    break stop;
                                }
                            }


                            //左方输入4a
                            else if (move.equals("4a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 4) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if ((animal[row][column - 1] >= 11 && animal[row][column - 1] <= 14) || animal[row][column - 1] == 0) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 4;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 15 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row][column - 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column - 1] == 2 || animal[row][column - 1] == 4) {
                                    animal[row][column - 1] = 4;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入5a
                            else if (move.equals("5a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 5) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if ((animal[row][column - 1] >= 11 && animal[row][column - 1] <= 15) || animal[row][column - 1] == 0) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 5;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 16 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if (tile[row][column - 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if(tile[row][column - 1] == 2 || animal[row][column - 1] == 4){
                                    animal[row][column - 1] = 5;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入6a
                            else if (move.equals("6a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 6) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if ((animal[row][column - 1] >= 11 && animal[row][column - 1] <= 16) || animal[row][column - 1] == 0) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 6;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 17 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if(tile[row][column - 1] == 3) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column - 1] == 2 || animal[row][column - 1] == 4){
                                    animal[row][column -1] = 6;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row][column - 1] != 11 && animal[row][column - 2] != 11 && animal[row][column - 3] != 11) {
                                        if (animal[row][column - 4] >= 11 && animal[row][column - 4] <= 16 || animal[row][column - 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column - 4] = 6;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column - 4] == 17 || animal[row][column - 4] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row][column - 4] >= 1 && animal[row][column - 4] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入7a
                            else if (move.equals("7a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 7) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if ((animal[row][column - 1] >= 11 && animal[row][column - 1] <= 17) || animal[row][column - 1] == 0) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 7;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] == 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if (tile[row][column - 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row][column - 1] == 2 || animal[row][column - 1] == 4) {
                                    animal[row][column - 1] = 7;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    if (animal[row][column - 1] != 11 && animal[row][column - 2] != 11 && animal[row][column - 3] != 11) {
                                        if (animal[row][column - 4] >= 11 && animal[row][column - 4] <= 17 || animal[row][column - 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column - 4] = 7;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column - 4] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row][column - 4] >= 1 && animal[row][column - 4] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入8a
                            else if (move.equals("8a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 8) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 3 && tile[row][column - 1] != 2 && animal[row][column - 1] != 4) {
                                    if ((animal[row][column - 1] >= 12 && animal[row][column - 1] <= 18) || animal[row][column - 1] == 0) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 8;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] == 11) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                    if (tile[row][column] == 3) {
                                        System.out.println("不能走入自己的家，请重新输入：");
                                        break stop;
                                    }
                                } else if(tile[row][column - 1] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if(tile[row][column - 1] == 2 || animal[row][column - 1] == 4) {
                                    animal[row][column - 1] = 8;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入1w
                            else if (move.equals("1w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 1) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if (tile[row][column] == 0) {
                                        if (animal[row - 1][column] == 18 || animal[row - 1][column] == 11 || animal[row - 1][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row - 1][column] = 1;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row - 1][column] >= 12 && animal[row - 1][column] <= 17) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    }else{
                                        if (animal[row - 1][column] == 0 || animal[row - 1][column] == 11){
                                            animal[row - 1][column] = 1;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop;
                                        }
                                    }
                                }else if(tile[row - 1][column] == 3) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4){
                                    animal[row - 1][column] = 1;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    animal[row - 1][column] = 1;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                            }

                            //左方输入2w
                            else if (move.equals("2w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 2) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 11 && animal[row - 1][column] <= 12) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 2;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 13 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                } else if (tile[row - 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4){
                                    animal[row - 1][column] = 2;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入3w
                            else if (move.equals("3w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 3) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 11 && animal[row - 1][column] <= 13) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 3;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 14 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row - 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if(tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 3;
                                    animal[row - 1][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("狼不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入4w
                            else if (move.equals("4w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 4) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 11 && animal[row - 1][column] <= 14) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 4;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 15 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row - 1][column] == 3) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 4;
                                    animal[row - 1][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入5w
                            else if (move.equals("5w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 5) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 11 && animal[row - 1][column] <= 15) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 5;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 16 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                    if (tile[row - 1][column] == 3) {
                                        System.out.println("不能走入自己的家，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row - 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 5;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入6w
                            else if (move.equals("6w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 6) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 11 && animal[row - 1][column] <= 16) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 6;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 17 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row - 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 6;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row - 1][column] != 11 && animal[row - 2][column] != 11) {
                                        if (animal[row - 3][column] >= 11 && animal[row - 3][column] <= 16 || animal[row - 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row - 3][column] = 6;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row - 3][column] == 17 || animal[row - 3][column] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row - 3][column] >= 1 && animal[row - 3][column] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入7w
                            else if (move.equals("7w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 7) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 11 && animal[row - 1][column] <= 17) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 7;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] == 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row - 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 7;
                                    animal[row - 1][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    if (animal[row - 1][column] != 11 && animal[row - 2][column] != 11) {
                                        if (animal[row - 3][column] >= 11 && animal[row - 3][column] <= 17 || animal[row - 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row - 3][column] = 7;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row - 3][column] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row - 3][column] >= 1 && animal[row - 3][column] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入8w
                            else if (move.equals("8w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 8) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 3 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 12 && animal[row - 1][column] <= 18) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 8;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] == 11) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row - 1][column] >= 1 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row - 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 8;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入1s
                            else if (move.equals("1s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 1) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if (tile[row][column] != 1) {
                                        if (animal[row + 1][column] == 18 || animal[row + 1][column] == 11 || animal[row + 1][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row + 1][column] = 1;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row + 1][column] >= 12 && animal[row + 1][column] <= 17) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    }else{
                                        if(animal[row + 1][column] == 0 || animal[row + 1][column] == 11){
                                            animal[row + 1][column] = 1;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop;
                                        }
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if(tile[row + 1][column] == 2 || tile[row + 1][column] == 4) {
                                    animal[row + 1][column] = 1;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    animal[row + 1][column] = 1;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                            }

                            //左方输入2s
                            else if (move.equals("2s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 2) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    move = scanner.next();
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 11 && animal[row][column] <= 12) || animal[row + 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 2;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 13 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4) {
                                    animal[row + 1][column] = 2;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入3s
                            else if (move.equals("3s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 3) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 11 && animal[row][column] <= 13 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 3;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 14 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4) {
                                    animal[row + 1][column] = 3;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狼不能下水，请重新输入：");
                                    break stop;
                                }

                            }

                            //左方输入4s
                            else if (move.equals("4s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 4) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 11 && animal[row][column] <= 14 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 4;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 15 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 4;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入5s
                            else if (move.equals("5s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 5) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3) {
                                    if ((animal[row + 1][column] >= 11 && animal[row][column] <= 15 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 5;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 16 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                } else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //左方输入6s
                            else if (move.equals("6s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 6) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 11 && animal[row][column] <= 16 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 6;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 17 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 6;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row + 1][column] != 11 && animal[row + 2][column] != 11) {
                                        if (animal[row + 3][column] >= 11 && animal[row + 3][column] <= 16 || animal[row + 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row + 3][column] = 6;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row + 3][column] == 17 || animal[row + 3][column] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row + 3][column] >= 1 && animal[row + 3][column] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入7s
                            else if (move.equals("7s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 7) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 11 && animal[row][column] <= 17 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 7;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] == 18) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 7;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row + 1][column] != 11 && animal[row + 2][column] != 11) {
                                        if (animal[row + 3][column] >= 11 && animal[row + 3][column] <= 17 || animal[row + 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row + 3][column] = 7;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row + 3][column] == 18) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop;
                                        }
                                        if (animal[row + 3][column] >= 1 && animal[row + 3][column] <= 8) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop;
                                    }
                                }
                            }

                            //左方输入8s
                            else if (move.equals("8s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 8) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop;
                                }
                                if (row == 6) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 3 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if (animal[row + 1][column] >= 12 && animal[row][column] <= 18 || animal[row + 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 8;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] == 11) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop;
                                    }
                                    if (animal[row + 1][column] >= 1 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop;
                                    }
                                }else if (tile[row + 1][column] == 3){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4) {
                                    animal[row + 1][column] = 8;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop;
                                }
                            }

                            //输入exit
                            else if (move.equals("exit")) {
                                return;
                            }

                            //输入restart
                            else if (move.equals("restart")) {
                                break stop2;
                            }

                            //输入undo悔棋
                            else if (move.equals("undo")) {
                                if (currentStep > 0) {
                                    lastStep++;
                                    currentStep--;
                                    for (row = 0; row < 7; row++) {
                                        for (column = 0; column < 9; column++) {
                                            animal[row][column] = animalHistory[currentStep][row][column];
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("当前步数为0，不能悔棋，请重新输入：");
                                    break stop;
                                }
                            }

                            //输入redo取消悔棋
                            else if (move.equals("redo")) {
                                if (lastStep > 0) {
                                    lastStep--;
                                    currentStep++;
                                    for (row = 0; row < 7; row++) {
                                        for (column = 0; column < 9; column++) {
                                            animal[row][column] = animalHistory[currentStep][row][column];
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("当前悔棋次数为0，不能取消悔棋，请重新输入：");
                                    break stop;
                                }
                            }

                            //输入不合法
                            else {
                                System.out.println("输入有误，请重新输入：");
                            }
                        }
                        move = scanner.next();
                    }


                    //判断左方是否走入右方兽穴
                    if (animal[3][8] != 0) {
                        System.out.println("左方获胜");
                        return;
                    }

                    //判断一方是否无子
                    int i = 0;
                    row = 0;
                    column = 0;
                    while (row < 7) {
                        column = 0;
                        while (column < 9) {
                            if (animal[row][column] >= 11 && animal[row][column] <= 18) {
                                i++;
                            }
                            column++;
                        }
                        row++;
                    }
                    if (i == 0) {
                        System.out.println("左方获胜");
                        return;
                    }


                    //记录棋盘
                    for (row = 0; row < 7; row++) {
                        for (column = 0; column < 9; column++) {
                            animalHistory[currentStep][row][column] = animal[row][column];
                        }
                    }

                    map(animal, tile);
                    System.out.println("右方玩家行动:");
                    move = scanner.next();

                    while (true) {
                        stop3:
                        {
                            row = 0;

                            //输出帮助
                            if (move.equals("help")) {
                                System.out.println("指令介绍：");
                                System.out.println("1、移动指令");
                                System.out.println("移动指令由两个部分组成。");
                                System.out.println("第一个部分是数字1-8，根据战斗力分别对应鼠（1），猫（2），狼（3），狗（4），豹（5），虎（6），狮（7），象（8）");
                                System.out.println("第二个部分是字母wasd中的一个，w对应上方向，a对应左方向，s对应下方向，d对应右方向");
                                System.out.println("比如指令'1d'表示鼠向右走，'4w'表示狗向左走");
                                System.out.println("2、游戏指令");
                                System.out.println("输入restart重新开始游戏");
                                System.out.println("输入help查看帮助");
                                System.out.println("输入undo悔棋");
                                System.out.println("输入redo取消悔棋");
                                System.out.println("输入exit退出游戏");
                                break stop3;
                            }

                            //右方输入1d
                            else if (move.equals("1d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 11) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (tile[row][column] != 1) {
                                        if (animal[row][column + 1] == 8 || animal[row][column + 1] == 1 || animal[row][column + 1] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column + 1] = 11;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column + 1] >= 2 && animal[row][column + 1] <= 7) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    }else{
                                        if (animal[row][column + 1] == 0 || animal[row][column + 1] == 1){
                                            animal[row][column + 1] = 11;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop3;
                                        }
                                    }
                                }else if (tile[row][column + 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4) {
                                    animal[row + 1][column] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    animal[row][column + 1] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                            }

                            //右方输入2d
                            else if (move.equals("2d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 12) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || animal[row][column + 1] == 1 || animal[row][column + 1] == 2) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 12;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 3 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column + 1] == 5) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4) {
                                    animal[row][column + 1] = 12;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入3d
                            else if (move.equals("3d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 13) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 3)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 13;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 4 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column + 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 13;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狼不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入4d
                            else if (move.equals("4d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 14) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 4)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 14;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 5 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column + 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 14;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入5d
                            else if (move.equals("5d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 15) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 5)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 15;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 6 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column + 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4) {
                                    animal[row][column + 1] = 15;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入6d
                            else if (move.equals("6d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 16) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 6)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 16;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] >= 7 && animal[row][column + 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column + 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 16;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row][column + 1] != 1 && animal[row][column + 2] != 1 && animal[row][column + 3] != 1) {
                                        if (animal[row][column + 4] >= 1 && animal[row][column + 4] <= 6 || animal[row][column + 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column + 4] = 16;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column + 4] == 7 || animal[row][column + 4] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row][column + 4] >= 11 && animal[row][column + 4] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入7d
                            else if (move.equals("7d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 17) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 1 && animal[row][column + 1] <= 7)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 17;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] == 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column + 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 17;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row][column + 1] != 1 && animal[row][column + 2] != 1 && animal[row][column + 3] != 1) {
                                        if (animal[row][column + 4] >= 1 && animal[row][column + 4] <= 7 || animal[row][column + 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column + 4] = 17;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column + 4] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row][column + 4] >= 11 && animal[row][column + 4] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入8d
                            else if (move.equals("8d")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 18) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 8) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column + 1] != 1 && tile[row][column + 1] != 5 && tile[row][column + 1] != 2 && tile[row][column + 1] != 4) {
                                    if (animal[row][column + 1] == 0 || (animal[row][column + 1] >= 2 && animal[row][column + 1] <= 8)) {
                                        animal[row][column] = 0;
                                        animal[row][column + 1] = 18;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column + 1] == 1) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column + 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column + 1] == 2 || tile[row][column + 1] == 4){
                                    animal[row][column + 1] = 18;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入1a
                            else if (move.equals("1a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 11) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (tile[row][column] != 1) {
                                        if (animal[row][column - 1] == 8 || animal[row][column - 1] == 1 || animal[row][column - 1] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column - 1] = 11;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column - 1] >= 2 && animal[row][column - 1] <= 7) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row][column - 1] >= 11 && animal[row][column - 1] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    }else{
                                        if (animal[row][column - 1] == 0 || animal[row][column - 1] == 1){
                                            animal[row][column - 1] = 11;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop3;
                                        }
                                    }
                                }else if (tile[row][column - 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if(tile[row][column - 1] == 2 || tile[row][column - 1] == 4){
                                    animal[row][column - 1] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    animal[row][column - 1] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                            }

                            //右方输入2a
                            else if (move.equals("2a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 12) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (animal[row][column - 1] == 0 || animal[row][column - 1] == 1 || animal[row][column - 1] == 2) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 12;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 3 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column - 1] >= 11 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column - 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column - 1] == 2 || tile[row][column - 1] == 4){
                                    animal[row][column - 1] = 12;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入3a
                            else if (move.equals("3a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 13) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (animal[row][column - 1] == 0 || (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 3)) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 13;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 4 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column - 1] >= 11 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column - 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column - 1] == 2 || tile[row][column - 1] == 4) {
                                    animal[row][column - 1] = 14;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入4a
                            else if (move.equals("4a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 14) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (animal[row][column - 1] == 0 || (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 4)) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 14;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 5 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column + 1] >= 11 && animal[row][column + 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column - 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column - 1] == 2 || tile[row][column - 1] == 4) {
                                    animal[row][column - 1] = 14;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入5a
                            else if (move.equals("5a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 15) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (animal[row][column - 1] == 0 || (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 5)) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 15;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 6 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column - 1] >= 11 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column - 1] == 5) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                } else if (tile[row][column - 1] == 2 || tile[row][column - 1] == 4){
                                    animal[row][column - 1] = 15;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入6a
                            else if (move.equals("6a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 16) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (animal[row][column - 1] == 0 || (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 6)) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 16;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] >= 7 && animal[row][column - 1] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column - 1] >= 11 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column - 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column - 1] == 2 || tile[row][column - 1] == 4) {
                                    animal[row][column - 1] = 16;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row][column - 1] != 1 && animal[row][column - 2] != 1 && animal[row][column - 3] != 1) {
                                        if (animal[row][column - 4] >= 1 && animal[row][column - 4] <= 6 || animal[row][column - 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column - 4] = 16;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column - 4] == 7 || animal[row][column - 4] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row][column - 4] >= 11 && animal[row][column - 4] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入7a
                            else if (move.equals("7a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 17) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (animal[row][column - 1] == 0 || (animal[row][column - 1] >= 1 && animal[row][column - 1] <= 7)) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 17;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] == 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column - 1] >= 11 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column - 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column - 1] == 2 || tile[row][column - 1] == 4) {
                                    animal[row][column - 1] = 17;
                                    animal[row][column ] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row][column - 1] != 1 && animal[row][column - 2] != 1 && animal[row][column - 3] != 1) {
                                        if (animal[row][column - 4] >= 1 && animal[row][column - 4] <= 7 || animal[row][column - 4] == 0) {
                                            animal[row][column] = 0;
                                            animal[row][column - 4] = 17;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row][column - 4] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row][column - 4] >= 11 && animal[row][column - 4] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入8a
                            else if (move.equals("8a")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 18) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop3;
                                }
                                if (column == 0) {
                                    System.out.println("不能走出边界，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row][column - 1] != 1 && tile[row][column - 1] != 5 && tile[row][column - 1] != 2 && tile[row][column - 1] != 4) {
                                    if (animal[row][column - 1] == 0 || (animal[row][column - 1] >= 2 && animal[row][column - 1] <= 8)) {
                                        animal[row][column] = 0;
                                        animal[row][column - 1] = 18;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row][column - 1] == 1) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row][column - 1] >= 11 && animal[row][column - 1] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row][column - 1] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row][column - 1] == 2 || tile[row][column - 1] == 4) {
                                    animal[row][column - 1] = 18;
                                    animal[row][column] = 0;
                                    currentStep ++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入1w
                            else if (move.equals("1w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 11) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if (tile[row][column] != 1) {
                                        if (animal[row - 1][column] == 8 || animal[row - 1][column] == 1 || animal[row - 1][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row - 1][column] = 11;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row - 1][column] >= 2 && animal[row - 1][column] <= 7) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    }else{
                                        if(animal[row - 1][column] == 0 || animal[row - 1][column] == 1){
                                            animal[row - 1][column] = 11;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop3;
                                        }
                                    }
                                }else if(tile[row - 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4){
                                    animal[row - 1][column] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    animal[row - 1][column] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                            }

                            //右方输入2w
                            else if (move.equals("2w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 12) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 1 && animal[row - 1][column] <= 2) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 12;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 3 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row - 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4){
                                    animal[row - 1][column] = 12;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入3w
                            else if (move.equals("3w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 13) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 1 && animal[row - 1][column] <= 3) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 13;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 4 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row - 1][column] == 5) {
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4){
                                    animal[row - 1][column] = 13;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狼不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入4w
                            else if (move.equals("4w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 14) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 1 && animal[row - 1][column] <= 4) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 14;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 5 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row - 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 14;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                     break;
                                } else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入5w
                            else if (move.equals("5w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 15) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 1 && animal[row - 1][column] <= 5) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 15;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 6 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row - 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 15;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入6w
                            else if (move.equals("6w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 16) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 1 && animal[row - 1][column] <= 6) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 16;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] >= 7 && animal[row - 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row - 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4){
                                    animal[row - 1][column] = 16;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                     break;
                                } else {
                                    if (animal[row - 1][column] != 1 && animal[row - 2][column] != 1) {
                                        if (animal[row - 3][column] >= 1 && animal[row - 3][column] <= 6 || animal[row - 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row - 3][column] = 16;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row - 3][column] == 7 || animal[row - 3][column] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row - 3][column] >= 11 && animal[row - 3][column] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入7w
                            else if (move.equals("7w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 17) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 1 && animal[row - 1][column] <= 7) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 17;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] == 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row - 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4){
                                    animal[row - 1][column] = 17;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row - 1][column] != 1 && animal[row - 2][column] != 1) {
                                        if (animal[row - 3][column] >= 1 && animal[row - 3][column] <= 7  || animal[row - 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row - 3][column] = 17;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row - 3][column] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row - 3][column] >= 11 && animal[row - 3][column] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入8w
                            else if (move.equals("8w")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 18) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 0) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row - 1][column] != 1 && tile[row - 1][column] != 5 && tile[row - 1][column] != 2 && tile[row - 1][column] != 4) {
                                    if ((animal[row - 1][column] >= 2 && animal[row - 1][column] <= 8) || animal[row - 1][column] == 0) {
                                        animal[row][column] = 0;
                                        animal[row - 1][column] = 18;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row - 1][column] == 1) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row - 1][column] >= 11 && animal[row - 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row - 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row - 1][column] == 2 || tile[row - 1][column] == 4) {
                                    animal[row - 1][column] = 18;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入1s
                            else if (move.equals("1s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 11) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("鼠已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if (tile[row][column] != 1) {
                                        if (animal[row + 1][column] == 8 || animal[row + 1][column] == 1 || animal[row + 1][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row + 1][column] = 11;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row + 1][column] >= 2 && animal[row + 1][column] <= 7) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    }else{
                                        if (animal[row + 1][column] == 0 || animal[row + 1][column] == 1){
                                            animal[row + 1][column] = 11;
                                            animal[row][column] = 0;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }else{
                                            System.out.println("岸上有动物，不能上岸，请重新输入：");
                                            break stop3;
                                        }
                                    }
                                }else if (tile[row + 1][column] == 5 ){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    animal[row + 1][column] = 11;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                            }

                            //右方输入2s
                            else if (move.equals("2s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 12) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("猫已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 1 && animal[row][column] <= 2 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 12;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 3 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row + 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 12;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("猫不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入3s
                            else if (move.equals("3s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 13) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狼已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 1 && animal[row][column] <= 3 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 13;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 4 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row + 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 13;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狼不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入4s
                            else if (move.equals("4s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 14) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狗已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 1 && animal[row][column] <= 4 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 14;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 5 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row + 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 14;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("狗不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方输入5s
                            else if (move.equals("5s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 15) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("豹已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 1 && animal[row][column] <= 5 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 15;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 6 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row + 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if(tile[row + 1][column] == 2 || tile[row + 1][column] == 4){
                                    animal[row + 1][column] = 15;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    System.out.println("豹不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //右方走输入6s
                            else if (move.equals("6s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 16) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("虎已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 1 && animal[row][column] <= 6 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 16;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] >= 7 && animal[row + 1][column] <= 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row + 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4) {
                                    animal[row + 1][column] = 16;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row + 1][column] != 1 && animal[row + 2][column] != 1) {
                                        if (animal[row + 3][column] >= 1 && animal[row + 3][column] <= 6 || animal[row + 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row + 3][column] = 16;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row + 3][column] == 7 || animal[row + 3][column] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row + 3][column] >= 11 && animal[row + 3][column] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入7s
                            else if (move.equals("7s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 17) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("狮已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 1 && animal[row][column] <= 7 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 17;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] == 8) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row + 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if(tile[row + 1][column] == 2 || tile[row + 1][column] == 4) {
                                    animal[row + 1][column] = 17;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                } else {
                                    if (animal[row + 1][column] != 1 && animal[row + 2][column] != 1) {
                                        if (animal[row + 3][column] >= 1 && animal[row + 3][column] <= 7  || animal[row + 3][column] == 0) {
                                            animal[row][column] = 0;
                                            animal[row + 3][column] = 17;
                                            currentStep++;
                                            lastStep = 0;
                                            break;
                                        }
                                        if (animal[row + 3][column] == 8) {
                                            System.out.println("不能送死，请重新输入：");
                                            break stop3;
                                        }
                                        if (animal[row + 3][column] >= 11 && animal[row + 3][column] <= 18) {
                                            System.out.println("不能吃己方动物，请重新输入：");
                                            break stop3;
                                        }
                                    } else {
                                        System.out.println("敌方老鼠在水中，不能跳河，请重新输入：");
                                        break stop3;
                                    }
                                }
                            }

                            //右方输入8s
                            else if (move.equals("8s")) {
                                stop1:
                                {
                                    while (row < 7) {
                                        column = 0;
                                        while (column < 9) {
                                            if (animal[row][column] == 18) {
                                                break stop1;
                                            }
                                            column++;
                                        }
                                        row++;
                                    }
                                    System.out.println("象已死，请重新输入：");
                                    break stop3;
                                }
                                if (row == 6) {
                                    System.out.println("输入有误，请重新输入：");
                                    break stop3;
                                }
                                if (tile[row + 1][column] != 1 && tile[row + 1][column] != 5 && tile[row + 1][column] != 2 && tile[row + 1][column] != 4) {
                                    if ((animal[row + 1][column] >= 2 && animal[row][column] <= 8 || animal[row + 1][column] == 0)) {
                                        animal[row][column] = 0;
                                        animal[row + 1][column] = 18;
                                        currentStep++;
                                        lastStep = 0;
                                        break;
                                    }
                                    if (animal[row + 1][column] == 1) {
                                        System.out.println("不能送死，请重新输入：");
                                        break stop3;
                                    }
                                    if (animal[row + 1][column] >= 11 && animal[row + 1][column] <= 18) {
                                        System.out.println("不能吃己方动物，请重新输入：");
                                        break stop3;
                                    }
                                }else if (tile[row + 1][column] == 5){
                                    System.out.println("不能走入自己的家，请重新输入：");
                                    break stop3;
                                }else if (tile[row + 1][column] == 2 || tile[row + 1][column] == 4) {
                                    animal[row + 1][column] = 18;
                                    animal[row][column] = 0;
                                    currentStep++;
                                    lastStep = 0;
                                    break;
                                }
                                else {
                                    System.out.println("象不能下水，请重新输入：");
                                    break stop3;
                                }
                            }

                            //输入exit
                            else if (move.equals("exit")) {
                                return;
                            }

                            //输入restart
                            else if (move.equals("restart")) {
                                break stop2;
                            }

                            //输入undo悔棋
                            else if (move.equals("undo")) {
                                if (currentStep > 0) {
                                    lastStep++;
                                    currentStep--;
                                    for (row = 0; row < 7; row++) {
                                        for (column = 0; column < 9; column++) {
                                            animal[row][column] = animalHistory[currentStep][row][column];
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("当前步数为0，不能悔棋，请重新输入：");
                                    break stop3;
                                }
                            }

                            //输入redo取消悔棋
                            else if (move.equals("redo")) {
                                if (lastStep > 0) {
                                    lastStep--;
                                    currentStep++;
                                    for (row = 0; row < 7; row++) {
                                        for (column = 0; column < 9; column++) {
                                            animal[row][column] = animalHistory[currentStep][row][column];
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("当前悔棋次数为0，不能取消悔棋，请重新输入：");
                                    break stop3;
                                }
                            }else{
                                System.out.println("输入有误，请重新输入：");
                                break stop3;
                            }
                        }
                        move = scanner.next();
                    }

                    //判断右方是否走入左方兽穴
                    if (animal[3][0] != 0) {
                        System.out.println("右方获胜");
                        return;
                    }

                    //判断左方是否无子
                    int n = 0;
                    row = 0;
                    column = 0;
                    while (row < 7) {
                        column = 0;
                        while (column < 9) {
                            if (animal[row][column] >= 1 && animal[row][column] <= 8) {
                                n++;
                            }
                            column++;
                        }
                        row++;
                    }
                    if (n == 0) {
                        System.out.println("右方获胜");
                        return;
                    }


                    //记录棋盘
                    for (row = 0; row < 7; row++) {
                        for (column = 0; column < 9; column++) {
                            animalHistory[currentStep][row][column] = animal[row][column];
                        }
                    }

                    map(animal, tile);
                    System.out.println("左方玩家行动：");
                    move = scanner.next();
                }
            }
        }
    }
}
