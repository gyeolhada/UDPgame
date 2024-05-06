/*
"人机对战"困难模式
*/
public class SixActivity extends ThirdActivity { // 继承简单模式
    @Override
    public void computer() {// 重写电脑下棋方法
        if(computer2()){}// 先防守
        else computer1();// 后主动
    }
    public boolean computer1() { // 判断是否存在获胜的下一步走法
        int k=0;
        for(int j:boom) k+=j;// 统计已触发的棋子数量
        if(k==1&&boom[4]==1){ // 我方已下第一个棋且选择中心
            boom[0]=5;// 机下在左上角（对角线）
            buttons[0].setIcon(b);
            return true;
        }else if(k==1&&(boom[4]==0)){// 我方已下第一个棋且不在中心
            boom[4]=5;// 机下在中心位置
            buttons[4].setIcon(b);
            return true;
        }else if(boom[0]+boom[1]+boom[2]==2){// 如果第一排有两枚敌方棋子
            for(int i=0;i<3;i++){// 在第一行找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if(boom[3]+boom[4]+boom[5]==2){ // 如果第二排有两枚敌方棋子
            for(int i=3;i<6;i++){// 在第二行找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[6]+boom[7]+boom[8]==2){// 如果第三排有两枚敌方棋子
            for(int i=6;i<9;i++){// 在第三行找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if(boom[0]+boom[3]+boom[6]==2){// 如果第一列有两枚敌方棋子
            for(int i=0;i<9;i+=3){// 在第一列找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[1]+boom[4]+boom[7]==2){// 如果第二列有两枚敌方棋子
            for(int i=1;i<=7;i+=3){// 在第二列找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[2]+boom[5]+boom[8]==2){// 如果第三列有两枚敌方棋子
            for(int i=2;i<=8;i+=3){// 在第三列找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if(boom[0]+boom[4]+boom[8]==2){// 如果主对角线有两枚敌方棋子
            for(int i=0;i<=8;i+=4){// 在对角线上找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[2]+boom[4]+boom[6]==2){// 如果副对角线有两枚敌方棋子
            for(int i=2;i<=6;i+=2){// 在对角线上找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else{// 若无获胜走法，则调用普通模式下棋方法
            super.computer();
            return false;
        }
        return false;
    }
    public boolean computer2(){  // 判断是否存在防守的下一步走法
        if(boom[0]+boom[1]+boom[2]==10){ // 若第一行有两个敌方棋子
            for(int i=0;i<3;i++){// 在第一行找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if(boom[3]+boom[4]+boom[5]==10){// 若第二行有两个敌方棋子
            for(int i=3;i<6;i++){// 在第二行找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[6]+boom[7]+boom[8]==10){// 若第三行有两个敌方棋子
            for(int i=6;i<9;i++){// 在第三行找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if(boom[0]+boom[3]+boom[6]==10){// 若第一列有两个敌方棋子
            for(int i=0;i<9;i+=3){// 在第一列找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[1]+boom[4]+boom[7]==10){// 若第二列有两个敌方棋子
            for(int i=1;i<=7;i+=3){ // 在第二列找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[2]+boom[5]+boom[8]==10){// 若第三列有两个敌方棋子
            for(int i=2;i<=8;i+=3){// 在第三列找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if(boom[0]+boom[4]+boom[8]==10){ // 若主对角线有两个敌方棋子
            for(int i=0;i<=8;i+=4){// 在对角线上找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else if( boom[2]+boom[4]+boom[6]==10){// 若副对角线有两个敌方棋子
            for(int i=2;i<=6;i+=2){// 在对角线上找到未触发的位置并下棋
                if(boom[i]==0){
                    boom[i]=5;
                    buttons[i].setIcon(b);
                    return true;
                }
            }
        }else return false;
    return false;
    }
}
