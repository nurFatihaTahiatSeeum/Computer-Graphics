import java.util.Scanner; 
public class MidpointLine{
    static int x0,y0,x1,y1,dx,dy,zone; 
    public static void main (String[] args){
        Scanner sc = new Scanner (System.in); 
        System.out.println("input X0"); 
        x0 = sc.nextInt(); 
        System.out.println("input Y0"); 
        y0 = sc.nextInt();
        System.out.println("input X1"); 
        x1 = sc.nextInt();
        System.out.println("input Y1"); 
        y1 = sc.nextInt(); 
        dx = x1-x0; 
        dy = y1-y0;
        findZone(); 
        convertToZone0(); // value manipulated
        midPointAlgo(); // value manipulated
        
    }
    
    public static void findZone(){
        if ( Math.abs(dx)>Math.abs(dy) ){
            
            if (dx>0 && dy>0){
                zone = 0 ; 
            }
            else if (dx<0 && dy>0){
                zone = 3 ; 
            }
            else if (dx<0 && dy<0){
                zone = 4 ; 
            }
            else if (dx>0 && dy<0){
                zone = 7 ; 
            }
        }
         else if ( Math.abs(dx)==Math.abs(dy) ){
            System.out.println("gkaskdkasdkas"); 
            if (dx>0 && dy>0){
                zone = 0 ; 
            }
            else if (dx<0 && dy>0){
                zone = 3 ; 
            }
            else if (dx<0 && dy<0){
                zone = 4 ; 
            }
            else if (dx>0 && dy<0){
                zone = 7 ; 
            }
        }
        else {
            if(dx>0 && dy>0 ){
                zone = 1 ; 
            }
            else if(dx<0 && dy>0 ){
                zone = 2 ; 
                
            }
            else if(dx<0 && dy<0 ){
                zone = 5 ; 
            }
            else if(dx>0 && dy<0 ){
                zone = 6 ; 
            }
        }
        System.err.println("I am at zone "+zone);
    } // end method
    
    public static void convertToZone0(){
        
        int  t0  = x0 ; 
        int  t1  = x1 ;
        if (zone==1){
            
            x0 = y0 ; 
            x1 = y1; 
            y0 = t0;
            y1 = t1;    
        }
        else if (zone==2){
//            x0 = (-1)*y0 ; 
//            x1 = (-1)*y1; 
//            y0 = x0;
//            y1 = x1;  
            
            x0 = y0 ; 
            x1 = y1; 
            y0 = (-1)*t0;
            y1 = (-1)*t1;    
          //  System.err.println(x0+" "+y0+" "+x1+" "+y1); 
        }
        else if (zone==3){
            x0 = (-1)*x0 ; 
            x1 = (-1)*x1; 
//                y0 = y0;
//                y1 = y1;    
        }
        else if (zone==4){
            x0 = (-1)*x0 ; 
            x1 = (-1)*x1; 
            y0 = (-1)*y0;
            y1 = (-1)*y1;    
        }
        else if (zone==5){
            x0 = (-1)*y0 ; 
            x1 = (-1)*y1; 
            y0 = (-1)*t0;
            y1 = (-1)*t1;    
        }
        else if (zone==6){
            x0 = (-1)*y0 ; 
            x1 = (-1)*y1; 
            y0 = t0;
            y1 = t1;    
        }
        else if (zone==7){
            y0 = (-1)*y0;
            y1 = (-1)*y1;    
        }
        
        
    }
    
    
    public static void midPointAlgo(){
        dx = x1-x0; 
        dy = y1-y0;
        int d= 2*dy - dx ; 
        System.err.println("initial D  "+d);
        int incrE = 2*dy ; 
        System.err.println("initial E  "+incrE);
        int incrNE = 2 * (dy - dx); 
             System.err.println("initial NE  "+incrNE);
        int x= x0 ; // x0 is working as x
        int y= y0; 
        System.err.println("d \t\t x' \t\t y' \t\t x \t\t y "); 
        originalZone(x,y);
        System.out.println(d+"\t\t"+x+"\t\t"+y+"\t\t"+x0+"\t\t"+y0); 
        while (x < x1) { //manipulate it for the n number of iteration 
            if (d <= 0) { 
                //choose E
                d = d + incrE;
                x = x + 1;
                
            }
            else {
                //choose NE
                d = d + incrNE;
                x = x + 1; 
                y = y + 1;
            }
            originalZone(x,y);
            System.out.println(d+"\t\t"+x+"\t\t"+y+"\t\t"+x0+"\t\t"+y0); 
        }
    }
    
    public static void originalZone(int x, int y){
        if (zone==1){
            x0 = y ; 
//            x1 = y1; 
            y0 = x;
//            y1 = x1;    
        }
        else if (zone==2){
            x0 = (-1)*y ; 
//            x1 = (-1)*y1; 
            y0 = x;
//            y1 = x1;    
        }
        else if (zone==3){
            x0 = (-1)*x ; 
//                x1 = (-1)*x1; 
            y0 = y;
//                y1 = y1;    
        }
        else if (zone==4){
            x0 = (-1)*x ; 
//                x1 = (-1)*x1; 
            y0 = (-1)*y;
//                y1 = (-1)*y1;    
        }
        else if (zone==5){
            x0 = (-1)*y ; 
//                x1 = (-1)*y1; 
            y0 = (-1)*x;
//                y1 = (-1)*x1;    
        }
        else if (zone==6){
            x0 = y ; 
//                x1 = y1; 
            y0 = (-1)*x;
//                y1 = (-1)*x1;    
        }
        else if (zone==7){
            x0=x ; 
            y0 = (-1)*y ; 
        }
        else{
            x0 = x ; 
            y0 = y ; 
        }
        
        
    }
}
